package com.shopping.controller;

import com.shopping.domain.User;
import com.shopping.domain.Verify;
import com.shopping.service.UserService;
import com.shopping.service.VerifyService;
import com.shopping.utils.Constant;
import org.apache.shiro.SecurityUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;
    @Resource
    private VerifyService verifyService;

    @PostMapping("/userinfo")
    public Map userinfo() {
        Map<String, Object> map = new HashMap<>();
        map.put("user", (User) SecurityUtils.getSubject().getPrincipal());
        return map;
    }

    @PostMapping("/ifLoginNameExist")
    public Map ifLoginNameExist(@RequestBody String loginName) {
        Map<String, Object> map = new HashMap<>();
        User user = userService.getUserByLoginNameOrMobile(loginName);
        map.put("retCode", "906");
        if (null != user) {
            if (loginName.equals(user.getMobile())) {
                map.put("retMsg", "不能使用别人手机号当做账号！");
                return map;
            }
            if (loginName.equals(user.getLoginname())) {
                map.put("retMsg", "该用户账号已存在！");
                return map;
            }
        }
        map.put("retCode", "1000");
        return map;
    }

    @PostMapping("/ifMobileExist")
    public Map ifMobileExist(@RequestBody String mobile) {
        Map<String, Object> map = new HashMap<>();
        User user = userService.getUserByLoginNameOrMobile(mobile);
        map.put("retCode", "906");
        if (null != user) {
            if (mobile.equals(user.getMobile())) {
                map.put("retMsg", "该号码已被注册！");
                return map;
            }
        }
        map.put("retCode", "1000");
        return map;
    }

    @PostMapping("/findLoginNameOrPwd")
    public Map findLoginName(@RequestBody User user, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        map.put("retCode","906");
        Verify verify = new Verify();
        verify.setSessionid(request.getSession().getId());
        verify.setNotecode(user.getNoteCode());
        verify.setMobile(user.getMobile());
        if(null == verifyService.getVerifyByNoteCode(verify)){
            map.put("retMsg","短信验证码错误！");
            return map;
        }
        if(!user.getCode().equals(request.getSession().getAttribute("rightCode"))){
            map.put("retMsg","验证码错误！");
            return map;
        }
        map.put("url","/login");
        if(null != user.getLoginname()){
            if(userService.updateLoginNameByMobile(user.getLoginname(),user.getMobile())>0){
                Verify verify1 = new Verify();
                verify1.setStatu(Constant.VERIFY_STATU_END);
                verify1.setNotecode(user.getNoteCode());
                verify1.setSessionid(request.getSession().getId());
                verify1.setMobile(user.getMobile());
                verifyService.updateVerifyStatu(verify1);
                map.put("retCode", "1000");
                map.put("retMsg","找回账号成功！新的账号为："+user.getLoginname());
                return map;
            }
            map.put("retMsg","找回账号失败！");
        }
        if(null != user.getPassword()){
            if(userService.updateUserPwdByMobile(user.getMobile(),user.getPassword())>0){
                Verify verify1 = new Verify();
                verify1.setStatu(Constant.VERIFY_STATU_END);
                verify1.setNotecode(user.getNoteCode());
                verify1.setSessionid(request.getSession().getId());
                verify1.setMobile(user.getMobile());
                verifyService.updateVerifyStatu(verify1);
                map.put("retCode", "1000");
                map.put("retMsg","找回密码成功！新的密码为："+user.getPassword());
                return map;
            }
            map.put("retMsg","找回密码失败！");
        }
        return map;
    }
}
