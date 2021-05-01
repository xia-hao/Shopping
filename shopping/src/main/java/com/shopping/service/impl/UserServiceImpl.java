package com.shopping.service.impl;

import com.shopping.domain.User;
import com.shopping.domain.Verify;
import com.shopping.mapper.UserMapper;
import com.shopping.service.UserService;
import com.shopping.service.VerifyService;
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
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.math.BigDecimal;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Map dologin(User user) {
        Map<String,Object> map = new HashMap();
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
    public int userConsume(Integer userid, BigDecimal balance) {
        return userMapper.userConsume(userid,balance);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Map update(User user) {
        Map<String,Object> map = new HashMap();
        map.put("code", "901");
        if(userMapper.updateByPrimaryKeySelective(user)>0){
            map.put("code", "1000");
        }
        return map;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Map updateUserImage(MultipartFile multipartFile) {
        Map<String, Object> map = new HashMap<>();
        map.put("code","901");
        map.put("retMsg","更改头像失败！");
        URL url = ClassLoader.getSystemClassLoader().getResource("./");
        String path = url.getPath().substring(0,url.getPath().indexOf("shopping"))
                +File.separator+"shopping-ui"+File.separator+"static"+File.separator+"images"; // 图片存放路径
        if ( null != multipartFile && !multipartFile.isEmpty()) {
            String saveFileName = multipartFile.getOriginalFilename(); //获取原文件名
            File saveFile =new File(path,saveFileName);
            if (!saveFile.getParentFile().exists()) { // 判断父级目录是否存在
                saveFile.getParentFile().mkdirs(); //不存在则创建
            }
            try {
                User user = new User();
                user.setUserid(((User)SecurityUtils.getSubject().getPrincipal()).getUserid());
                user.setImg(saveFileName);
                if(userMapper.updateByPrimaryKeySelective(user)>0){
//                  multipartFile.transferTo(saveFile);
                    BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(saveFile));
                    out.write(multipartFile.getBytes());
                    out.flush();
                    out.close();
                    map.put("code","1000");
                    map.put("retMsg","更改头像成功！");
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                map.put("retMsg",e.getMessage());
            } catch (IOException e) {
                e.printStackTrace();
                map.put("retMsg",e.getMessage());
            }
        } else {
            map.put("retMsg","上传失败，因为文件为空！");
        }
        return map;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Map updateMobile(User user) {
        Map<String,Object> map = new HashMap();
        map.put("code", "901");
        user.setUserid(((User)SecurityUtils.getSubject().getPrincipal()).getUserid());
        user.setMobile(user.getNewMobile());
        if(userMapper.updateMobile(user)>0){
            map.put("code", "1000");
            SecurityUtils.getSubject().logout();
        }
        return map;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Map updatePwd(User user) {
        Map<String,Object> map = new HashMap();
        map.put("code", "901");
        user.setUserid(((User)SecurityUtils.getSubject().getPrincipal()).getUserid());
        user.setPassword(MD5Utils.md5(user.getPassword()));
        if(userMapper.updatePwd(user)>0){
            map.put("code", "1000");
            SecurityUtils.getSubject().logout();
        }
        return map;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public int userIncome(Integer userid, BigDecimal balance) {
        return userMapper.userIncome(userid,balance);
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
