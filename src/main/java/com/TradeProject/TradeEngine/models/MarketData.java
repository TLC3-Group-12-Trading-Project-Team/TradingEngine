package com.TradeProject.TradeEngine.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import java.security.Timestamp;
import java.time.LocalDateTime;

@Getter
@Service
@AllArgsConstructor
@ToString
@Setter
@NoArgsConstructor
@Table
@Entity(name="ExchangeMarketData")
public class MarketData {

    @Id
    @SequenceGenerator(
            name= "market_data_sequence",
            sequenceName = "market_data_sequence",
            allocationSize = 1
    )

    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "market_data_sequence"
    )
    @Column(
            nullable=false,
            updatable = false
    )
    private Long id;
    @JsonProperty(value = "LAST_TRADED_PRICE")
    private double lastTradedPrice;
    @JsonProperty(value = "BID_PRICE")
    private double bidPrice;
    @JsonProperty(value = "SELL_LIMIT")
    private int sellLimit;
    @JsonProperty(value = "MAX_PRICE_SHIFT")
    private double maxPriceShift;
    @JsonProperty(value = "TICKER")
    private String ticker;
    @JsonProperty(value = "ASK_PRICE")
    private double askPrice;
    @JsonProperty(value = "BUY_LIMIT")
    private int buyLimit;
    @JsonIgnore
    private String exchange;

//    @CreationTimestamp
//    @Column(name="createdAt", nullable = false, updatable = false, insertable = false)
    private LocalDateTime createdAt;

    public void setExchange(String exchange){
        this.exchange = exchange;
    }
    public void setCreatedAt(){
        this. createdAt = LocalDateTime.now();
    }

    public double getLastTradedPrice() {
        return lastTradedPrice;
    }

    public double getBidPrice() {
        return bidPrice;
    }

    public double getAskPrice() {
        return askPrice;
    }

    public double getMaxPriceShift() {
        return maxPriceShift;
    }

    public int getBuyLimit() {
        return buyLimit;
    }

    public int getSellLimit() {
        return sellLimit;
    }

    public String getTicker() {
        return ticker;
    }

    public Long getId() {
        return id;
    }
}
