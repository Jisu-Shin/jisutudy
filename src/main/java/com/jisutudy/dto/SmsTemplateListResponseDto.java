package com.jisutudy.dto;

import com.jisutudy.domain.SmsTemplate;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
@Schema(title = "sms템플릿 전체 조회 응답 DTO")
public class SmsTemplateListResponseDto {
    private Long id;
    private String templateContent;
    private String smsType;

    public SmsTemplateListResponseDto(SmsTemplate entity) {
        this.id = entity.getId();
        this.templateContent = entity.getTemplateContent();
        this.smsType = entity.getSmsType().getDisplayName();
    }
}
