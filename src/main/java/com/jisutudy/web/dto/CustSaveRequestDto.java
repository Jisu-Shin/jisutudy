package com.jisutudy.web.dto;

import com.jisutudy.domain.customer.Cust;
import com.jisutudy.domain.customer.CustSmsConsentType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CustSaveRequestDto {

    private String name;
    private String phoneNumber;
    private String smsConsentType;

    @Builder
    public CustSaveRequestDto(String name, String phoneNumber, String smsConsentType) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.smsConsentType = smsConsentType;
    }

    public Cust toEntity() {
        return Cust.builder()
                .name(name)
                .phoneNumber(phoneNumber)
                .smsConsentType(CustSmsConsentType.of(smsConsentType))
                .build();
    }
}
