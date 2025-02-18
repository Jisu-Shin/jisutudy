package com.jisutudy.domain.customer;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum CustSmsConsentType {
    ALL_ALLOW("01"),  // 전체허용
    ALL_DENY("02");   // 전체거부

    private final String label;

    CustSmsConsentType(String label) {
        this.label = label;
    }

    public static CustSmsConsentType of(String label) {
        return Arrays.stream(values())
                .filter(val -> label.equals(val.label))
                .findFirst()
                .orElse(null);
    }
}
