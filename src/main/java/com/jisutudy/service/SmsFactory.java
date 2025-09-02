package com.jisutudy.service;

import com.jisutudy.domain.CustSmsConsentType;
import com.jisutudy.domain.SmsTemplate;
import com.jisutudy.domain.sms.Sms;
import com.jisutudy.domain.sms.SmsResult;
import com.jisutudy.service.filter.SmsFilter;
import com.jisutudy.service.smsTemplateVarBind.BindingDto;
import com.jisutudy.service.smsTemplateVarBind.SmsTmpltVarBinder;
import com.jisutudy.dto.CustInfo;
import com.jisutudy.dto.SmsSendRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class SmsFactory {
    private final SmsFilter smsFilter;
    private final SmsTmpltVarBinder smsTmpltVarBinder;

    public List<Sms> createSmsList(SmsTemplate smsTemplate, SmsSendRequestDto requestDto) {
        return requestDto.getCustIdList().stream()
                .map(custInfo -> create(custInfo, smsTemplate, requestDto))
                .collect(Collectors.toList());
    }

    private Sms create(CustInfo cust, SmsTemplate smsTemplate, SmsSendRequestDto requestDto) {
        // 1. 문자내용 바인딩
        String bindSmsContent = smsTmpltVarBinder.bind(smsTemplate, BindingDto.create(cust.getCustId(), requestDto));
        log.info("@@@@@ 생성된 문자내용 {}" , bindSmsContent);

        // 2. 문자 엔티티 생성
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmm");
        LocalDateTime sendDt = LocalDateTime.parse(requestDto.getSendDt(), formatter);

        Sms sms = Sms.builder()
                .custId(cust.getCustId())
                .smsTemplate(smsTemplate)
                .smsContent(bindSmsContent)
                .sendDt(sendDt)
                .sendPhoneNumber(cust.getPhoneNumber())
                .build();
        log.info("@@@@@ sms 생성완료");

        // 3. 필터링
        SmsResult smsResult = smsFilter.filter(sms, CustSmsConsentType.of(cust.getCustSmsConsentType()));
        log.info("@@@@@ sms 필터완료 {}", smsResult);

        // 4. 필터링 결과 세팅
        sms.setSmsResult(smsResult);
        log.info("@@@@@ sms 필터완료 결과 세팅 완료");

        return sms;
    }
}
