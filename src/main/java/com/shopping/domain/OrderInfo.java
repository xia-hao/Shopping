package com.shopping.domain;

import com.shopping.utils.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;

public class OrderInfo extends BaseEntity {
    private String orderno;

    private Integer userid;

    private String loginname;

    private Integer userAddressId;

    private String distribution;

    private String payway;

    private BigDecimal orderprice;

    private String orderstatu;

    public String getOrderno() {
        return orderno;
    }

    public void setOrderno(String orderno) {
        this.orderno = orderno == null ? null : orderno.trim();
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getLoginname() {
        return loginname;
    }

    public void setLoginname(String loginname) {
        this.loginname = loginname == null ? null : loginname.trim();
    }

    public Integer getUserAddressId() {
        return userAddressId;
    }

    public void setUserAddressId(Integer userAddressId) {
        this.userAddressId = userAddressId;
    }

    public String getDistribution() {
        return distribution;
    }

    public void setDistribution(String distribution) {
        this.distribution = distribution == null ? null : distribution.trim();
    }

    public String getPayway() {
        return payway;
    }

    public void setPayway(String payway) {
        this.payway = payway == null ? null : payway.trim();
    }

    public BigDecimal getOrderprice() {
        return orderprice;
    }

    public void setOrderprice(BigDecimal orderprice) {
        this.orderprice = orderprice;
    }

    public String getOrderstatu() {
        return orderstatu;
    }

    public void setOrderstatu(String orderstatu) {
        this.orderstatu = orderstatu == null ? null : orderstatu.trim();
    }
}