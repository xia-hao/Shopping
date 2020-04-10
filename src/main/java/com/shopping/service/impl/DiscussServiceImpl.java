package com.shopping.service.impl;

import com.github.pagehelper.PageHelper;
import com.shopping.domain.Discuss;
import com.shopping.domain.User;
import com.shopping.mapper.DiscussMapper;
import com.shopping.service.DiscussService;
import com.shopping.service.UserService;
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
 * @PACKAGE_NAME: com.shopping.service.impl
 * @author: XIA
 * @NAME: DiscussServiceImpl
 * @DATE: 2020/4/7
 **/
@Service
public class DiscussServiceImpl implements DiscussService {
    @Resource
    private DiscussMapper discussMapper;
    @Resource
    private UserService userService;

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Map selectDiscussByUidANDPid(Discuss discuss) {
        Map<String,Object> map = new HashMap<>();
        PageHelper.startPage(discuss.getPageNo(),discuss.getPageSize());
        List<Discuss> discussList = discussMapper.selectDiscussByPid(discuss.getProductid());
        int discussCount = discussMapper.getDiscussCountByPid(discuss.getProductid());
        map.put("code","901");
        if (!discussList.isEmpty()){
            for (Discuss dis : discussList){
                User user = userService.getUserById(dis.getUserid());
                dis.setLoginname(user.getLoginname());
                dis.setUsername(user.getUsername());
            }
            map.put("code","1000");
            map.put("discussList",discussList);
            map.put("discussCount",discussCount);
        }
        return map;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Map addDiscuss(Discuss discuss) {
        Map<String,Object> map = new HashMap<>();
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        map.put("code","901");
        map.put("retMsg","评论发布失败！");
        discuss.setUserid(user.getUserid());
        discuss.setCreatetime(new Date());
        if(discussMapper.insertSelective(discuss)>0){
            map.put("code","1000");
            map.put("retMsg","评论发布成功！");
        }
        return map;
    }
}
