package com.runcoding.service.support.elastic.repositorys;


import com.runcoding.model.trade.Trade;
import com.runcoding.model.trade.order.TradeOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Created by xukai on 2017/6/9.
 * 处理的域对象是OrderPo，其主键类型是Long(id的类型)
 * @see  //es.yemengying.com/4/4.4/4.4.1.html
 */
@Repository
public interface TradeRepository extends ElasticsearchRepository<Trade, String> {

    /**通过tradeId查询*/
    List<Trade> findByTradeId(String tradeId);

    /**通过活动名称查询*/
    @Query("{\"bool\":{\"must\":{\"query_string\":{\"query\":\"?0\",\"fields\":[\"name\"],\"default_operator\":\"and\"}}}}")
    List<Trade> findByCaseName(String caseName);

    /**组合查询：通过活动名称和活动编号查询*/
    @Query("{\"filtered\":{\"query\":{\"bool\":{\"should\":[{\"match_phrase\":{\"name\":{\"query\":\"?0\",\"slop\":1}}}]}},\"filter\":{\"bool\":{\"should\":[{\"term\":{\"caseCode\":\"?1\"}}]}}}}")
    Page<Trade> queryByCaseInfo(String caseName, String caseCode, Pageable pageable);

}
