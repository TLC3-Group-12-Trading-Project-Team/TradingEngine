package com.TradeProject.TradeEngine.controllers;

import com.TradeProject.TradeEngine.Exceptions.ResourceNotFoundException;
import com.TradeProject.TradeEngine.Repository.OrderTransactionRepository;
import com.TradeProject.TradeEngine.dto.Orders;
import com.TradeProject.TradeEngine.models.OrderTransaction;
import com.TradeProject.TradeEngine.services.OrderService;
import com.TradeProject.TradeEngine.services.OrderTransactionService;
import com.TradeProject.TradeEngine.services.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;


@RestController
public class OrderTransactionController {

    @Autowired
    OrderService orderService;

    @Autowired
    OrderTransactionRepository orderTransactionRepository;

//    @Autowired
//    OrderRepository orderRepository;

    public OrderTransactionController(OrderService orderService){
        this.orderService = orderService;
    }
    @PostMapping("/orders/{orderId}/transactions")
    public OrderTransaction createOrderTransaction(@PathVariable(value = "orderId") Long orderId,
                                                @RequestBody OrderTransaction orderTransaction){
//        return this.orderTransactionService.createOrderTransaction(orderTransaction);
        Orders order = this.orderService.getOrder(orderId);
//        orderTransaction.setOrders(order);
        orderTransaction.setCreatedAt(LocalDateTime.now());
        return  orderTransactionRepository.save(orderTransaction);
//        return orderRepository.findById(orderId).map(orders -> {
//            orderTransaction.setOrders(orders);
//            orderTransaction.setCreatedAt(LocalDateTime.now());
//            return orderTransactionRepository.save(orderTransaction);
//        }).orElseThrow(()-> new ResourceNotFoundException("orderId " + orderId + " not found"));
    }
}
