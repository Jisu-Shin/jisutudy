package com.jisutudy.web.dto;

import com.jisutudy.domain.sms.Sms;
import com.jisutudy.domain.sms.SmsType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class SmsSendRequestDto {
    Long custId;
    String smsContent;
    LocalDateTime sendDt;
    SmsType smsType;

    @Builder
    public SmsSendRequestDto(Long custId, String smsContent, LocalDateTime sendDt, SmsType smsType) {
        this.custId = custId;
        this.smsContent = smsContent;
        this.sendDt = sendDt;
        this.smsType = smsType;
    }

    public Sms toEntity(){
        return Sms.builder()
                .custId(custId)
                .smsContent(smsContent)
                .sendDt(sendDt)
                .smsType(smsType)
                .build();
    }
}
