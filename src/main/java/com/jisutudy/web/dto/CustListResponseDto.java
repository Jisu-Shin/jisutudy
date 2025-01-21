package com.jisutudy.web.dto;

import com.jisutudy.domain.customer.Cust;
import com.jisutudy.domain.customer.CustSmsConsentType;
import lombok.Getter;

@Getter
public class CustListResponseDto {

    private Long id;
    private String name;
    private String phoneNumber;
    private CustSmsConsentType consentType;

    public CustListResponseDto(Cust entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.phoneNumber = entity.getPhoneNumber();
        this.consentType = entity.getSmsConsentType();
    }
}
