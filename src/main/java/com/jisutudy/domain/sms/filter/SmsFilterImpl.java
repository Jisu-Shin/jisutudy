package com.jisutudy.domain.sms.filter;

import com.jisutudy.domain.SmsTemplate;
import com.jisutudy.domain.customer.Cust;
import com.jisutudy.domain.customer.springstudy.CustRepository;
import com.jisutudy.domain.sms.Sms;
import com.jisutudy.domain.sms.SmsType;
import com.jisutudy.domain.sms.springstudy.SmsRepository;
import com.jisutudy.domain.sms.SmsResult;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@RequiredArgsConstructor
@Component
public class SmsFilterImpl implements SmsFilter {

    private final TimeSmsFilter timeSmsFilter;
    private final AdvertiseSmsFilter advertiseSmsFilter;

    @Override
    public SmsResult filter(Sms sms) {
        // sms 필터링
        // 1. 시간
        if (!timeSmsFilter.isSendable(sms.getSendDt())) {
            return SmsResult.NOT_SEND_TIME;
        }

        // 2. 고객동의
        Cust cust = sms.getCust();
        SmsTemplate smsTemplate = sms.getSmsTemplate();

        CustConsentFilter consentFilter = new CustConsentFilter(); // TODO OCP, DIP 위반
        if (!consentFilter.isSendable(cust.getSmsConsentType(), smsTemplate.getSmsType())) {
            return SmsResult.CUST_REJECT;
        }

        // 3. 광고
        if (SmsType.ADVERTISING == smsTemplate.getSmsType()) {
            if (!advertiseSmsFilter.isSendable(sms)) {
                return SmsResult.AD_COUNT_OVER;
            }
        }

        return SmsResult.SUCCESS;
    }
}
