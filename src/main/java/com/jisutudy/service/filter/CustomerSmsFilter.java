package com.jisutudy.service.filter;

import com.jisutudy.domain.CustSmsConsentType;
import com.jisutudy.domain.SmsType;

public interface CustomerSmsFilter {
    public boolean isSendable(CustSmsConsentType custSmsConsentType, SmsType smsType);
}
