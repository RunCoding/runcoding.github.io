package com.runcoding.controller;

import com.runcoding.model.trade.Trade;
import com.runcoding.service.support.OrderSupportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author maidao
 */
@RestController
@RequestMapping(value = "/trade")
public class TradeController {

    @Autowired
    private OrderSupportService orderSupportService;


    @PostMapping("/add")
    public ResponseEntity<Trade> addTrade(@RequestBody Trade trade) {
        Trade savedTrade = orderSupportService.addTrade(trade);
        return ResponseEntity.ok(savedTrade);
    }

    @DeleteMapping("/{tradeId}/delete")
    public ResponseEntity<String> deleteTrade(@PathVariable("tradeId") String tradeId) {
        orderSupportService.deleteTrade(tradeId);
        return ResponseEntity.ok("Deleted");
    }

    @GetMapping("/{tradeId}")
    public ResponseEntity<Trade> getByTradeId(@PathVariable("tradeId") String tradeId) {
        Trade trade = orderSupportService.getByTradeId(tradeId);
        return ResponseEntity.ok(trade);
    }
}