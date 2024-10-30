package com.jisutudy.sms;

import com.jisutudy.customer.Cust;
import com.jisutudy.customer.CustRepository;
import com.jisutudy.customer.MemoryCustRepository;
import com.jisutudy.sms.filter.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class SmsServiceImpl implements SmsService{
    private final SmsRepository smsRepository = new MemorySmsRepository();
    private final CustRepository custRepository = new MemoryCustRepository();
    private final SmsFilter smsFilter = new SmsFilterImpl();

    @Override
    public SmsResult sendSms() {
        Sms sms = new Sms(1L,2L,null,"문자내용",LocalDateTime.now(), SmsType.INFORMAITONAL);

        // 고객 전화번호 세팅
        Cust cust = custRepository.findById(2L);
        sms.setSendPhoneNumber(cust.getPhoneNumber());

        // 필터링
        SmsResult smsResult = smsFilter.filter(sms);

        // TODO sms 상태? 처리결과를 저장하는 필드가 필요할듯
        smsRepository.save(sms);
        return smsResult;
    }

    @Override
    public List<Sms> findSmsList(LocalDateTime startDt, LocalDateTime endDt) {
        List<Sms> smsList = smsRepository.findListBySendDt(startDt, endDt);
        return smsList;
    }
}
