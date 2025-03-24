package com.jisutudy.service.filter;

import com.jisutudy.domain.customer.CustSmsConsentType;
import com.jisutudy.domain.sms.SmsResult;
import com.jisutudy.domain.sms.SmsType;

public class CustConsentFilter {
    // TODO 고객 SMS 동의 유형이 추가될 때 변경 -> 그럼 매번 변경해야하는것인가?
    public boolean isSendable(CustSmsConsentType smsConsentType, SmsType smsType) {

        // 0. 인증 문자는 무조건 바로 나가기
        if (SmsType.VERIFICATION == smsType) {
            return true;
        }

        if (CustSmsConsentType.ALL_ALLOW == smsConsentType) {
            return true;
        }

        // 고객은 광고 거부인데 문자는 일반인경우... 나가야함
        if (CustSmsConsentType.ADVERTISE_DENY == smsConsentType
                && SmsType.INFORMAITONAL == smsType) {
            return true;
        }

        return false;
    }
}
