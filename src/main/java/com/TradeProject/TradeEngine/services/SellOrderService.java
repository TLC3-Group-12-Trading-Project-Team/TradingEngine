package com.TradeProject.TradeEngine.services;

import com.TradeProject.TradeEngine.Repository.MarketDataRepository;
import com.TradeProject.TradeEngine.models.MarketData;
import com.TradeProject.TradeEngine.dto.Orders;
import com.TradeProject.TradeEngine.redis.service.TradeMessageQueueProducer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class SellOrderService {
    private final MarketDataRepository marketDataRepository;

    @Autowired
    public SellOrderService(MarketDataRepository marketDataRepository) {
        this.marketDataRepository = marketDataRepository;
    }

    private static Logger logger = LoggerFactory.getLogger(BuyOrderService.class);


    @Autowired
    private TradeMessageQueueProducer sender;

    @Value("${api.exchange.first}")
    private String exchangeOneAPI;

    @Value("${api.exchange.second}")
    private String exchangeTwoAPI;


    ObjectMapper objectMapper = new ObjectMapper();

    private List<MarketData> exchangeOneMarketData;
    private List<MarketData> exchangeTwoMarketData;

    public void processOrder(Orders marketOrder){
        logger.info(marketOrder.getAction());
        Orders order = marketOrder;
        logger.info(order.toString());
        exchangeOneMarketData = marketDataRepository.
                findCurrentTickerByCurrentExchange("exchange-1", order.getProduct());
        exchangeTwoMarketData = marketDataRepository.
                findCurrentTickerByCurrentExchange("exchange-2", order.getProduct());
        logger.info("exchange one data: "+ exchangeOneMarketData);
        logger.info("exchange two data: "+ exchangeTwoMarketData);
        double exchangeOnePrice = exchangeOneMarketData.get(0).getBidPrice();
        double exchangeTwoPrice = exchangeTwoMarketData.get(0).getBidPrice();
        try {
            if(exchangeOnePrice < exchangeTwoPrice){
                // check if number of stocks are available in this market
                order.setExchange(exchangeOneAPI);
            }
            else{
                order.setExchange(exchangeTwoAPI);
            }
            logger.info(order.toString());
            String orderValueAsString = objectMapper.writeValueAsString(order);
            logger.info(orderValueAsString);
            sender.sendDataToRedisQueue(orderValueAsString);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }


}
