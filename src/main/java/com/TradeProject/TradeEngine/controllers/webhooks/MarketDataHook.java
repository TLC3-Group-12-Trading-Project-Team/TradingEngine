package com.TradeProject.TradeEngine.controllers.webhooks;

import com.TradeProject.TradeEngine.dto.MarketDataDto;
import com.TradeProject.TradeEngine.models.MarketData;
import com.TradeProject.TradeEngine.services.MarketDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/webhooks")
public class MarketDataHook {
    private final MarketDataService marketDataService;

    private static Logger logger = LoggerFactory.getLogger(MarketDataHook.class);

    @Autowired
    public MarketDataHook(MarketDataService marketDataService){
        this.marketDataService = marketDataService;
    }

    @PostMapping("/market-data")
    public void onMarket1DataUpdate(@RequestBody List<MarketData> md) {
        // save to database
        for(MarketData marketData : md){
            marketData.setExchange("exchange-1");
            marketData.setCreatedAt();
            marketDataService.createMarketData(marketData);

        }
        logger.info(md.toString());
    }

    @PostMapping("/market-data-2")
    public void onMarket2DataUpdate(@RequestBody List<MarketData> md) {
        // save to database
        for(MarketData marketData : md){
            marketData.setExchange("exchange-2");
            marketData.setCreatedAt();
            marketDataService.createMarketData(marketData);

        }
        logger.info(md.toString());
    }
}
