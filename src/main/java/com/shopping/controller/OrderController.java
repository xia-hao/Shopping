package com.shopping.controller;

import com.shopping.domain.OrderInfo;
import com.shopping.service.OrderService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @PACKAGE_NAME: com.shopping.controller
 * @author: XIA
 * @NAME: OrderController
 * @DATE: 2020/4/19
 **/
@RestController
@RequestMapping("/order")
public class OrderController {
    @Resource
    private OrderService orderService;

    @PostMapping("/add")
    public Map add(@RequestBody OrderInfo orderInfo) {
        Map map = new HashMap();
        try {
            map = orderService.addOrder(orderInfo);
        } catch (Exception e) {
            map.put("retMsg", e.getMessage());
        } finally {
            return map;
        }
    }

    @GetMapping("/ByOrderNo")
    public Map ByOrderNo(@RequestParam String orderNo) {
        return orderService.getOrderByOrderNo(orderNo);
    }

    @GetMapping("/list")
    public Map list(OrderInfo orderInfo) {
        return orderService.selectOrderByUidAndStatu(orderInfo);
    }

    @GetMapping("/del/{orderno}")
    public Map del(@PathVariable String orderno) {
        Map map = new HashMap();
        try {
            map = orderService.delOrderByOrderNo(orderno);
        } catch (Exception e) {
            map.put("retMsg", e.getMessage());
        } finally {
            return map;
        }
    }

    @PostMapping("/updateOrder")
    public Map updateOrder(@RequestBody OrderInfo orderInfo) {
        Map map = new HashMap();
        map.put("code", "901");
        if(orderService.updateOrder(orderInfo)>0){
            map.put("code", "1000");
        }
        return map;
    }

    @PostMapping("/refund")
    public Map refund(@RequestBody OrderInfo orderInfo) {
        Map map = new HashMap();
        map.put("code", "901");
        if(orderService.updateOrder(orderInfo)>0){
            map.put("code", "1000");
        }
        return map;
    }
}
