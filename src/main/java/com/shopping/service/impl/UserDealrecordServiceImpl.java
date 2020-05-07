package com.shopping.service.impl;

import com.github.pagehelper.PageHelper;
import com.shopping.domain.User;
import com.shopping.domain.UserDealrecord;
import com.shopping.mapper.UserDealrecordMapper;
import com.shopping.service.UserDealrecordService;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @PACKAGE_NAME: com.shopping.service.impl
 * @author: XIA
 * @NAME: UserDealrecordServiceImpl
 * @DATE: 2020/4/23
 **/
@Service
public class UserDealrecordServiceImpl implements UserDealrecordService {
    @Resource
    private UserDealrecordMapper userDealrecordMapper;

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public int adduserDealrecord(UserDealrecord userDealrecord) {
        return userDealrecordMapper.insert(userDealrecord);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Map selectUserDealrecord(UserDealrecord userDealrecord) {
        Map<String,Object> map = new HashMap<>();
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        map.put("code","901");
        PageHelper.startPage(userDealrecord.getPageNo(),userDealrecord.getPageSize());
        List<UserDealrecord> userDealrecordList = userDealrecordMapper.selectUserDealrecord(user.getUserid());
        if(!userDealrecordList.isEmpty()){
            map.put("code","1000");
            map.put("userDealrecordList",userDealrecordList);
            map.put("userDealrecordCount",userDealrecordMapper.getUserDealrecordCount(user.getUserid()));
        }
        return map;
    }
}
