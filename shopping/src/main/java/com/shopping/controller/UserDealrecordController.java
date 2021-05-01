package com.shopping.controller;

import com.shopping.domain.UserDealrecord;
import com.shopping.service.UserDealrecordService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @PACKAGE_NAME: com.shopping.controller
 * @author: XIA
 * @NAME: UserDealrecordController
 * @DATE: 2020/4/28
 **/
@RestController
@RequestMapping("/userDealrecord")
public class UserDealrecordController {
    @Resource
    private UserDealrecordService userDealrecordService;

    @GetMapping("/list")
    public Map list(UserDealrecord userDealrecord){
        return userDealrecordService.selectUserDealrecord(userDealrecord);
    }
}
