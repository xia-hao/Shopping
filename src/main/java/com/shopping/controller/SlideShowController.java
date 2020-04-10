package com.shopping.controller;

import com.shopping.service.SlideShowService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @PACKAGE_NAME: com.shopping.controller
 * @author: XIA
 * @NAME: SlideShowController
 * @DATE: 2020/3/28
 **/
@RestController
@RequestMapping("/slideShow")
public class SlideShowController {
    @Resource
    private SlideShowService slideShowService;

    @GetMapping("/list")
    public Map slideShow(Integer pid ,String type){
        Map<String,Object> map = new HashMap<>();
        map.put("slideShowList",slideShowService.getBySlideShowByProductidANDType(pid,type));
        return map;
    }
}
