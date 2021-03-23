package com.TradeProject.TradeEngine.controllers;

import com.TradeProject.TradeEngine.models.Portfolio;
import com.TradeProject.TradeEngine.services.PortfolioService;
import com.TradeProject.TradeEngine.services.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "portfolio")
public class PortfolioController {
    private final PortfolioService portfolioService;

    @Autowired
    public PortfolioController(PortfolioService portfolioService){
        this.portfolioService = portfolioService;
    }

    @GetMapping(path = "{clientId}")
    public List<Portfolio> getPortfolio(@PathVariable("clientId") Long clientId){
        return this.portfolioService.getAllPortfolio(clientId);
    }

    @PostMapping(path = "/create")
    public ResponseData registerNewPortfolio(@RequestBody Portfolio portfolio){
        return this.portfolioService.addPortfolio(portfolio);
    }

    @DeleteMapping(path = "{id}")
    public ResponseData remove(@PathVariable("id") Long id){
        return this.portfolioService.removePortfolio(id);
    }

//    @PutMapping(path = "{id}")
//    public Response update(@PathVariable("id") Long id){
//        return this.portfolioService.updatePortfolio(id);
//    }
}

