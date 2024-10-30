package com.jisutudy.sms.filter;

import com.jisutudy.customer.Cust;
import com.jisutudy.customer.CustRepository;
import com.jisutudy.customer.MemoryCustRepository;
import com.jisutudy.sms.MemorySmsRepository;
import com.jisutudy.sms.Sms;
import com.jisutudy.sms.SmsRepository;
import com.jisutudy.sms.SmsResult;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public class SmsFilterImpl implements SmsFilter{

    private final SmsRepository smsRepository;
    private final CustRepository custRepository;

    public SmsFilterImpl(SmsRepository smsRepository, CustRepository custRepository) {
        this.smsRepository = new MemorySmsRepository();
        this.custRepository = new MemoryCustRepository();
    }

    @Override
    public SmsResult filter(Sms sms) {
        Cust cust = custRepository.findById(sms.getCustId());

        // sms 필터링
        // 1. 시간
        TimeSmsFilter timeSmsFilter = new ProdTimeSmsFilter();
            if(!timeSmsFilter.isSendable(sms.getSendDt())){
            return SmsResult.NOT_SEND_TIME;
        }

        // 2. 고객동의
        CustConsentFilter consentFilter = new CustConsentFilter();
            if (!consentFilter.isSendable(cust.getMsgConsentType(), sms.getSmsType())){
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
