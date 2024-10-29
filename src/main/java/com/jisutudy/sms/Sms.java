package com.jisutudy.sms;

import java.time.LocalDateTime;

public class Sms {
    Long smsId;
    Long custId;
    String sendPhoneNumber;
    String smsContent;
    LocalDateTime sendDt;
    SmsType smsType;

    public Sms(Long smsId, Long custId, String sendPhoneNumber, String smsContent, LocalDateTime sendDt, SmsType smsType) {
        this.smsId = smsId;
        this.custId = custId;
        this.sendPhoneNumber = sendPhoneNumber;
        this.smsContent = smsContent;
        this.sendDt = sendDt;
        this.smsType = smsType;
    }

    public Long getSmsId() {
        return smsId;
    }

    public void setSmsId(Long smsId) {
        this.smsId = smsId;
    }

    public Long getCustId() {
        return custId;
    }

    public void setCustId(Long custId) {
        this.custId = custId;
    }

    public String getSendPhoneNumber() {
        return sendPhoneNumber;
    }

    public void setSendPhoneNumber(String sendPhoneNumber) {
        this.sendPhoneNumber = sendPhoneNumber;
    }

    public String getSmsContent() {
        return smsContent;
    }

    public void setSmsContent(String smsContent) {
        this.smsContent = smsContent;
    }

    public LocalDateTime getSendDt() {
        return sendDt;
    }

    public void setSendDt(LocalDateTime sendDt) {
        this.sendDt = sendDt;
    }

    public SmsType getSmsType() {
        return smsType;
    }

    public void setSmsType(SmsType smsType) {
        this.smsType = smsType;
    }
}
