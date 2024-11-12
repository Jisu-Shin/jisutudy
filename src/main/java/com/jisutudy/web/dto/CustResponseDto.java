package com.jisutudy.web.dto;

import com.jisutudy.domain.customer.Cust;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CustResponseDto {
    private Long id;
    private String name;
    private String phoneNumber;
    private String smsConsentType;

    public CustResponseDto(Cust entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.phoneNumber = entity.getPhoneNumber();
        this.smsConsentType = entity.getSmsConsentType().getLabel();
    }
}
