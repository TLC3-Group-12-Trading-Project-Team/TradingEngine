package com.TradeProject.TradeEngine.services;

import com.TradeProject.TradeEngine.Repository.OrderTransactionRepository;
import com.TradeProject.TradeEngine.models.OrderTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class OrderTransactionService {
    private OrderTransactionRepository orderTransactionRepository;

    ResponseData response = new ResponseData();
    @Autowired
    public OrderTransactionService(OrderTransactionRepository orderTransactionRepository) {
        this.orderTransactionRepository = orderTransactionRepository;
    }

    public ResponseData createOrderTransaction(OrderTransaction orderTransaction) {
//        Orders order = OrderRepository.findById();
        if(orderTransaction.getExchange().isEmpty())
            throw new IllegalMonitorStateException("Exchange cannot be empty");
        if(orderTransaction.getExchangeOrderId().isEmpty())
            throw new IllegalMonitorStateException("Order id cannot be empty");

        this.orderTransactionRepository.save(orderTransaction);
        response.setCode(HttpStatus.OK.value());
        response.setStatus("Created Successfully");
        HttpStatus.OK.value();

        return response;
    }
}
