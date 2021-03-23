package com.TradeProject.TradeEngine.redis.service;

import com.TradeProject.TradeEngine.models.Orders;
import com.TradeProject.TradeEngine.redis.dto.MessageDto;
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


    public TradeMessageListener(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public void onMessage(Message message, byte[] pattern) {

        try {
            Orders ord =objectMapper.readValue(message.toString(), Orders.class);
            logger.info(String.valueOf(ord.toString()));
            if(ord != null) {
                logger.info("Channel: {}, Message: {}", new String(message.getChannel()), ord);
                // perform trade engine logic

                //is successful forward trade to queue
                sender.sendDataToRedisQueue(String.valueOf(ord));
            }
        } catch (IOException e) {
            logger.error("Couldn't convert json", e);
        }
    }
}
