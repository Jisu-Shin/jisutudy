package com.jisutudy.web.dto;

import com.jisutudy.domain.sms.Sms;
import com.jisutudy.domain.sms.SmsType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@NoArgsConstructor
public class SmsSendRequestDto {
    Long custId;
    String smsContent;
    String sendDt;
    String smsType;

    @Builder
    public SmsSendRequestDto(Long custId, String smsContent, String sendDt, String smsType) {
        this.custId = custId;
        this.smsContent = smsContent;
        this.sendDt = sendDt;
        this.smsType = smsType;
    }

    public Sms toEntity(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmm");
        return Sms.builder()
                .custId(custId)
                .smsContent(smsContent)
                .sendDt(LocalDateTime.parse(sendDt,formatter))
                .smsType(SmsType.of(smsType))
                .build();
    }
}
