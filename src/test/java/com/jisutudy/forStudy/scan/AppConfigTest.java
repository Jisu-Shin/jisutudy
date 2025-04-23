package com.jisutudy.forStudy.scan;

import com.jisutudy.service.SmsService;
import com.jisutudy.service.SmsTemplateService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("prod")
public class AppConfigTest {

    @Autowired
    ApplicationContext ac;

    @Test
    void basicScan() {
//        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        SmsService smsService = ac.getBean(SmsService.class);
        SmsTemplateService smsTemplateService = ac.getBean(SmsTemplateService.class);

        for (String beanName : ac.getBeanDefinitionNames()){
            System.out.println("beanName = " + beanName);
        }
        
        assertThat(smsService).isInstanceOf(SmsService.class);
        assertThat(smsTemplateService).isInstanceOf(SmsTemplateService.class);

    }
}
