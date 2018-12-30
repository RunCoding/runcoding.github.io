package com.runcoding.controller.lottery;

import com.alibaba.fastjson.JSON;
import com.runcoding.dto.LotteryList;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class LotteryController {

    /**
     * https://github.com/MZCretin/RollToolsApi
     * @return
     */
    @RequestMapping(value = "/lottery",method = RequestMethod.GET)
    public String lottery(){

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<LotteryList> lotteryList = restTemplate.getForEntity("http://www.mxnzp.com/api/lottery/ssq/lottery_list?page=1", LotteryList.class);
        String jsonString = JSON.toJSONString(lotteryList);

        return jsonString;
    }


}
