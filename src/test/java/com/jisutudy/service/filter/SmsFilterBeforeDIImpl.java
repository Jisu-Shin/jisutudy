package com.jisutudy.service.filter;

import com.jisutudy.domain.CustSmsConsentType;
import com.jisutudy.domain.SmsTemplate;
import com.jisutudy.domain.SmsType;
import com.jisutudy.domain.sms.Sms;
import com.jisutudy.domain.sms.SmsResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class SmsFilterBeforeDIImpl implements SmsFilter {

    private final TimeSmsFilter timeSmsFilter;
    private final AdvertiseSmsFilter advertiseSmsFilter;

    @Override
    public SmsResult filter(Sms sms, CustSmsConsentType type) {
        // sms 필터링
        SmsTemplate smsTemplate = sms.getSmsTemplate();

        // 0. 인증문자는 항상 나가야하는 법...
        if (SmsType.VERIFICATION == smsTemplate.getSmsType()) {
            return SmsResult.SUCCESS;
        }

        // 1. 시간
        if (!timeSmsFilter.isSendable(sms.getSendDt())) {
            return SmsResult.NOT_SEND_TIME;
        }

        // 2. 고객동의
        CustConsentFilter customerSmsFilter = new CustConsentFilter(); // OCP, DIP 위반
        if (!customerSmsFilter.isSendable(type, smsTemplate.getSmsType())) {
            return SmsResult.CUST_REJECT;
        }

        // 3. 광고 개수 제한
        if (SmsType.ADVERTISING == smsTemplate.getSmsType()) {
            if (!advertiseSmsFilter.isSendable(sms)) {
                return SmsResult.AD_COUNT_OVER;
            }
        }

        return SmsResult.SUCCESS;
    }

}
