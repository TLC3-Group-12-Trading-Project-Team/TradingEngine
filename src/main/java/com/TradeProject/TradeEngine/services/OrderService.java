package com.TradeProject.TradeEngine.services;


import com.TradeProject.TradeEngine.controllers.webhooks.MarketDataHook;
import com.TradeProject.TradeEngine.dto.Orders;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.io.DataInput;
import java.io.IOException;

@Service
public class OrderService {

    @Autowired
    private RestTemplate restTemplate;

    private ObjectMapper objectMapper = new ObjectMapper();

//    private final OrderRepository orderRepository;
    @Value("${api.ordervalidation.host}")
    private String orderValidationApi;

    private static Logger logger = LoggerFactory.getLogger(MarketDataHook.class);

//    @Autowired
//    public OrderService(OrderRepository orderRepository){
//        this.orderRepository = orderRepository;
//    }

//    public void createOrders(Orders orders){
//        this.orderRepository.save(orders);
//    }

    public Orders getOrder(Long orderId){
        String uri = orderValidationApi + "/orders/"+ orderId;
        logger.info("uri: "+ uri);
        try {
            return this.restTemplate.getForObject(uri, Orders.class);
//            JsonNode responseJson = objectMapper.readTree(response.getBody());
//            Orders order = objectMapper.readValue( response,Orders.class);
//            logger.info("response: "+String.valueOf(responseJson));
//            return  order;


        } catch (HttpServerErrorException  e){
            logger.error(String.valueOf(e));
            e.printStackTrace();
        }
        return null;
    }


    public void updateOrders(Orders orders){
        String uri = orderValidationApi+ "/orders/"+ orders.getId();
        logger.info("uri: "+ uri);
        try {
            HttpEntity<Orders> request = new HttpEntity<>(orders);
            this.restTemplate.exchange(uri, HttpMethod.PUT, request, Void.class);
//            JsonNode responseJson = objectMapper.readTree(response.getBody());
//            Orders order = objectMapper.readValue((DataInput) response,Orders.class);
//            logger.info("response: "+String.valueOf(responseJson));
//            return  order;


        } catch (HttpServerErrorException e){
            logger.error(String.valueOf(e));
            e.printStackTrace();
        }
//        return null;
    }
}