package com.shopping.controller;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.shopping.domain.User;
import com.shopping.domain.Verify;
import com.shopping.service.UserService;
import com.shopping.service.VerifyService;
import com.shopping.utils.Constant;
import com.shopping.utils.utils.MD5Utils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @PACKAGE_NAME: com.shopping.controller
 * @author: XIA
 * @NAME: LoginController
 * @DATE: 2020/3/22
 **/
@CrossOrigin
@RestController
@RequestMapping("")
public class LoginController {
    @Autowired
    private UserService userService;
    @Autowired
    private DefaultKaptcha defaultKaptcha;
    @Resource
    private VerifyService verifyService;

    @GetMapping("/login")
    public Map login() {
        Map<String, String> map = new HashMap<>();
        map.put("status","notlogin");
        map.put("retMsg", "您还未进行登录，是否跳转至登录页面进行登录？");
        map.put("url", "/login");
        return map;
    }

    @PostMapping("/signIn")
    public Map doLogin(@RequestBody User user, HttpSession session) {
        Map<String, String> retMap = new HashMap();
        if (user.getCode().equals(session.getAttribute("rightCode"))) {
            User user1 = userService.getUserByLoginNameOrMobile(user.getLoginname());
            if(user1.getStatu().equals(Constant.USER_STATU_NORMAL)){
                retMap = userService.dologin(user);
                //如果登录成功,则将用户信息存入session
                if (retMap.get("retCode").equals("1000")) {
                    session.setAttribute("user", retMap.get("user"));
                }
            }else{
                retMap.put("retMsg","账号已被冻结，请在："+user1.getFreezelimit()+"秒后重试！");
            }

        }else{
            retMap.put("retCode","905");
            retMap.put("retMsg","验证码错误！");
        }
        return retMap;
    }

    @RequestMapping("/img")
    public void img(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        byte[] captchaChallengeAsJpeg = null;
        ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
        try {
            // 生产验证码字符串并保存到session中
            String createText = defaultKaptcha.createText();
            httpServletRequest.getSession().setAttribute("rightCode", createText);
            // 使用生产的验证码字符串返回一个BufferedImage对象并转为byte写入到byte数组中
            BufferedImage challenge = defaultKaptcha.createImage(createText);
            ImageIO.write(challenge, "jpg", jpegOutputStream);

            // 定义response输出类型为image/jpeg类型，使用response输出流输出图片的byte数组
            captchaChallengeAsJpeg = jpegOutputStream.toByteArray();
            httpServletResponse.setHeader("Cache-Control", "no-store");
            httpServletResponse.setHeader("Pragma", "no-cache");
            httpServletResponse.setDateHeader("Expires", 0);
            httpServletResponse.setContentType("image/jpeg");
            ServletOutputStream responseOutputStream = httpServletResponse.getOutputStream();
            responseOutputStream.write(captchaChallengeAsJpeg);
            responseOutputStream.flush();
            responseOutputStream.close();
        } catch (IllegalArgumentException e) {
            return;
        } catch (IOException e) {
            return;
        }

    }

    @RequestMapping("/register")
    public Map register(@RequestBody User user,HttpServletRequest request){
        Map<String,Object> map = new HashMap<>();
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
        user.setCreatetime(new Date());
        user.setPassword(MD5Utils.md5(user.getPassword()));
        map.put("retMsg","注册失败！");
        map.put("url","/home");
        if(userService.registerUser(user)>0){
            Verify verify1 = new Verify();
            verify1.setStatu(Constant.VERIFY_STATU_END);
            verify1.setNotecode(user.getNoteCode());
            verify1.setSessionid(request.getSession().getId());
            verify1.setMobile(user.getMobile());
            verifyService.updateVerifyStatu(verify1);
            map.put("retCode","1000");
            map.put("retMsg","注册成功！");
            map.put("url","/login");
        }
        return map;
    }

    @GetMapping("/loginOut")
    public void doLogin() {
        SecurityUtils.getSubject().logout();
    }
}
