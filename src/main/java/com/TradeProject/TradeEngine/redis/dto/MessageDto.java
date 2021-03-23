package com.TradeProject.TradeEngine.redis.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class MessageDto {
    private int portfolioId;
    private String product;
    private int quantity;
    private double price;
    private String side;

}