package com.jisutudy.singleton;

import com.jisutudy.AppConfig;
import com.jisutudy.domain.sms.filter.TestTimeSmsFilter;
import com.jisutudy.domain.sms.springstudy.SmsService;
import com.jisutudy.domain.sms.springstudy.SmsServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class SingtletonTest {

    @Autowired ApplicationContext ac;

    @Test
    @DisplayName("스프링 컨테이너와 싱글톤")
    void springContainer() {
//        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        //1. 조회: 호출할 때마다 같은 객체를 반환
        SmsService smsService1 = ac.getBean(SmsService.class);

        //2. 조회: 호출할 때마다 같은 객체를 반환
        SmsService smsService2 = ac.getBean(SmsService.class);

        //참조값이 같은 것을 확인
        assertThat(smsService1).isSameAs(smsService2);

    }

}
