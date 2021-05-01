package com.shopping.domain;

import com.shopping.utils.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;

public class User extends BaseEntity {
    private Integer userid;

    private String loginname;

    private String username;

    private String password;

    private String sex;

    private String img;

    private BigDecimal balance;

    private String identitytype;

    private String identitycode;

    private String mobile;

    private Integer type;

    private Integer pwderrorcount;

    private String statu;

    private Integer freezelimit;

    private Date freezingtime;

    private String code;

    private String noteCode;

    private String newMobile;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNoteCode() {
        return noteCode;
    }

    public void setNoteCode(String noteCode) {
        this.noteCode = noteCode;
    }

    public String getNewMobile() {
        return newMobile;
    }

    public void setNewMobile(String newMobile) {
        this.newMobile = newMobile;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img == null ? null : img.trim();
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getIdentitytype() {
        return identitytype;
    }

    public void setIdentitytype(String identitytype) {
        this.identitytype = identitytype == null ? null : identitytype.trim();
    }

    public String getIdentitycode() {
        return identitycode;
    }

    public void setIdentitycode(String identitycode) {
        this.identitycode = identitycode == null ? null : identitycode.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getPwderrorcount() {
        return pwderrorcount;
    }

    public void setPwderrorcount(Integer pwderrorcount) {
        this.pwderrorcount = pwderrorcount;
    }

    public String getStatu() {
        return statu;
    }

    public void setStatu(String statu) {
        this.statu = statu == null ? null : statu.trim();
    }

    public Integer getFreezelimit() {
        return freezelimit;
    }

    public void setFreezelimit(Integer freezelimit) {
        this.freezelimit = freezelimit;
    }

    public Date getFreezingtime() {
        return freezingtime;
    }

    public void setFreezingtime(Date freezingtime) {
        this.freezingtime = freezingtime;
    }
}