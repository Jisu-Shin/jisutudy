package com.jisutudy.web.dto;

import com.jisutudy.domain.sms.Sms;
import com.jisutudy.domain.sms.SmsResult;
import com.jisutudy.domain.sms.SmsType;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class SmsFindListResponseDto {
    private Long smsId;
    private Long custId;
    private String sendPhoneNumber;
    private String smsContent;
    private LocalDateTime sendDt;
    private SmsType smsType;
    private SmsResult smsResult;

    public SmsFindListResponseDto(Sms sms) {
        this.smsId = sms.getSmsId();
        this.custId = sms.getCust().getId();
        this.sendPhoneNumber = sms.getSendPhoneNumber();
        this.smsContent = sms.getSmsContent();
        this.sendDt = sms.getSendDt();
        this.smsType = sms.getSmsTemplate().getSmsType();
        this.smsResult = sms.getSmsResult();
    }
}
