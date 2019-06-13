package com.runcoding.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.runcoding.model.trade.Trade;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.data.elasticsearch.core.query.StringQuery;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.elasticsearch.index.query.QueryBuilders.matchAllQuery;

/**
 * @author runcoding
 * [Elasticsearch 权威指南（中文版）](https://es.xiaoleilu.com/index.html)
 * https://n3xtchen.github.io/n3xtchen/elasticsearch/2017/07/05/elasticsearch-23-useful-query-example
 *
 *
 */
@Slf4j
@RestController
@RequestMapping(value = "/trade/query")
public class TradeQueryController {

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;


    @GetMapping("/queryUserIdAndProductSkuId")
    @ApiOperation("精确匹配用户下sku查询交易")
    public ResponseEntity<Page<Trade>> queryUserIdAndProductSkuId(@RequestParam(value = "userId",defaultValue = "33661")String userId,
                                                 @RequestParam(value = "productSkuId",defaultValue = "4")String productSkuId,
                                                 @RequestParam(value = "tradeTypeId",defaultValue = "1")String tradeTypeId,
                                                 @RequestParam(value = "tradeStatus",defaultValue = "0")String tradeStatus){
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery()
                .must(new QueryStringQueryBuilder(tradeStatus).field("tradeStatus"))
                .must(new QueryStringQueryBuilder(tradeTypeId).field("tradeTypeId"))
                .must(new QueryStringQueryBuilder(productSkuId).field("tradeOrders.orderDetails.productSkuId"))
                .must(new QueryStringQueryBuilder(userId).field("userId"));

        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withFilter(boolQuery).withQuery(matchAllQuery())
                .withSort(new FieldSortBuilder("tradeId").order(SortOrder.ASC))
                .build();

        long count = elasticsearchTemplate.count(searchQuery);
        log.info("用户和sku查询交易数量={}",count);

        Page<Trade> page = elasticsearchTemplate.queryForPage(searchQuery, Trade.class);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/match/tradeName")
    @ApiOperation("模糊查询交易")
    public ResponseEntity<Page<Trade>> matchQueryByTradeName(@RequestParam(value = "tradeName",defaultValue = "千岛湖交易")String tradeName){
        Pageable pageable =   PageRequest.of(0,10);
        Sort sort = Sort.by(new Sort.Order(Sort.Direction.ASC, "tradeId"));

        /**
         * "match"分词查询
         * 比如"千岛湖交易1200"会被分词为"千岛湖 交易 1200"那么所有包含这三个词中的一个或多个的文档就会被搜索出来。
         * */
        String matchQuery = QueryBuilders.matchQuery("tradeName", tradeName).toString();

        Page<Trade> page = elasticsearchTemplate.queryForPage(new StringQuery(matchQuery, pageable,sort), Trade.class);
        log.info("match模糊查询(分词):="+ JSON.toJSONString(page, SerializerFeature.PrettyFormat));

        matchQuery = QueryBuilders.matchPhraseQuery("tradeName", tradeName).toString();
        page = elasticsearchTemplate.queryForPage(new StringQuery(matchQuery, pageable,sort), Trade.class);
        log.info("matchPhrase(短语)查询:="+ JSON.toJSONString(page, SerializerFeature.PrettyFormat));

        return ResponseEntity.ok(page);
    }

}