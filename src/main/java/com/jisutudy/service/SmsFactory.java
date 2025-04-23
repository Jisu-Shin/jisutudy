package com.jisutudy.service;

import com.jisutudy.domain.CustSmsConsentType;
import com.jisutudy.domain.SmsTemplate;
import com.jisutudy.domain.sms.Sms;
import com.jisutudy.domain.sms.SmsResult;
import com.jisutudy.repository.JpaSmsTemplateRepository;
import com.jisutudy.service.filter.SmsFilter;
import com.jisutudy.service.smsTemplateVarBind.BindingDto;
import com.jisutudy.service.smsTemplateVarBind.SmsTmpltVarBinder;
import com.jisutudy.dto.CustInfo;
import com.jisutudy.dto.SmsSendRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class SmsFactory {
    private final JpaSmsTemplateRepository jpaSmsTemplateRepository;
    private final SmsFilter smsFilter;
    private final SmsTmpltVarBinder smsTmpltVarBinder;

    public List<Sms> createSmsList(SmsSendRequestDto requestDto) {
        SmsTemplate smsTemplate = getSmsTemplate(requestDto);

        return requestDto.getCustIdList().stream()
                .map(custInfo -> create(custInfo, smsTemplate, requestDto))
                .collect(Collectors.toList());
    }

    private Sms create(CustInfo cust, SmsTemplate smsTemplate, SmsSendRequestDto requestDto) {
        // 문자내용 바인딩
        String bindSmsContent = smsTmpltVarBinder.bind(smsTemplate, BindingDto.create(cust.getCustId(), requestDto));

        // 문자 엔티티 생성
        Sms sms = Sms.createSms(cust.getCustId(), smsTemplate, bindSmsContent, requestDto.getSendDt(), cust.getPhoneNumber());

        // 필터링
        SmsResult smsResult = smsFilter.filter(sms, CustSmsConsentType.of(cust.getCustSmsConsentType()));
        sms.setSmsResult(smsResult);

        return sms;
    }

    private SmsTemplate getSmsTemplate(SmsSendRequestDto requestDto) {
        return jpaSmsTemplateRepository.findById(requestDto.getTemplateId())
                .orElseThrow(() -> new IllegalArgumentException("해당 SMS 템플릿이 없습니다: " + requestDto.getTemplateId()));
    }

}
