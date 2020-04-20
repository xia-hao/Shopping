package com.shopping.service.impl;

import com.shopping.domain.User;
import com.shopping.mapper.UserMapper;
import com.shopping.service.UserService;
import com.shopping.utils.Constant;
import com.shopping.utils.utils.MD5Utils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    public UserMapper userMapper;

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Map dologin(User user) {
        Map map = new HashMap();
        map.put("retCode", "1000");
        map.put("retMsg", "登录成功");
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(new UsernamePasswordToken(user.getLoginname(), MD5Utils.md5(user.getPassword())));
            map.put("user", subject.getPrincipal());
            userMapper.updateUserPwdErrorCount(user.getLoginname());
        } catch (UnknownAccountException un) {
            map.put("retCode", "901");
            map.put("retMsg", "用户不存在");
        } catch (IncorrectCredentialsException in) {
            userMapper.updateUserPwdErrorCountByLoginNameOrMobile(user.getLoginname());
            User user1 = getUserByLoginNameOrMobile(user.getLoginname());
            map.put("retCode", "901");
            if (user1.getPwderrorcount() <= 0) {
                User user2 = new User();
                user2.setFreezingtime(new Date());
                user2.setUserid(user1.getUserid());
                user2.setStatu(Constant.USER_STATU_FREEZE);
                if (userMapper.updateUserStatu(user2) > 0) {
                    map.put("retMsg", "账号已被冻结，请在：" + user1.getFreezelimit() + "秒后重试！");
                }
            } else {
                map.put("retMsg", "密码错误，还剩：" + user1.getPwderrorcount() + "次机会！");
            }
        }
        return map;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public User getUserByLoginNameOrMobile(String loginname) {
        return userMapper.getUserByLoginNameOrMobile(loginname);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public User getUserById(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public int registerUser(User user) {
        return userMapper.insertSelective(user);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public int updateUserPwdByMobile(String mobile, String password) {
        return userMapper.updateUserPwdByMobile(mobile,MD5Utils.md5(password));
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public int updateUserFreezing() {
        return userMapper.updateUserFreezing();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public int updateLoginNameByMobile(String loginname, String mobile) {
        return userMapper.updateLoginNameByMobile(loginname,mobile);
    }
}
