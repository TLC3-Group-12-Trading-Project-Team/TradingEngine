package com.TradeProject.TradeEngine.redis.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

@Service
public class TradeMessageQueueProducer {
    private static final Logger LOGGER = LoggerFactory.getLogger(TradeMessageQueueProducer.class);

    @Value("${redis.queue.name}")
    private String exchangeConnectionServiceQueue;

    public void sendDataToRedisQueue(String input) {
//		redisTemplate.convertAndSend(topic.getTopic(), input);
        Jedis jedis = new Jedis("localhost",6379);
        jedis.rpush(exchangeConnectionServiceQueue,input);
        LOGGER.info("Data - " + input + " sent through Redis Topic - " );
        
    }
}
