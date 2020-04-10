package com.shopping.domain;

import java.util.Date;

public class SlideShow {
    private Integer id;

    private Integer pid;

    private String type;

    private String picurl;

    private String picurlTo;

    private Date createtime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getPicurl() {
        return picurl;
    }

    public void setPicurl(String picurl) {
        this.picurl = picurl == null ? null : picurl.trim();
    }

    public String getPicurlTo() {
        return picurlTo;
    }

    public void setPicurlTo(String picurlTo) {
        this.picurlTo = picurlTo == null ? null : picurlTo.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
}