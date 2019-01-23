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



}
