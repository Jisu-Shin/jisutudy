package com.jisutudy.service;

import com.jisutudy.domain.SmsTemplate;
import com.jisutudy.domain.TemplateVariable;
import com.jisutudy.repository.*;
import com.jisutudy.dto.SmsTemplateListResponseDto;
import com.jisutudy.dto.SmsTemplateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
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
        List<String> koTextList = findVariableByTemplateContent(requestDto.getTemplateContent());
        addRelation(koTextList, smsTemplate);

        smsTmpltRepository.save(smsTemplate);
        return smsTemplate.getId();
    }

    // 템플릿 수정
    @Transactional
    public void update() {

    }

    public List<SmsTemplateListResponseDto> findAll() {
        return smsTmpltRepository.findAll().stream()
                .map(SmsTemplateListResponseDto::new)
                .collect(Collectors.toList());
    }

    private List<String> findVariableByTemplateContent(String content) {
        List<String> varList = new ArrayList<>();

        String regEx = "#\\{(.*?)\\}";
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(content);

        //when
        while(matcher.find()) {
            varList.add(matcher.group(1));
        }

        return varList;
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
