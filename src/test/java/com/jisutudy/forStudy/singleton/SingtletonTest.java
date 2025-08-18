package com.jisutudy.forStudy.singleton;

import com.jisutudy.service.filter.timeSmsFilter.ProdTimeSmsFilter;
import com.jisutudy.service.filter.timeSmsFilter.TimeSmsFilter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.assertj.core.api.Assertions.assertThat;

public class SingtletonTest {

    @Test
    @DisplayName("스프링 없는 순수한 DI 컨테이너")
    public void pureContainer() throws Exception {
        TestConfig config = new TestConfig();

        // 1. 조회: 호출할 때마다 객체를 생성
        TimeSmsFilter timeSmsFilter1 = config.liteTimeSmsFilter();

        // 2. 조회: 호출할 때마다 객체를 생성
        TimeSmsFilter timeSmsFilter2 = config.liteTimeSmsFilter();

        System.out.println("timeSmsFilter1 = " + timeSmsFilter1);
        System.out.println("timeSmsFilter2 = " + timeSmsFilter2);

        assertThat(timeSmsFilter1).isNotSameAs(timeSmsFilter2);
    }

    @Test
    @DisplayName("스프링 컨테이너와 싱글톤")
    public void springContainer() throws Exception {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);

        // 1. 조회: 호출할 때마다 객체를 생성
        TimeSmsFilter timeSmsFilter1 = ac.getBean(TimeSmsFilter.class);

        // 2. 조회: 호출할 때마다 객체를 생성
        TimeSmsFilter timeSmsFilter2 = ac.getBean(TimeSmsFilter.class);

        System.out.println("timeSmsFilter1 = " + timeSmsFilter1);
        System.out.println("timeSmsFilter2 = " + timeSmsFilter2);

        assertThat(timeSmsFilter1).isSameAs(timeSmsFilter2);
    }

    static class TestConfig {

        @Bean
        public TimeSmsFilter liteTimeSmsFilter() {
            return new ProdTimeSmsFilter();
        }
    }
}
