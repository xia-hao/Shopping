package com.shopping.controller;

import com.shopping.domain.Discuss;
import com.shopping.domain.User;
import com.shopping.service.DiscussService;
import org.apache.shiro.SecurityUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Map;

/**
 * @PACKAGE_NAME: com.shopping.controller
 * @author: XIA
 * @NAME: DiscussController
 * @DATE: 2020/4/7
 **/
@RestController
@RequestMapping("/discuss")
public class DiscussController {
    @Resource
    private DiscussService discussService;

    @RequestMapping("/list")
    public Map list(Discuss discuss){
        return discussService.selectDiscussByUidANDPid(discuss);
    }

    @PostMapping("/addDiscuss")
    public Map addDiscuss(@RequestBody Discuss discuss){
        return discussService.addDiscuss(discuss);
    }
}
