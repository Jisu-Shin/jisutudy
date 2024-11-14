package com.jisutudy.domain.sms.filter;

import com.jisutudy.domain.customer.Cust;
import com.jisutudy.domain.customer.springstudy.CustRepository;
import com.jisutudy.domain.sms.Sms;
import com.jisutudy.domain.sms.springstudy.SmsRepository;
import com.jisutudy.domain.sms.SmsResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Component("smsFilter")
public class SmsFilterImpl implements SmsFilter{

    private SmsRepository smsRepository;
    private CustRepository custRepository;
    private TimeSmsFilter timeSmsFilter;

    @Autowired
    public SmsFilterImpl(SmsRepository smsRepository, CustRepository custRepository,TimeSmsFilter prodTimeSmsFilter) {
        this.smsRepository = smsRepository;
        this.custRepository = custRepository;
        this.timeSmsFilter = prodTimeSmsFilter;
    }

    @Override
    public SmsResult filter(Sms sms) {
        Cust cust = custRepository.findById(sms.getCustId());

        // sms 필터링
        // 1. 시간
            if(!timeSmsFilter.isSendable(sms.getSendDt())){
            return SmsResult.NOT_SEND_TIME;
        }

        // 2. 고객동의
        CustConsentFilter consentFilter = new CustConsentFilter();
            if (!consentFilter.isSendable(cust.getSmsConsentType(), sms.getSmsType())){
            return SmsResult.CUST_REJECT;
        }

        // 3. 광고
        AdvertiseFilter advertiseFilter = new AdvertiseFilter();
        LocalDateTime startDt = LocalDateTime.of(LocalDate.now(), LocalTime.of(0,0));
        LocalDateTime endDt = LocalDateTime.of(LocalDate.now().plusDays(1), LocalTime.of(0,0));
        List<Sms> todaySmsList = smsRepository.findListBySendDt(startDt, endDt);
            if (!advertiseFilter.isSendable(todaySmsList, sms)){
            return SmsResult.AD_COUNT_OVER;
        }

        return SmsResult.SUCCESS;
    }
}
