package com.jisutudy.scan;

import com.jisutudy.AppConfig;
import com.jisutudy.domain.customer.springstudy.CustService;
import com.jisutudy.domain.sms.springstudy.SmsService;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

public class AppConfigTest {

    @Test
    void basicScan() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        SmsService smsService = ac.getBean(SmsService.class);
        CustService custService = ac.getBean(CustService.class);

        for (String beanName : ac.getBeanDefinitionNames()){
            System.out.println("beanName = " + beanName);
        }
        
        assertThat(smsService).isInstanceOf(SmsService.class);
        assertThat(custService).isInstanceOf(CustService.class);

    }
}
