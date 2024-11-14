package com.jisutudy.domain.sms.springstudy;

import com.jisutudy.domain.customer.Cust;
import com.jisutudy.domain.customer.springstudy.CustRepository;
import com.jisutudy.domain.sms.Sms;
import com.jisutudy.domain.sms.SmsResult;
import com.jisutudy.domain.sms.filter.SmsFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class SmsServiceImpl implements SmsService{
    private final SmsRepository smsRepository;
    private final CustRepository custRepository;
    private final SmsFilter smsFilter;

    @Override
    public SmsResult sendSms(Sms sms) {
//        Sms sms = new Sms(1L,2L,null,"문자내용",LocalDateTime.now(), SmsType.INFORMAITONAL);

        // 고객 전화번호 세팅
        Cust cust = custRepository.findById(sms.getCustId());
        sms.setSendPhoneNumber(cust.getPhoneNumber());

        // 필터링
        SmsResult smsResult = smsFilter.filter(sms);

        // TODO sms 상태? 처리결과를 저장하는 필드가 필요하다
        smsRepository.save(sms);
        return smsResult;
    }

    @Override
    public List<Sms> findSmsList(LocalDateTime startDt, LocalDateTime endDt) {
        List<Sms> smsList = smsRepository.findListBySendDt(startDt, endDt);
        return smsList;
    }
}
