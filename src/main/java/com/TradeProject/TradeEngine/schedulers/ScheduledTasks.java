package com.TradeProject.TradeEngine.schedulers;


import com.TradeProject.TradeEngine.Exceptions.ResourceNotFoundException;
import com.TradeProject.TradeEngine.Repository.OrderTransactionRepository;
import com.TradeProject.TradeEngine.dto.Orders;
import com.TradeProject.TradeEngine.models.OrderTransaction;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.criterion.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
public class ScheduledTasks {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    OrderTransactionRepository orderTransactionRepository;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Value("${api.key}")
    private String apiKey;

    private static final Logger logger = LoggerFactory.getLogger(ScheduledTasks.class);
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    @Scheduled(fixedRate = 15000)
    public void scheduleTaskToCheckOrderStatus() throws JsonProcessingException {
        logger.info("Fixed Rate Task :: Execution Time - {}", dateTimeFormatter.format(LocalDateTime.now()) );
        List<OrderTransaction> orderTransactionList = orderTransactionRepository.findAllByStatus("active");
        logger.info("TRansaction list: "+orderTransactionList.toString());
        for(OrderTransaction orderTransaction: orderTransactionList){
            logger.info("Transaction: "+ orderTransaction);
            String uri = orderTransaction.getExchange()+ "/"+ apiKey+"/order/"+orderTransaction.getExchangeOrderId() ;
            logger.info("uri: "+ uri);
            try {
                ResponseEntity<String> response = this.restTemplate.getForEntity(uri, String.class);
                JsonNode responseJson = objectMapper.readTree(response.getBody());
                logger.info("response: "+String.valueOf(responseJson));
                String quantity = String.valueOf(responseJson.get("quantity"));   //findValue("quantity").asText();
                String cummulativeQuantity = String.valueOf(responseJson.findValue("cumulatitiveQuantity")); //.asText();
                logger.info("===================================================");
                logger.info("cummulativeQuantity: "+ cummulativeQuantity);
                logger.info("quantity: "+ quantity);
                logger.info("====================================================");
                if(Integer.parseInt(quantity) == Integer.parseInt( cummulativeQuantity)){
                    orderTransactionRepository.findById(orderTransaction.getId()).map(orderTransaction1 -> {
                        orderTransaction.setStatus("complete");
                        return orderTransactionRepository.save(orderTransaction);
                    }).orElseThrow(
                            ()->new ResourceNotFoundException("orderTransactionId:  " + orderTransaction.getId()  + " not found"));
                    // update orders
                    Orders order;
                    order = new Orders();
                    order.setStatus("complete");
//                    order.setId();


                }

            } catch (HttpServerErrorException  e){
                logger.error(String.valueOf(e));
                e.printStackTrace();
            }


        }
    }

//    public void scheduleTaskWithFixedDelay() {}
//
//    public void scheduleTaskWithInitialDelay() {}
//
//    public void scheduleTaskWithCronExpression() {}
}
