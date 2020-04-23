package com.shopping.domain;

import com.shopping.utils.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;

public class PayMent extends BaseEntity {
    private Integer userid;

    private String loginname;

    private String orderno;

    private BigDecimal cost;

    private String paystatu;

    private String paytype;

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

    public String getOrderno() {
        return orderno;
    }

    public void setOrderno(String orderno) {
        this.orderno = orderno == null ? null : orderno.trim();
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public String getPaystatu() {
        return paystatu;
    }

    public void setPaystatu(String paystatu) {
        this.paystatu = paystatu == null ? null : paystatu.trim();
    }

    public String getPaytype() {
        return paytype;
    }

    public void setPaytype(String paytype) {
        this.paytype = paytype == null ? null : paytype.trim();
    }
}