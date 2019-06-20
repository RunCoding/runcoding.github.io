package com.runcoding.controller;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.runcoding.model.trade.TradeGeoPoint;
import com.runcoding.model.trade.Trade;
import com.runcoding.model.trade.order.OrderDetail;
import com.runcoding.model.trade.order.TradeOrder;
import com.runcoding.service.support.elastic.repositorys.TradeRepository;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
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

    @Autowired(required = false)
    private TradeRepository tradeRepository;

    private static ThreadLocalRandom random =   ThreadLocalRandom.current();

    @PostMapping("/init")
    @ApiOperation("初始化交易数据")
    public boolean initTrade(@RequestParam(value = "tradeTypeName",defaultValue = "订单交易")String tradeTypeName){
        List<Trade> trades = initTrades(tradeTypeName);
        tradeRepository.saveAll(trades);
        return true;
    }

    public static List<Trade> initTrades(@RequestParam(value = "tradeTypeName", defaultValue = "订单交易") String tradeTypeName) {
        /**交易地理位置*/
        List<TradeGeoPoint> tradeGeoPoints = Lists.newArrayList(
                TradeGeoPoint.builder().name("杭州").latitude(30.2756).longitude(120.197521).build(),
                TradeGeoPoint.builder().name("千岛湖").latitude(29.604433).longitude(119.005505).build(),
                TradeGeoPoint.builder().name("宁波").latitude(29.795295).longitude(121.614758).build(),
                TradeGeoPoint.builder().name("上海").latitude(31.233868).longitude(121.449963).build(),
                TradeGeoPoint.builder().name("南京").latitude(32.061557).longitude(118.758312).build()
        );

        /**初始化10笔交易*/
        return Stream.generate(()->random.nextLong(1000000,99999999))
                .limit(10).map((i)->tradeBuild(tradeTypeName,i, tradeGeoPoints.get(random.nextInt(tradeGeoPoints.size()))))
                .collect(Collectors.toList());
    }

    @PostMapping("/add")
    @ApiOperation("手动添加一笔交易数据到ES")
    public ResponseEntity<Trade> addTrade(@RequestBody Trade trade) {
        Trade savedTrade = tradeRepository.save(trade);
        return ResponseEntity.ok(savedTrade);
    }

    @GetMapping("/trades")
    @ApiOperation("查询所有的交易数据")
    public ResponseEntity<PageImpl<Trade>> trades() {
        PageImpl<Trade> tradeList = (PageImpl<Trade>) tradeRepository.findAll();
        return ResponseEntity.ok(tradeList);
    }

    @GetMapping("/{tradeId}")
    @ApiOperation("按交易id查询")
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
    @ApiOperation("按交易id和用户id查询查询")
    public ResponseEntity<Page<Trade>> getByTradeAndUserId(@PathVariable("tradeId") Long tradeId,
                                                           @PathVariable("userId") Long userId) {

        Pageable pageable =   PageRequest.of(0,10);
        Page<Trade> page = tradeRepository.findByTradeAndUserId(tradeId,userId,pageable);
        log.info("getByTradeAndUserId={}", JSON.toJSONString(page));

        return ResponseEntity.ok(page);
    }

    @DeleteMapping("/{tradeId}/delete")
    @ApiOperation("按交易id删除数据")
    public ResponseEntity<String> deleteTrade(@PathVariable("tradeId") Long  tradeId) {
        tradeRepository.deleteById(tradeId);
        return ResponseEntity.ok("Deleted");
    }

    @DeleteMapping("/clean")
    @ApiOperation("删除所有的es中的交易数据")
    public ResponseEntity<String> deleteTradeAll() {
        tradeRepository.deleteAll();
        return ResponseEntity.ok("Deleted");
    }

    public static Trade tradeBuild(String tradeTypeName,Long tradeId, TradeGeoPoint geo) {
        List<String> userNames = Lists.newArrayList("张三", "李四", "王五");
        String username = userNames.get(random.nextInt(userNames.size()));

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
        TradeOrder tradeOrder = TradeOrder.builder()
            .orderNumber(String.valueOf(random.nextLong(1000000,99999999)))
            .freightAmount(BigDecimal.valueOf(random.nextLong(200),2))
            .realAmount(BigDecimal.valueOf(random.nextLong(8),2))
            .promotionAmount(BigDecimal.valueOf(random.nextLong(6),2))
            .totalAmount(BigDecimal.valueOf(random.nextLong(100),2))
            .tradeNumber(String.valueOf(random.nextLong(1000000,99999999)))
            .orderStatus(0).orderDetails(orderDetails)
            .createTime(DateTime.now().toDate()).build();
        List<TradeOrder> tradeOrderOrders = Lists.newArrayList(tradeOrder);
        return Trade.builder().tradeId(tradeId).tradeName(tradeTypeName+tradeId)
                .tradeTypeId(1L).tradeStatus(0).userName(username)
                .totalRealAmount(BigDecimal.valueOf(random.nextLong(20000),2))
                .tradeOrders(tradeOrderOrders).userId(userId)
                .location(new GeoPoint(geo.getLatitude(),geo.getLongitude()))
                .createTime(DateTime.now().toDate()).build();
    }

}