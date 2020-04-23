package com.shopping.controller;

import com.shopping.domain.Refund;
import com.shopping.service.RefundService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @PACKAGE_NAME: com.shopping.controller
 * @author: XIA
 * @NAME: RefundController
 * @DATE: 2020/4/23
 **/
@RestController
@RequestMapping("/refund")
public class RefundController {
    @Resource
    private RefundService refundService;

    @PostMapping("/recede")
    public Map refund(@RequestBody Refund refund){
        Map map = new HashMap();
        try {
            map = refundService.refund(refund);
        } catch (Exception e) {
            map.put("retMsg", e.getMessage());
        } finally {
            return map;
        }
    }
}
