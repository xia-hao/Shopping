package com.shopping.controller;

import com.shopping.domain.User;
import com.shopping.domain.UserAddress;
import com.shopping.service.DistrictService;
import com.shopping.service.UserAddressService;
import org.apache.shiro.SecurityUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @PACKAGE_NAME: com.shopping.controller
 * @author: XIA
 * @NAME: UserAddressController
 * @DATE: 2020/4/5
 **/

@CrossOrigin
@RestController
@RequestMapping("/userAddress")
public class UserAddressController {
    @Resource
    private UserAddressService userAddressService;

    @GetMapping("/list")
    public Map list(){
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        return userAddressService.selectUserAddressByUid(user.getUserid());
    }
    @GetMapping("/getUserAddress/{id}")
    public Map getUserAddress(@PathVariable Integer id){
        return userAddressService.getUserAddressById(id);
    }
    @PostMapping("/addUserAddress")
    public Map addUserAddress(@RequestBody UserAddress userAddress){
        return userAddressService.addUserAddress(userAddress);
    }
    @PostMapping("/updateUserAddress")
    public Map updateUserAddress(@RequestBody UserAddress userAddress){
        return userAddressService.updateUserAddress(userAddress);
    }
    @GetMapping("/updateUserAddressIsDefault/{id}")
    public Map updateUserAddressIsDefault(@PathVariable Integer id){
        return userAddressService.updateUserAddressIsDefault(id);
    }
    @GetMapping("/delUserAddress/{id}")
    public Map delUserAddress(@PathVariable Integer id){
        return userAddressService.delUserAddress(id);
    }
}
