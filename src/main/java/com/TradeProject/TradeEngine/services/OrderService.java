package com.TradeProject.TradeEngine.services;

import com.TradeProject.TradeEngine.Repository.OrderRepository;
import com.TradeProject.TradeEngine.models.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository){
        this.orderRepository = orderRepository;
    }

    public void createOrders(Orders orders){
        this.orderRepository.save(orders);
    }
}