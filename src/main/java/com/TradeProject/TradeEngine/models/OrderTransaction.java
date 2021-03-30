package com.TradeProject.TradeEngine.models;

import com.TradeProject.TradeEngine.dto.Orders;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Service
@AllArgsConstructor
@ToString
@Setter
@NoArgsConstructor
@Table
@Entity(name="order_transaction")
public class OrderTransaction {
    @Id
    @SequenceGenerator(
            name= "order_transaction_sequence",
            sequenceName = "order_transaction_sequence",
            allocationSize = 1
    )

    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "order_transaction_sequence"
    )
    @Column(
            nullable=false,
            updatable = false
    )
    private Long id;

    private String status;

    private String exchange;

    private double price;
//    @ManyToOne
//    @JoinColumn(name="orders_id")
//    @JsonBackReference
//    private Orders orders;

    private String exchangeOrderId;

//    private Long orderId;

//    @Temporal(TemporalType.TIMESTAMP)
//    @Column(name = "created_at", nullable = false, updatable = false)
//    @CreatedDate
    private LocalDateTime createdAt;

//    @Temporal(TemporalType.TIMESTAMP)
//    @Column(name = "updated_at", nullable = false)
//    @LastModifiedDate
    private LocalDateTime updatedAt;

    public double getPrice() {
        return price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getExchangeOrderId() {
        return exchangeOrderId;
    }

    public String getExchange() {
        return exchange;
    }

//    public Orders getOrders() {
//        return orders;
//    }

//    public void setOrderId(Long orderId) {
//        this.orderId = orderId;
//    }
//
//    public Long getOrderId() {
//        return orderId;
//    }

//    public void setOrders(Orders orders) {
//        this.orders = orders;
//    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }
}

