package com.jisutudy.domain.sms.filter;

import com.jisutudy.domain.customer.CustSmsConsentType;
import com.jisutudy.domain.sms.SmsType;

public class CustConsentFilter{
    // TODO 추후변경
    public boolean isSendable(CustSmsConsentType smsConsentType, SmsType smsType){
        if (CustSmsConsentType.ALL_ALLOW == smsConsentType){
            return true;
        }
        return false;
    }
}
