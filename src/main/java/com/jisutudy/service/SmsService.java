package com.jisutudy.service;

import com.jisutudy.domain.SmsTemplate;
import com.jisutudy.domain.customer.Cust;
import com.jisutudy.repository.JpaCustRepository;
import com.jisutudy.repository.JpaSmsRepository;
import com.jisutudy.domain.sms.Sms;
import com.jisutudy.domain.sms.SmsResult;
import com.jisutudy.service.filter.SmsFilter;
import com.jisutudy.repository.JpaSmsTemplateRepository;
import com.jisutudy.service.smsTemplateVarBind.SmsTmpltVarBinder;
import com.jisutudy.web.dto.SmsFindListResponseDto;
import com.jisutudy.web.dto.SmsSendRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SmsService {
    private final JpaSmsRepository jpaSmsRepository;
    private final JpaCustRepository jpaCustRepository;
    private final JpaSmsTemplateRepository jpaSmsTemplateRepository;
    private final SmsFilter smsFilter;
    private final SmsTmpltVarBinder smsTmpltVarBinder;

    @Transactional
    public boolean send(SmsSendRequestDto requestDto) {
        // 템플릿 엔티티 조회
        SmsTemplate smsTemplate = jpaSmsTemplateRepository.findById(requestDto.getTemplateId())
                .orElseThrow(() -> new IllegalArgumentException("해당 SMS 템플릿이 없습니다: " + requestDto.getTemplateId()));

        for (Long custId : requestDto.getCustIdList()) {
            // 고객 엔티티 조회
            Cust cust = jpaCustRepository.findById(custId)
                    .orElseThrow(() -> new IllegalArgumentException("해당 고객이 없습니다: "+custId));

            // TODO 템플릿 결합
            String bindSmsContent = smsTmpltVarBinder.bind(smsTemplate, cust);

            // 문자 엔티티 생성
            Sms sms = Sms.createSms(cust, smsTemplate, bindSmsContent, requestDto.getSendDt());

            // 필터링
            SmsResult smsResult = smsFilter.filter(sms);

            // TODO sms 처리결과를 세팅
            sms.setSmsResult(smsResult);

            // TODO db단에서 bulk로 insert 할지 vs java단에서 for문 돌려서 insert 할지
            jpaSmsRepository.save(sms).getSmsId();
        }
        return true;
    }

    /**
     * sms 발송 목록을 조회하는 서비스
     *
     * @param startDt yyyyMMddHHmm 형식
     * @param endDt yyyyMMddHHmm 형식
     */
    @Transactional
    public List<SmsFindListResponseDto> findSmsList(String startDt, String endDt) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyyMMddHHmm");
        LocalDateTime startLdt = LocalDateTime.parse(startDt,format);
        LocalDateTime endLdt = LocalDateTime.parse(endDt,format);
        return jpaSmsRepository.findAllBySendDtBetween(startLdt, endLdt).stream()
                .map(SmsFindListResponseDto::new)
                .collect(Collectors.toList());
    }
}
