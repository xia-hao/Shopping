package com.shopping.controller;

import com.shopping.domain.OrderInfo;
import com.shopping.domain.PayMent;
import com.shopping.service.PayMentService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @PACKAGE_NAME: com.shopping.controller
 * @author: XIA
 * @NAME: PayMentController
 * @DATE: 2020/4/22
 **/
@RestController
@RequestMapping("/payMent")
public class PayMentController {
    @Resource
    private PayMentService payMentService;

    @PostMapping("/pay")
    public Map pay(@RequestBody PayMent payMent){
        Map<String,Object> map = new HashMap<>();
        try {
            map = payMentService.pay(payMent);
        }catch (Exception e){
            map.put("retMsg",e.getMessage());
        }finally {
            return map;
        }
    }
}
