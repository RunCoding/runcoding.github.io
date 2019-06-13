package com.runcoding.controller;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.runcoding.model.trade.GeoPoint;
import com.runcoding.model.trade.Trade;
import com.runcoding.model.trade.order.OrderDetail;
import com.runcoding.model.trade.order.TradeOrder;
import com.runcoding.service.support.elastic.repositorys.TradeRepository;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author runcoding
 */
@Slf4j
@RestController
@RequestMapping(value = "/trade")
public class TradeController {


    @Autowired
    private TradeRepository tradeRepository;

    private static ThreadLocalRandom random =   ThreadLocalRandom.current();

    @PostMapping("/add")
    public ResponseEntity<Trade> addTrade(@RequestBody Trade trade) {
        Trade savedTrade = tradeRepository.save(trade);
        return ResponseEntity.ok(savedTrade);
    }

    @DeleteMapping("/{tradeId}/delete")
    public ResponseEntity<String> deleteTrade(@PathVariable("tradeId") Long  tradeId) {
        tradeRepository.deleteById(tradeId);
        return ResponseEntity.ok("Deleted");
    }

    @DeleteMapping("/clean")
    public ResponseEntity<String> deleteTradeAll() {
        tradeRepository.deleteAll();
        return ResponseEntity.ok("Deleted");
    }

    @GetMapping("/{tradeId}")
    public ResponseEntity<Trade> getByTradeId(@PathVariable("tradeId") Long  tradeId) {
        Trade trade = tradeRepository.findById(tradeId).orElse(null);
        log.info("findById={}", JSON.toJSONString(trade));

        trade = tradeRepository.findByTradeId(tradeId);
        log.info("findByTradeId={}", JSON.toJSONString(trade));

        Pageable pageable =   PageRequest.of(0,10);
        Page<Trade> page = tradeRepository.findByTradeId2(tradeId,pageable);
        log.info("findByTradeId2={}", JSON.toJSONString(page));
        
        trade = tradeRepository.findByTradeIdAndTradeTypeId(tradeId,1);
        log.info("findByTradeIdAndTradeTypeId={}", JSON.toJSONString(trade));

        List<Trade> tradeList = tradeRepository.findByTradeNameLike("交易");
        log.info("findByTradeIdLike={}", JSON.toJSONString(tradeList));

        return ResponseEntity.ok(trade);
    }

    @GetMapping("/{tradeId}/{userId}")
    public ResponseEntity<Page<Trade>> getByTradeAndUserId(@PathVariable("tradeId") Long  tradeId,
                                                           @PathVariable("userId") Long userId) {

        Pageable pageable =   PageRequest.of(0,10);
        Page<Trade> page = tradeRepository.findByTradeAndUserId(tradeId,userId,pageable);
        log.info("getByTradeAndUserId={}", JSON.toJSONString(page));

        return ResponseEntity.ok(page);
    }

    @GetMapping("/trades")
    public ResponseEntity<PageImpl<Trade>> trades() {
        PageImpl<Trade> tradeList = (PageImpl<Trade>) tradeRepository.findAll();
        return ResponseEntity.ok(tradeList);
    }


    @PostMapping("/init")
    public boolean initTrade(){
        /**交易地理位置*/
        List<GeoPoint> geoPoints = Lists.newArrayList(
            GeoPoint.builder().name("杭州").latitude(30.2756).longitude(120.197521).build(),
            GeoPoint.builder().name("千岛湖").latitude(29.604433).longitude(119.005505).build(),
            GeoPoint.builder().name("宁波").latitude(29.795295).longitude(121.614758).build(),
            GeoPoint.builder().name("上海").latitude(31.233868).longitude(121.449963).build(),
            GeoPoint.builder().name("南京").latitude(32.061557).longitude(118.758312).build()
        );
        /**初始化10笔交易*/
        List<Trade> trades =  Stream.generate(()->random.nextLong(1000000,99999999))
                .limit(10).map((i)->tradeBuild(i,geoPoints.get(random.nextInt(geoPoints.size()))))
                .collect(Collectors.toList());
        tradeRepository.saveAll(trades);
        return true;
    }


    public static Trade tradeBuild(Long tradeId, GeoPoint location) {
        Long userId = random.nextLong(100,105);
        OrderDetail orderDetail = OrderDetail.builder()
              .orderNumber(String.valueOf(random.nextLong(1000000,99999999)))
              .freightAmount(BigDecimal.valueOf(random.nextLong(200),2))
              .productSalePrice(BigDecimal.valueOf(random.nextLong(30),2))
              .productSkuCode("sku-"+random.nextLong(3))
              .productSkuId(random.nextLong(5))
              .productSkuName("商品-"+random.nextLong(200))
              .promotionAmount(BigDecimal.valueOf(random.nextLong(30),2))
              .realAmount(BigDecimal.valueOf(random.nextLong(200),2))
              .qty(random.nextInt(10))
              .build();
        List<OrderDetail> orderDetails = Lists.newArrayList(orderDetail);
        TradeOrder tradeOrder = TradeOrder.builder().userId(userId)
            .orderNumber(String.valueOf(random.nextLong(1000000,99999999)))
            .freightAmount(BigDecimal.valueOf(random.nextLong(200),2))
            .realAmount(BigDecimal.valueOf(random.nextLong(8),2))
            .promotionAmount(BigDecimal.valueOf(random.nextLong(6),2))
            .totalAmount(BigDecimal.valueOf(random.nextLong(100),2))
            .tradeNumber(String.valueOf(random.nextLong(1000000,99999999)))
            .orderStatus(0).orderDetails(orderDetails)
            .createTime(DateTime.now().toDate()).build();
        List<TradeOrder> tradeOrderOrders = Lists.newArrayList(tradeOrder);

        return Trade.builder().tradeId(tradeId).tradeName(location.getName()+"交易"+tradeId)
                .tradeTypeId(1L).tradeStatus(0)
                .totalRealAmount(BigDecimal.valueOf(random.nextLong(20000),2))
                .tradeOrders(tradeOrderOrders).userId(userId).location(location)
                .createTime(DateTime.now().toDate()).build();
    }
}