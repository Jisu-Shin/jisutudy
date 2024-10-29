package com.jisutudy.sms.filter;

import com.jisutudy.customer.CustSmsConsentType;
import com.jisutudy.sms.SmsType;

public class CustConsentFilter{
    // TODO 추후변경
    public boolean isSendable(CustSmsConsentType smsConsentType, SmsType smsType){
        if (CustSmsConsentType.ALL_ALLOW == smsConsentType){
            return true;
        }
        return false;
    }
}
