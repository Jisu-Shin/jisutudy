package com.jisutudy.domain.sms.filter;

import com.jisutudy.domain.customer.CustSmsConsentType;
import com.jisutudy.domain.sms.SmsType;

public class CustConsentFilter{
    // TODO 고객 SMS 동의 유형이 추가될 때 변경 -> 그럼 매번 변경해야하는것인가?
    public boolean isSendable(CustSmsConsentType smsConsentType, SmsType smsType){
        if (CustSmsConsentType.ALL_ALLOW == smsConsentType){
            return true;
        }
        return false;
    }
}
