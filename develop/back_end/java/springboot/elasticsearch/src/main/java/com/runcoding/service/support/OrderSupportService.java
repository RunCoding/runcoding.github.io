package com.runcoding.service.support;

import com.runcoding.model.trade.Trade;
import com.runcoding.service.support.elastic.repositorys.TradeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: xukai
 * @email: runcoding@163.com
 * @created Time: 2018/1/2 17:32
 * @description 订单业务支持
 * Copyright (C), 2017-2018,
 **/
@Service
public class OrderSupportService {

    private Logger  logger = LoggerFactory.getLogger(OrderSupportService.class);

    @Autowired
    private TradeRepository tradeRepository;

    public Trade addTrade(Trade trade) {
        return tradeRepository.save(trade);
    }

    public void deleteTrade(String tradeId) {
        tradeRepository.delete(tradeId);
    }

    public Trade getByTradeId(String tradeId) {
        Trade trade = tradeRepository.findOne(tradeId);
        return trade;
    }
}
