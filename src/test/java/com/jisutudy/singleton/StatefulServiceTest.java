package com.jisutudy.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

public class StatefulServiceTest {

    @Test
    void statefulServiceSingleton() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean("statefulService", StatefulService.class);
        StatefulService statefulService2 = ac.getBean("statefulService", StatefulService.class);

        //ThreadA : A사용자 문자 "안녕하세요" 보냄
        statefulService1.send("안녕하세요");

        //ThreadB : B사용자 문자 "안녕히 계세요" 보냄
        statefulService2.send("안녕히 계세요");

        // ThreadA 문자 내용 조회
        // "안녕하세요" 를 기대했지만, 기대와 다르게 "안녕히 계세요" 출력
        String msg = statefulService1.getMsg();
        System.out.println("msg = " + msg);

        Assertions.assertThat(statefulService1.getMsg()).isNotEqualTo("안녕하세요");
    }

    static class TestConfig {
        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }
}
