package com.jisutudy.domain.sms.filter;

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

    // 리포지토리 버전1) 메모리 리포지토리
    private SmsRepository smsRepository;
    private CustRepository custRepository;

    @Autowired
    public void setRepository(SmsRepository smsRepository, CustRepository custRepository) {
        this.smsRepository = smsRepository;
        this.custRepository = custRepository;
    }

    @Override
    public SmsResult filter(Sms sms) {
        Cust cust = custRepository.findById(sms.getCustId());

        return filter(sms, cust);
    }

    @Override
    public SmsResult filter(Sms sms, Cust cust) {
        // sms 필터링
        // 1. 시간
        if (!timeSmsFilter.isSendable(sms.getSendDt())) {
            return SmsResult.NOT_SEND_TIME;
        }

        // 2. 고객동의
        CustConsentFilter consentFilter = new CustConsentFilter(); // TODO OCP, DIP 위반
        if (!consentFilter.isSendable(cust.getSmsConsentType(), sms.getSmsType())) {
            return SmsResult.CUST_REJECT;
        }

        // 3. 광고
        if (SmsType.ADVERTISING == sms.getSmsType()) {
            if (!advertiseSmsFilter.isSendable(sms)) {
                return SmsResult.AD_COUNT_OVER;
            }
        }

        return SmsResult.SUCCESS;
    }
}
