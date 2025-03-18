package com.jisutudy.domain.sms.springstudy;

import com.jisutudy.domain.BaseTimeEntity;
import com.jisutudy.domain.sms.SmsResult;
import com.jisutudy.domain.sms.SmsType;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class Sms extends BaseTimeEntity {
    private Long smsId;
    private Long custId;
    private String sendPhoneNumber;
    private String smsContent;
    private LocalDateTime sendDt;
    private SmsResult smsResult;
    private SmsType smsType;

    public Sms(Long smsId, Long custId, String sendPhoneNumber, String smsContent, LocalDateTime sendDt, SmsResult smsResult, SmsType smsType) {
        this.smsId = smsId;
        this.custId = custId;
        this.sendPhoneNumber = sendPhoneNumber;
        this.smsContent = smsContent;
        this.sendDt = sendDt;
        this.smsResult = smsResult;
        this.smsType = smsType;
    }

    public Sms(Long smsId, Long custId, String sendPhoneNumber, String smsContent, LocalDateTime sendDt, SmsType smsType) {
        this.smsId = smsId;
        this.custId = custId;
        this.sendPhoneNumber = sendPhoneNumber;
        this.smsContent = smsContent;
        this.sendDt = sendDt;
        this.smsType = smsType;
    }

    //== setter==
    private void setSendPhoneNumber(String phoneNumber){
        this.sendPhoneNumber = phoneNumber;
    }

    public void setSmsResult(SmsResult smsResult) {
        this.smsResult = smsResult;
    }
}
