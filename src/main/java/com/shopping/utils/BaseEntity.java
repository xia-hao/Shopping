package com.shopping.utils;

import com.shopping.domain.User;
import com.shopping.service.UserService;
import com.shopping.utils.shiro.UserRealm;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @PACKAGE_NAME: com.shopping.utils
 * @author: XIA
 * @NAME: BaseEntity
 * @DATE: 2020/3/15
 **/
public class BaseEntity {
    private Integer pageNo=1;       //当前页
    private Integer pageSize=4;     //每页显示条数
    private Integer categoryId;     //分类Id
    private Float price1;           //商品列表中价格区间1
    private Float price2;           //商品列表中价格区间2
    private String code;            //验证码
    private String noteCode;        //短信验证码
    private String provinceName;   //省份名称
    private String cityName;       //城市名称
    private String districtName;   //地区名称

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public String getNoteCode() {
        return noteCode;
    }

    public void setNoteCode(String noteCode) {
        this.noteCode = noteCode;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Float getPrice1() {
        return price1;
    }

    public void setPrice1(Float price1) {
        this.price1 = price1;
    }

    public Float getPrice2() {
        return price2;
    }

    public void setPrice2(Float price2) {
        this.price2 = price2;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }



}
