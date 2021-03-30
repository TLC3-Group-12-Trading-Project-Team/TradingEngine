package com.TradeProject.TradeEngine.services;

import com.TradeProject.TradeEngine.Repository.MarketDataRepository;
import com.TradeProject.TradeEngine.dto.MarketDataDto;
import com.TradeProject.TradeEngine.models.MarketData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MarketDataService {
    private final MarketDataRepository marketDataRepository;

    @Autowired
    public  MarketDataService(MarketDataRepository marketDataRepository){
        this.marketDataRepository = marketDataRepository;
    }

    public void createMarketData(MarketData marketData){
        this.marketDataRepository.save(marketData);
    }

}
