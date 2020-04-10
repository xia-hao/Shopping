package com.shopping.service.impl;

import com.shopping.domain.User;
import com.shopping.domain.UserAddress;
import com.shopping.mapper.UserAddressMapper;
import com.shopping.service.DistrictService;
import com.shopping.service.UserAddressService;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @PACKAGE_NAME: com.shopping.service.im
 *
 * pl
 * @author: XIA
 * @NAME: UserAddressServiceImpl
 * @DATE: 2020/4/5
 **/
@Service
public class UserAddressServiceImpl implements UserAddressService {
    @Resource
    private UserAddressMapper userAddressMapper;
    @Resource
    private DistrictService districtService;

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Map selectUserAddressByUid(Integer uid) {
        Map<String,Object> map = new HashMap<>();
        List<UserAddress> userAddressList = userAddressMapper.selectUserAddressByUid(uid);
        map.put("code","901");
        if(!userAddressList.isEmpty()){
            for (UserAddress userAddress : userAddressList){
                userAddress.setProvinceName(districtService.getDistrictById(userAddress.getProvinceid()).getDistrictName());
                userAddress.setCityName(districtService.getDistrictById(userAddress.getCityid()).getDistrictName());
                userAddress.setDistrictName(districtService.getDistrictById(userAddress.getDistrictid()).getDistrictName());
            }
            map.put("code","1000");
            map.put("userAddressList",userAddressList);
        }
        return map;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Map addUserAddress(UserAddress userAddress) {
        Map<String,Object> map = new HashMap<>();
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        userAddress.setUserid(user.getUserid());
        userAddress.setCreatetime(new Date());
        map.put("code","901");
        map.put("retMsg","新增收货地址失败！");
        if(userAddress.getIsdefault() == 1){                        // 如果是默认地址则把其余的地址设置为不默认
            updateUserAddressIsDefaultByUserid(user.getUserid());
        }
        if(userAddressMapper.insertSelective(userAddress)>0){
            map.put("code","1000");
            map.put("retMsg","新增收货地址成功！");
        }
        return map;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Map updateUserAddress(UserAddress userAddress) {
        Map<String,Object> map = new HashMap<>();
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        map.put("code","901");
        map.put("retMsg","编辑收货地址失败！");
        if(userAddress.getIsdefault() == 1){
            updateUserAddressIsDefaultByUserid(user.getUserid());
        }
        if(userAddressMapper.updateByPrimaryKeySelective(userAddress)>0){
            map.put("code","1000");
            map.put("retMsg","编辑收货地址成功！");
        }
        return map;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Map getUserAddressById(Integer id) {
        Map<String,Object> map = new HashMap<>();
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        UserAddress userAddress = userAddressMapper.selectByIdAndUserId(id,user.getUserid());
        map.put("code","901");
        if(null!=userAddress){
            userAddress.setProvinceName(districtService.getDistrictById(userAddress.getProvinceid()).getDistrictName());
            userAddress.setCityName(districtService.getDistrictById(userAddress.getCityid()).getDistrictName());
            userAddress.setDistrictName(districtService.getDistrictById(userAddress.getDistrictid()).getDistrictName());
            map.put("code","1000");
            map.put("userAddress",userAddress);
        }
        return map;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Map updateUserAddressIsDefault(Integer id) {
        Map<String,Object> map = new HashMap<>();
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        map.put("code","901");
        map.put("retMsg","设置失败！");
        updateUserAddressIsDefaultByUserid(user.getUserid());
        if(updateUserAddressIsDefaultById(id)>0){
            map.put("code","1000");
            map.put("retMsg","设置成功！");
        }
        return map;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public int updateUserAddressIsDefaultByUserid(Integer userid) {
        return userAddressMapper.updateUserAddressIsDefaultByUserid(userid);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public int updateUserAddressIsDefaultById(Integer id) {
        return userAddressMapper.updateUserAddressIsDefaultById(id);
    }
}
