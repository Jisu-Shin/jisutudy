package com.jisutudy.service;

import com.jisutudy.domain.SmsTemplate;
import com.jisutudy.domain.sms.SmsType;
import com.jisutudy.repository.JpaSmsTemplateRepository;
import com.jisutudy.web.dto.SmsTemplateListResponseDto;
import com.jisutudy.web.dto.SmsTemplateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SmsTemplateService {

    private final JpaSmsTemplateRepository repository;

    // 템플릿 추가
    @Transactional
    public Long create(SmsTemplateRequestDto requestDto) {
        System.out.println(requestDto);
        SmsTemplate smsTemplate = SmsTemplate.createSmsTemplate(requestDto.getTemplateContent(), requestDto.getSmsType());
        repository.save(smsTemplate);
        return smsTemplate.getId();
    }

    // 템플릿 수정
    @Transactional
    public void update() {

    }

    public List<SmsTemplateListResponseDto> findAll() {
        return repository.findAll().stream()
                .map(SmsTemplateListResponseDto::new)
                .collect(Collectors.toList());
    }

}
