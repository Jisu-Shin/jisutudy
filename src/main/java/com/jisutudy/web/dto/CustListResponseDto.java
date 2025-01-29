package com.jisutudy.web.dto;

import com.jisutudy.domain.customer.Cust;
import com.jisutudy.domain.customer.CustSmsConsentType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
@Schema(title = "고객전체조회 응답 DTO")
public class CustListResponseDto {

    @Schema(description = "아이디")
    private Long id;

    @Schema(description = "이름")
    private String name;

    @Schema(description = "전화번호")
    private String phoneNumber;

    @Schema(description = "고객SMS수신동의타입")
    private CustSmsConsentType consentType;

    public CustListResponseDto(Cust entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.phoneNumber = entity.getPhoneNumber();
        this.consentType = entity.getSmsConsentType();
    }
}
