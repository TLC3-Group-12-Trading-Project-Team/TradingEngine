package com.TradeProject.TradeEngine.Repository;

import com.TradeProject.TradeEngine.dto.MarketDataDto;
import com.TradeProject.TradeEngine.models.MarketData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MarketDataRepository extends JpaRepository<MarketData, Long> {
    @Query(value = "select * from exchange_market_data where exchange = ?1 and ticker = ?2 order by id desc LIMIT 1",
            nativeQuery = true
    )
    List<MarketData> findCurrentTickerByCurrentExchange(String exchange, String ticker);

//    MarketData findByExchangeAndTicker(String exchange, String ticker);
}
