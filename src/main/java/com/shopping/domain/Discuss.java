package com.shopping.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.shopping.utils.BaseEntity;

import java.util.Date;

public class Discuss extends BaseEntity {
    private Integer id;

    private Integer userid;

    private Integer productid;

    private String content;

    private String Loginname;

    private String username;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createtime;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLoginname() {
        return Loginname;
    }

    public void setLoginname(String loginname) {
        Loginname = loginname;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getProductid() {
        return productid;
    }

    public void setProductid(Integer productid) {
        this.productid = productid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
}