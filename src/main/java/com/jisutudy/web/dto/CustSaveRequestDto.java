package com.jisutudy.web.dto;

import com.jisutudy.domain.customer.Cust;
import com.jisutudy.domain.customer.CustSmsConsentType;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CustSaveRequestDto {

    @NotBlank(message = "이름은 필수값입니다")
    private String name;
    @NotBlank(message = "전화번호는 필수값입니다")
    private String phoneNumber;
    @NotBlank(message = "고객SMS발송유형은 필수값입니다")
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
