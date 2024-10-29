package com.jisutudy.sms;

import com.jisutudy.customer.Cust;
import com.jisutudy.customer.CustRepository;
import com.jisutudy.customer.MemoryCustRepository;
import com.jisutudy.sms.filter.AdvertiseFilter;
import com.jisutudy.sms.filter.CustConsentFilter;
import com.jisutudy.sms.filter.ProdTimeSmsFilter;
import com.jisutudy.sms.filter.TimeSmsFilter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public class SmsServiceImpl implements SmsService{
    private final SmsRepository smsRepository = new MemorySmsRepository();
    private final CustRepository custRepository = new MemoryCustRepository();

    @Override
    public SmsResult sendSms() {
        Sms sms = new Sms(1L,2L,null,"문자내용",LocalDateTime.now(), SmsType.INFORMAITONAL);

        // 고객 전화번호 세팅
        Cust cust = custRepository.findById(2L);
        sms.setSendPhoneNumber(cust.getPhoneNumber());

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
        List<Sms> todaySmsList = findSmsList(startDt, endDt);
        if (!advertiseFilter.isSendable(todaySmsList, cust.getId())){
            return SmsResult.AD_COUNT_OVER;
        }

        smsRepository.save(sms);
        return SmsResult.SUCCESS;
    }

    @Override
    public List<Sms> findSmsList(LocalDateTime startDt, LocalDateTime endDt) {
        return List.of();
    }
}
