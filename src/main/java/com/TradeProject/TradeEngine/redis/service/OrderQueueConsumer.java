package com.TradeProject.TradeEngine.redis.service;

import com.TradeProject.TradeEngine.models.OrderTransaction;
import com.TradeProject.TradeEngine.services.OrderTransactionService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

import java.util.List;

@Component
public class OrderQueueConsumer {
//
//    @Autowired
//    Jedis jedis;
//
//    @Autowired
//    OrderTransactionService orderTransactionService;
//
//    private static final int TIMEOUT = 30000;
//
//    @Value("${spring.redis.queue.trading-engine.name}")
//    String QUEUE_NAME;
//
//
//    ObjectMapper objectMapper = new ObjectMapper();
//
//    @EventListener(ApplicationReadyEvent.class)
//    public void listenForMessages() {
//        System.out.println("listening for messages from " + QUEUE_NAME);
//        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//        while (true) {
//            List<String> messages = jedis.blpop(TIMEOUT, QUEUE_NAME);
//            System.out.println(messages.get(1));
//            String orderTransactionJsonString = messages.get(1);
//            try {
//                OrderTransaction orderTransaction = objectMapper.readValue(orderTransactionJsonString, OrderTransaction.class);
//                this.orderTransactionService.createOrderTransaction(orderTransaction);
//            } catch (JsonProcessingException e) {
//                e.printStackTrace();
//            }
//
//        }
//    }
}

