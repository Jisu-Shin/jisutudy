package com.jisutudy.service.filter;

import com.jisutudy.domain.CustSmsConsentType;
import com.jisutudy.domain.sms.Sms;
import com.jisutudy.domain.sms.SmsResult;

public interface SmsFilter {
    SmsResult filter(Sms sms);
    SmsResult filter(Sms sms, CustSmsConsentType type);
}
