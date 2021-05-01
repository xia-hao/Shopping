package com.shopping.controller;


import com.shopping.domain.Verify;
import com.shopping.service.VerifyService;
import com.shopping.utils.Constant;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @PACKAGE_NAME: com.shopping.controller
 * @author: XIA
 * @NAME: VerifyController
 * @DATE: 2020/3/23
 **/
@RestController
@RequestMapping("/verify")
public class VerifyController {
    @Resource
    private VerifyService verifyService;

    @GetMapping("/add")
    public ModelAndView add(Verify verify, HttpServletRequest request, ModelAndView modelAndView) {
        int num = (int) (Math.random() * (999999 - 100000 + 1) + 100000);
        Date now = new Date();
        verify.setSessionid(request.getSession().getId());
        verify.setNotecode(Integer.toString(num));
        verify.setStatu(Constant.VERIFY_STATU_NOT);
        verify.setCreatetime(now);
        verifyService.addVerify(verify);
        request.setAttribute("NoteCode", verify.getNotecode());
        request.setAttribute("mobile",verify.getMobile());
        modelAndView.setViewName("/verify/info");
        return modelAndView;
    }

    @RequestMapping("/info")
    public Map info(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        Verify verify = new Verify();
        verify.setSessionid(request.getSession().getId());
        verify.setNotecode(request.getAttribute("NoteCode").toString());
        verify.setMobile(request.getAttribute("mobile").toString());
        Verify getVerify = verifyService.getVerifyByNoteCode(verify);
        map.put("verify", getVerify);
        map.put("retCode", "1000");
        if (null == getVerify) {
            map.put("retCode", "500");
        }
        return map;
    }
}
