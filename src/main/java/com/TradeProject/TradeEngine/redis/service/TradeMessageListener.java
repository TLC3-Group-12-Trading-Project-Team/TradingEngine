package com.TradeProject.TradeEngine.redis.service;

import com.TradeProject.TradeEngine.dto.Orders;
import com.TradeProject.TradeEngine.services.BuyOrderService;
import com.TradeProject.TradeEngine.services.SellOrderService;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Slf4j
@Service
public class TradeMessageListener implements MessageListener {
    private final ObjectMapper objectMapper;
    private static  Logger logger = LoggerFactory.getLogger(TradeMessageListener.class);

    @Autowired
    private TradeMessageQueueProducer sender;

    @Autowired
    BuyOrderService buyOrderService;

    @Autowired
    SellOrderService sellOrderService;

    public TradeMessageListener(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
        this.objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    @Override
    public void onMessage(Message message, byte[] pattern) {

        try {

            Orders order =objectMapper.readValue(message.getBody(), Orders.class);
            logger.info(String.valueOf(order.toString()));
            if(order != null) {
                logger.info("Channel: {}, Message: {}", new String(message.getChannel()), order);
                logger.info("This is the data{}",message);
                // perform trade engine logic
                logger.info(order.getSide());
                if(order.getSide().equals("BUY")){
                    logger.info("do");
                    buyOrderService.processOrder(order);
                }
                else if(order.getSide().equals("SELL")){
                    logger.info("process sell order");
                    sellOrderService.processOrder(order);
                }
                //is successful forward trade to queue
//                sender.sendDataToRedisQueue(String.valueOf(order));
            }
        } catch (IOException e) {
            logger.error("Couldn't convert json", e);
        }
        logger.info("This is the data{}",message);
    }
}
