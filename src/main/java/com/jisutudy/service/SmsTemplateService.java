package com.jisutudy.service;

import com.jisutudy.domain.SmsTemplate;
import com.jisutudy.domain.TemplateVariable;
import com.jisutudy.repository.*;
import com.jisutudy.dto.SmsTemplateListResponseDto;
import com.jisutudy.dto.SmsTemplateRequestDto;
import com.jisutudy.service.smsTemplateVarBind.TemplateVariableUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SmsTemplateService {

    private final JpaSmsTemplateRepository smsTmpltRepository;
    private final JpaTemplateVariableRepository tmpltVarRepository;

    // 템플릿 추가
    @Transactional
    public Long create(SmsTemplateRequestDto requestDto) {
        SmsTemplate smsTemplate = SmsTemplate.createSmsTemplate(requestDto.getTemplateContent(), requestDto.getSmsType());

        // sms템플릿에서 변수찾기
        List<String> koTextList = TemplateVariableUtils.extractVariabels(requestDto.getTemplateContent());
        addRelation(koTextList, smsTemplate);

        smsTmpltRepository.save(smsTemplate);
        return smsTemplate.getId();
    }

    // todo 템플릿 수정
    @Transactional
    public void update() {

    }

    public List<SmsTemplateListResponseDto> findAll() {
        return smsTmpltRepository.findAll().stream()
                .map(SmsTemplateListResponseDto::new)
                .collect(Collectors.toList());
    }

    private void addRelation(List<String> koTextList, SmsTemplate smsTemplate) {
        if (!koTextList.isEmpty()) {
            for (String koText : koTextList) {
                // 템플릿 변수 검증
                TemplateVariable tmpltVar = tmpltVarRepository.findByKoText(koText)
                        .orElseThrow(() -> new IllegalArgumentException("해당 템플릿 변수는 없습니다 : " + koText));
                smsTemplate.addRelation(tmpltVar);
            }
        }
    }
}
