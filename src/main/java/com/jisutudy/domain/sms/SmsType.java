package com.jisutudy.domain.sms;

import com.jisutudy.domain.customer.CustSmsConsentType;

import java.util.Arrays;

public enum SmsType {
    INFORMAITONAL("정보성 문자"), // 정보성
    ADVERTISING("광고성 문자"),  // 광고성
    VERIFICATION("인증 문자"); // 인증

    private final String displayName;

    SmsType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
