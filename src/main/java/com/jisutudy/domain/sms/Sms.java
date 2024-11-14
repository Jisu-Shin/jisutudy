package com.jisutudy.domain.sms;

import com.jisutudy.domain.BaseTimeEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
public class Sms extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long smsId;
    private Long custId;
    private String sendPhoneNumber;
    private String smsContent;
    private LocalDateTime sendDt;
    private SmsType smsType;
    private SmsResult smsResult;

    @Builder
    public Sms(Long custId, String smsContent, LocalDateTime sendDt, SmsType smsType) {
        this.custId = custId;
        this.smsContent = smsContent;
        this.sendDt = sendDt;
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

    public void setCustId(Long custId) {
        this.custId = custId;
    }

    public void setSendPhoneNumber(String sendPhoneNumber) {
        this.sendPhoneNumber = sendPhoneNumber;
    }

    public void setSmsContent(String smsContent) {
        this.smsContent = smsContent;
    }

    public void setSendDt(LocalDateTime sendDt) {
        this.sendDt = sendDt;
    }

    public void setSmsType(SmsType smsType) {
        this.smsType = smsType;
    }

    public void setSmsResult(SmsResult smsResult) {
        this.smsResult = smsResult;
    }
}
