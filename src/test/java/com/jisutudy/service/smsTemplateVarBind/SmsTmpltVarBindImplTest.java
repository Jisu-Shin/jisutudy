package com.jisutudy.service.smsTemplateVarBind;

import com.jisutudy.domain.SmsTemplate;
import com.jisutudy.domain.SmsTmpltVarRel;
import com.jisutudy.domain.TemplateVariable;
import com.jisutudy.domain.TemplateVariableType;
import com.jisutudy.domain.SmsType;
import com.jisutudy.service.SmsTemplateService;
import com.jisutudy.dto.SmsSendRequestDto;
import com.jisutudy.dto.SmsTemplateRequestDto;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
@Transactional
class SmsTmpltVarBindImplTest {
    
    @Autowired
    EntityManager em;
    
    @Autowired
    SmsTemplateService smsTemplateService;

    @Autowired
    SmsTmpltVarBinder smsTmpltVarBinder;

    @Autowired
    ApplicationContext ac;
    
    @Test
    public void 템플릿enum() throws Exception {
        //given
        Arrays.stream(TemplateVariableType.values())
                .collect(Collectors.toMap(e -> e, TemplateVariableType::getClassName))
                .forEach((k, v) -> System.out.println(k + ":" + v));


        //when

        //then
    }
    
    @Test
    public void 템플릿추출() throws Exception {
        //given

        createTmpltVar("custName", "고객명", TemplateVariableType.CUST);
        createTmpltVar("performanceName", "공연명", TemplateVariableType.ITEM);
        createTmpltVar("performanceName", "공연일시", TemplateVariableType.ITEM);
        Long smsTmpltId = createSmsTmplt("#{고객명} #{공연명} #{공연일시}", SmsType.INFORMAITONAL);

        SmsTemplate smsTemplate = em.find(SmsTemplate.class, smsTmpltId);

        List<TemplateVariable> tmpltVarList = smsTemplate.getTmpltVarRelList().stream()
                .filter(rel -> rel.getTemplateVariable().getVariableType() == TemplateVariableType.ITEM)
                .map(SmsTmpltVarRel::getTemplateVariable)
                .collect(Collectors.toList());
//                .forEach(s -> System.out.println(s));
        
        //when
    
        //then
    }

    @Test
    public void 템플릿바인딩() throws Exception {
        //given
        createTmpltVar("custName", "고객명", TemplateVariableType.CUST);
        Long smsTmpltId = createSmsTmplt("#{고객명}", SmsType.INFORMAITONAL);

        SmsTemplate smsTemplate = em.find(SmsTemplate.class, smsTmpltId);

        Long custId = 1L;

        //when
        BindingDto bindingDto = BindingDto.create(custId, new SmsSendRequestDto());
        String result = smsTmpltVarBinder.bind(smsTemplate, bindingDto);
        System.out.println(result);

        //then
        assertEquals("홍길동", result);
    }

    private TemplateVariable createTmpltVar(String enText, String koTet, TemplateVariableType type) {
        TemplateVariable templateVariable = TemplateVariable.create(enText, koTet, type);
        em.persist(templateVariable);
        return templateVariable;
    }

    private Long createSmsTmplt(String templateContent, SmsType smsType) {
        SmsTemplateRequestDto requestDto = new SmsTemplateRequestDto(templateContent, smsType);
        Long smsTmpltId = smsTemplateService.create(requestDto);

        return smsTmpltId;
    }

}