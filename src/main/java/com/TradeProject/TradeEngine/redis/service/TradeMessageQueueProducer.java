package com.TradeProject.TradeEngine.redis.service;

import com.TradeProject.TradeEngine.models.Order;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.util.List;

@Service
public class TradeMessageQueueProducer {
    private static final Logger LOGGER = LoggerFactory.getLogger(TradeMessageQueueProducer.class);

    ObjectMapper objectMapper = new ObjectMapper();

    @Value("${spring.redis.queue.name}")
    private String exchangeConnectionServiceQueue;

    @Autowired
    Jedis jedis;

    public void sendDataToRedisQueue(String input) {
        jedis.rpush(exchangeConnectionServiceQueue,input);
        LOGGER.info("Data - " + input + " sent through Redis Topic - " );
        
    }
}
