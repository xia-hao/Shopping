package com.shopping.service.impl;

import com.github.pagehelper.PageHelper;
import com.shopping.domain.Collectible;
import com.shopping.domain.User;
import com.shopping.mapper.CollectibleMapper;
import com.shopping.service.CollectibleService;
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
 * @NAME: CollectibleServiceImpl
 * @DATE: 2020/4/24
 **/
@Service
public class CollectibleServiceImpl implements CollectibleService {
    @Resource
    private CollectibleMapper collectibleMapper;

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Map addCollectible(Collectible collectible) {
        Map<String,Object> map = new HashMap<>();
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        collectible.setUserid(user.getUserid());
        collectible.setLoginname(user.getLoginname());
        collectible.setCreatetime(new Date());
        map.put("code","901");
        map.put("retMsg","收藏失败！");
        if(collectibleMapper.insert(collectible)>0){
            map.put("code","1000");
            map.put("retMsg","收藏成功！");
        }
        return map;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Map delCollectible(Integer productid) {
        Map<String,Object> map = new HashMap<>();
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        map.put("code","901");
        map.put("retMsg","取消收藏失败！");
        if(collectibleMapper.delCollectibleByUidAndPid(user.getUserid(),productid)>0){
            map.put("code","1000");
            map.put("retMsg","已取消收藏！");
        }
        return map;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Map getCollectible(Integer productid) {
        Map<String,Object> map = new HashMap<>();
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        map.put("code","901");
        if(null != user){
            Collectible collectible = collectibleMapper.getCollectibleByPid(user.getUserid(),productid);
            if(null != collectible){
                map.put("code","1000");
                map.put("collectible",collectible);
            }
        }
        return map;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Map selectCollectible(Collectible collectible) {
        Map<String,Object> map = new HashMap<>();
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        map.put("code","901");
        PageHelper.startPage(collectible.getPageNo(),collectible.getPageSize());
        List<Collectible> collectibleList = collectibleMapper.selectCollectibleByUid(user.getUserid());
        if(!collectibleList.isEmpty()){
            map.put("code","1000");
            map.put("collectibleList",collectibleList);
            map.put("collectibleCount",collectibleMapper.getCollectibleCount(user.getUserid()));
        }
        return map;
    }
}
