package com.shopping.controller;

import com.shopping.domain.OrderInfo;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @RequestMapping("/add")
    public Map add(@RequestBody OrderInfo orderInfo){
        Map<String,Object> map = new HashMap<>();
        return map;
    }
}
