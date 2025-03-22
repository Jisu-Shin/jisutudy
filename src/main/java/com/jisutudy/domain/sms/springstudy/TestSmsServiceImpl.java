package com.jisutudy.domain.sms.springstudy;

import com.jisutudy.domain.customer.springstudy.Cust;
import com.jisutudy.domain.customer.springstudy.CustRepository;
import com.jisutudy.domain.sms.SmsResult;
import com.jisutudy.service.filter.SmsFilter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
public class TestSmsServiceImpl implements TestSmsService {
    private final SmsRepository smsRepository;
    private final CustRepository custRepository;
    private final SmsFilter smsFilter;

    @Override
    public SmsResult sendSms(Sms sms) {
        // 고객 전화번호 세팅
        Cust cust = custRepository.findById(sms.getCustId());
//        sms.setCustPhoneNumber(cust.getPhoneNumber());

        // 필터링
//        SmsResult smsResult = smsFilter.filter(sms);
        SmsResult smsResult = SmsResult.SUCCESS;

        smsRepository.save(sms);
        return smsResult;
    }

    @Override
    public List<Sms> findSmsList(LocalDateTime startDt, LocalDateTime endDt) {
        List<Sms> smsList = smsRepository.findListBySendDt(startDt, endDt);
        return smsList;
    }
}
