package com.jisutudy.forStudy.scan;

import com.jisutudy.domain.customer.springstudy.CustService;
import com.jisutudy.service.SmsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class AppConfigTest {

    @Autowired ApplicationContext ac;

    @Test
    void basicScan() {
//        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        SmsService smsService = ac.getBean(SmsService.class);
        CustService custService = ac.getBean(CustService.class);

        for (String beanName : ac.getBeanDefinitionNames()){
            System.out.println("beanName = " + beanName);
        }
        
        assertThat(smsService).isInstanceOf(SmsService.class);
        assertThat(custService).isInstanceOf(CustService.class);

    }
}
