package com.jisutudy.web.dto;

import com.jisutudy.domain.sms.Sms;
import com.jisutudy.domain.sms.SmsType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Getter
@NoArgsConstructor
public class SmsSendRequestDto {
    List<Long> custIdList;
    String smsContent;
    String sendDt;
    String smsType;
    String templateId;

    @Builder
    public SmsSendRequestDto(List<Long> custIdList, String smsContent, String sendDt, String smsType, String templateId) {
        this.custIdList = custIdList;
        this.smsContent = smsContent;
        this.sendDt = sendDt;
        this.smsType = smsType;
        this.templateId = templateId;
    }

//    public Sms toEntity(Long custId){
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmm");
//        return Sms.builder()
//                .custId(custId)
//                .smsContent(smsContent)
//                .sendDt(LocalDateTime.parse(sendDt,formatter))
//                .smsType(SmsType.of(smsType))
//                .build();
//    }
}
