package com.TradeProject.TradeEngine.dto;

import com.TradeProject.TradeEngine.models.OrderTransaction;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.persistence.criteria.Order;
import java.time.LocalDateTime;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Orders {
    @JsonProperty(value = "id")
    private Long id;
    @JsonProperty(value = "product")
    private String product;
    @JsonProperty(value = "quantity")
    private int quantity;
    @JsonProperty(value = "price")
    private Double price;
    @JsonProperty(value = "status")
    private String status;
    @JsonProperty(value = "side")
    private String side;
//    private String exchangeOrderId;
    @JsonProperty(value = "exchange")
    private String exchange;
    @JsonProperty(value = "action")
    private String action ;

    public String getSide() {
        return side;
    }

    public String getProduct() {
        return product;
    }

    public void setExchange(String exchange){
        this.exchange = exchange;
    }

    public String toString(){
        return id + " "+
                product +" "
                + quantity +" "
                + price + " "
                + status + " "
                + side + " "
                +  exchange
                +" " +action;
    }

    public  Orders(){

    }
    public String getAction() {
        return action;
    }

    public Long getId() {
        return id;
    }

    public int getQuantity() {
        return quantity;
    }

    public Double getPrice() {
        return price;
    }

    public String getExchange() {
        return exchange;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setId(Long id) {
        this.id = id;
    }
}


