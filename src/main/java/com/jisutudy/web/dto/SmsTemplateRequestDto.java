package com.jisutudy.web.dto;

import com.jisutudy.domain.sms.SmsType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class SmsTemplateRequestDto {

    private String templateContent;
    private SmsType smsType;

    @Builder
    public SmsTemplateRequestDto(String templateContent, SmsType smsType) {
        this.templateContent = templateContent;
        this.smsType = smsType;
    }

    @Override
    public String toString() {
        return "SmsTemplateRequestDto{" +
                "templateContent='" + templateContent + '\'' +
                ", smsType='" + smsType + '\'' +
                '}';
    }
}
