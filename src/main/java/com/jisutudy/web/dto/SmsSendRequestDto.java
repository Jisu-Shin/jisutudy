package com.jisutudy.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class SmsSendRequestDto {

    List<Long> custIdList;
    String smsContent;
    String sendDt;
    String smsType;
    Long templateId;
    Long itemId;

    @Builder
    public SmsSendRequestDto(List<Long> custIdList, String smsContent, String sendDt, String smsType, Long templateId) {
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
