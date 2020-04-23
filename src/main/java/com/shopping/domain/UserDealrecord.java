package com.shopping.domain;

import com.shopping.utils.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;

public class UserDealrecord extends BaseEntity {
    private Integer userid;

    private String productname;

    private Integer number;

    private BigDecimal price;

    private BigDecimal userprice;

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname == null ? null : productname.trim();
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getUserprice() {
        return userprice;
    }

    public void setUserprice(BigDecimal userprice) {
        this.userprice = userprice;
    }
}