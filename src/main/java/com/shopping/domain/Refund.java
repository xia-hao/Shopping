package com.shopping.domain;

import com.shopping.utils.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;

public class Refund extends BaseEntity {
    private String refundNo;

    private Integer userId;

    private String orderno;

    private BigDecimal brefundMoney;

    private String backNotice;

    private String refundStatus;

    public String getRefundNo() {
        return refundNo;
    }

    public void setRefundNo(String refundNo) {
        this.refundNo = refundNo == null ? null : refundNo.trim();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getOrderno() {
        return orderno;
    }

    public void setOrderno(String orderno) {
        this.orderno = orderno == null ? null : orderno.trim();
    }

    public BigDecimal getBrefundMoney() {
        return brefundMoney;
    }

    public void setBrefundMoney(BigDecimal brefundMoney) {
        this.brefundMoney = brefundMoney;
    }

    public String getBackNotice() {
        return backNotice;
    }

    public void setBackNotice(String backNotice) {
        this.backNotice = backNotice == null ? null : backNotice.trim();
    }

    public String getRefundStatus() {
        return refundStatus;
    }

    public void setRefundStatus(String refundStatus) {
        this.refundStatus = refundStatus == null ? null : refundStatus.trim();
    }
}