package com.shopping.domain;

import com.shopping.utils.BaseEntity;

import java.math.BigDecimal;

public class ProductSku extends BaseEntity {
    private Integer id;

    private Integer productid;

    private String productspecs;

    private Integer productstock;

    private BigDecimal productprice;

    private String imgarray;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProductid() {
        return productid;
    }

    public void setProductid(Integer productid) {
        this.productid = productid;
    }

    public String getProductspecs() {
        return productspecs;
    }

    public void setProductspecs(String productspecs) {
        this.productspecs = productspecs == null ? null : productspecs.trim();
    }

    public Integer getProductstock() {
        return productstock;
    }

    public void setProductstock(Integer productstock) {
        this.productstock = productstock;
    }

    public BigDecimal getProductprice() {
        return productprice;
    }

    public void setProductprice(BigDecimal productprice) {
        this.productprice = productprice;
    }

    public String getImgarray() {
        return imgarray;
    }

    public void setImgarray(String imgarray) {
        this.imgarray = imgarray == null ? null : imgarray.trim();
    }

}