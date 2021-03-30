package com.TradeProject.TradeEngine.redis.resource;

import com.TradeProject.TradeEngine.models.MarketData;
import com.TradeProject.TradeEngine.models.Order;
import com.TradeProject.TradeEngine.redis.service.TradeMessageQueueProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/redis")
public class RedisController {
    @Autowired
    private TradeMessageQueueProducer sender;

    @PostMapping
    public String sendDataToRedisQueue(@RequestBody Order orders) {
        sender.sendDataToRedisQueue(orders);
        return "Your message was recieved";
    }
}
