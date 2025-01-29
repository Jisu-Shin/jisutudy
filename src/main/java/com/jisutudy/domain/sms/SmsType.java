package com.jisutudy.domain.sms;

import com.jisutudy.domain.customer.CustSmsConsentType;

import java.util.Arrays;

public enum SmsType {
    INFORMAITONAL("01"), // 정보성
    ADVERTISING("02"),  // 광고성
    VERIFICATION("03"); // 인증

    private final String label;

    SmsType(String label) {
        this.label = label;
    }

    public static SmsType of(String label) {
        return Arrays.stream(values())
                .filter(val -> label.equals(val.label))
                .findFirst()
                .orElse(null);
    }
}
