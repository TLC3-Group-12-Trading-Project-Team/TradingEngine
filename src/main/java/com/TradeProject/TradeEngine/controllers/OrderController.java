package com.TradeProject.TradeEngine.controllers;

import com.TradeProject.TradeEngine.services.OrderService;
import com.TradeProject.TradeEngine.services.PortfolioService;
import com.TradeProject.TradeEngine.services.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="order")
public class OrderController {

//    @Autowired
//    private SoapClient client;
//
//    private final OrderService orderService;
//    private  final PortfolioService portfolioService;
//
//    @Autowired
//    public OrderController(PortfolioService portfolioService, OrderService orderService){
//        this.portfolioService = portfolioService;
//        this.orderService = orderService;
//    }
//
//    @PostMapping("/getOrderStatus")
//    public ResponseData createValidatedOrders(@RequestBody OrderRequest request) {
//        ResponseData response = new ResponseData();
//        if(client.getOrderStatus(request).isIsOrderValid()){
//            if(portfolioService.getPortfolio((long) request.getPortfolioId())!=null){
//                response.setCode(HttpStatus.CREATED.value());
//                response.setStatus("Created Successfully");
//            }else{
//                response.setCode(HttpStatus.BAD_REQUEST.value());
//                response.setStatus("Portflio does not exist");
//            }
//        }else{
//            response.setName("Order is invalid");
//            response.setCode(HttpStatus.BAD_REQUEST.value());
//            response.setData(client.getOrderStatus(request));
//        }
//
//        return response;
//    }

}
