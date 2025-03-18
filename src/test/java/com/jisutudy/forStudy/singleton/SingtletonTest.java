package com.jisutudy.forStudy.singleton;

import com.jisutudy.service.SmsService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class SingtletonTest {

    @Autowired ApplicationContext ac;

    @Test
    @DisplayName("스프링 컨테이너와 싱글톤")
    void springContainer() {

        //1. 조회: 호출할 때마다 같은 객체를 반환
        SmsService smsService1 = ac.getBean(SmsService.class);

        //2. 조회: 호출할 때마다 같은 객체를 반환
        SmsService smsService2 = ac.getBean(SmsService.class);

        //참조값이 같은 것을 확인
        assertThat(smsService1).isSameAs(smsService2);

    }

}
