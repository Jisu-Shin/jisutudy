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
        System.out.println("size = " + jpaSmsTemplateRepository.findAll().size());

        createTemplateVariable("custName", "고객명", TemplateVariableType.CUST);
        createTemplateVariable("performanceName", "공연명", TemplateVariableType.ITEM);

        SmsTemplateRequestDto requestDto = new SmsTemplateRequestDto("#{고객명}님 안녕하세요 #{공연명}은 ...", SmsType.INFORMAITONAL);

        //when
        Long tmpltId = smsTemplateService.create(requestDto);

        em.flush(); // DB insert 쿼리 넣기
        em.clear(); // 영속성 컨텍스트 초기화해서 1차 캐시도 비움

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

    @Test
    public void 문자발송테스트() throws Exception {
        //given

        //when

        //then
    }

}