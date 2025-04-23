package com.jisutudy.service;

import com.jisutudy.domain.SmsTemplate;
import com.jisutudy.domain.TemplateVariable;
import com.jisutudy.domain.TemplateVariableType;
import com.jisutudy.domain.SmsType;
import com.jisutudy.repository.JpaSmsTemplateRepository;
import com.jisutudy.dto.SmsTemplateRequestDto;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class SmsTemplateServiceTest {

    @Autowired
    SmsTemplateService smsTemplateService;

    @Autowired
    JpaSmsTemplateRepository jpaSmsTemplateRepository;

    @Autowired
    EntityManager em;

    @Test
    public void 매치패턴() throws Exception {
        //given
        String target = "#{고객명} #{공연명}";

        String regEx = "#\\{(.*?)\\}";
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(target);

        //when
        System.out.println(matcher.groupCount());
        while(matcher.find()) {
            System.out.println("========");
            System.out.println(matcher.group(1));
        }

        //then
    }

    @Test
    public void 템플릿추가() throws Exception {
        //given
        createTemplateVariable("custName", "고객명", TemplateVariableType.CUST);
        createTemplateVariable("performanceName", "공연명", TemplateVariableType.ITEM);

        SmsTemplateRequestDto requestDto = new SmsTemplateRequestDto("#{고객명}님 안녕하세요 #{공연명}은 ...", SmsType.INFORMAITONAL);

        //when
        Long tmpltId = smsTemplateService.create(requestDto);
        SmsTemplate findTmplt = jpaSmsTemplateRepository.findById(tmpltId)
                .orElseThrow(()->new IllegalArgumentException());

        findTmplt.getTmpltVarRelList().stream()
                .forEach(f-> System.out.println(f.getTemplateVariable().getKoText()));

        //then
        assertEquals(2, findTmplt.getTmpltVarRelList().size());
    }

    private void createTemplateVariable(String enText, String koText, TemplateVariableType templateVariableType) {
        TemplateVariable tmpltVar = TemplateVariable.create(enText, koText, templateVariableType);
        em.persist(tmpltVar);
    }

}