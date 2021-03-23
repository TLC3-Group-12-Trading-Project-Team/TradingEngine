package com.TradeProject.TradeEngine.redis.service;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import redis.clients.jedis.Jedis;

public class OVSListener implements MessageListener {

    @Override
    public void onMessage(Message message, byte[] pattern) {

    }
}
