package com.jisutudy.forStudy.dependencyInjection;

import com.jisutudy.service.SmsTemplateService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = DITestConfig.class)
public class DIPerformanceTest {

    @Autowired
    private SmsTemplateService constructorSmsTmpltService;

    @Autowired
    private SetterInjectionSmsTmpltService setterInjectionSmsTmpltService;

    @Autowired
    private FieldInjectionSmsTmpltService fieldInjectionSmsTmpltService;

    @Autowired
    private MethodInjectionSmsTmpltService methodInjectionSmsTmpltService;
    
    @Test
    @DisplayName("DI 방식별 생명주기 로그 확인")
    public void testDILifeCycle() throws Exception {
        //given
        
        //when
    
        //then
    } 
}
