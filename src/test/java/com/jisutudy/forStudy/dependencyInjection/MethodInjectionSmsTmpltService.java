package com.jisutudy.forStudy.dependencyInjection;

import com.jisutudy.domain.SmsTemplate;
import com.jisutudy.domain.TemplateVariable;
import com.jisutudy.dto.SmsTemplateRequestDto;
import com.jisutudy.repository.JpaSmsTemplateRepository;
import com.jisutudy.repository.JpaTemplateVariableRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class MethodInjectionSmsTmpltService {
    private JpaSmsTemplateRepository smsTmpltRepository;
    private JpaTemplateVariableRepository tmpltVarRepository;

    @Autowired
    public void injectDenpendencies(JpaSmsTemplateRepository smsTmpltRepository, JpaTemplateVariableRepository tmpltVarRepository) {
        this.smsTmpltRepository = smsTmpltRepository;
        this.tmpltVarRepository = tmpltVarRepository;

        log.info("=== 일반 메서드 방식 주입 ===");
    }

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
