package com.TradeProject.TradeEngine.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Order implements Serializable {
    private String product;
    private int quantity;
    private double price;
    private String side;
    private String action;
    private String exchange;
    private String OrderId;

    public String getExchange() {
        return exchange;
    }

    public String getOrderId() {
        return OrderId;
    }

    public String getAction() {
        return action;
    }
}