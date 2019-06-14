package com.runcoding.controller;

import com.google.common.collect.Lists;
import com.runcoding.model.trade.Trade;
import org.elasticsearch.client.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ElasticController {

    @Autowired
    private ElasticsearchOperations elasticsearchOperations;

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;


    @GetMapping("/elastic/details")
    public ResponseEntity<Map<String, String>> getElasticInformation() {

        Client client = elasticsearchOperations.getClient();
        Map<String, String> asMap = new HashMap<>();
        client.settings().getAsGroups(true).forEach((groupName,settings)->{
            asMap.put(groupName, String.valueOf(settings.names()));
        });
        return ResponseEntity.ok(asMap);
    }

    @PutMapping("/elastic/clear-indices")
    public void clearIndices() {
        elasticsearchTemplate.deleteIndex(Trade.class);
        elasticsearchTemplate.createIndex(Trade.class);
        //elasticsearchTemplate.putMapping(Trade.class);
        List<IndexQuery> queries = Lists.newArrayList();
        List<Trade> initTrades = TradeController.initTrades("订单交易");

        elasticsearchTemplate.bulkIndex(queries);
        elasticsearchTemplate.refresh(Trade.class);
    }


}