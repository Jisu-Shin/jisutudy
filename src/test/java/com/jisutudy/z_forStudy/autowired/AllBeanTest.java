package com.jisutudy.z_forStudy.autowired;

import com.jisutudy.service.filter.timeSmsFilter.ProdTimeSmsFilter;
import com.jisutudy.service.filter.timeSmsFilter.TestTimeSmsFilter;
import com.jisutudy.service.filter.timeSmsFilter.TimeSmsFilter;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class AllBeanTest {

    @Test
    void findAllBean() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestAppConfig.class, FilterService.class);
        FilterService filterService = ac.getBean(FilterService.class);
        LocalDateTime ldt = LocalDateTime.of(LocalDate.now(), LocalTime.of(21,00));
        boolean prodResult = filterService.filter(ldt, "prodTimeSmsFilter");
        System.out.println("prodResult = " + prodResult);
        assertThat(prodResult).isFalse();

        boolean testResult = filterService.filter(ldt, "testTimeSmsFilter");
        System.out.println("testResult = " + testResult);
        assertThat(testResult).isTrue();

    }

    @TestConfiguration
    @RequiredArgsConstructor
    static class FilterService {
        private final Map<String, TimeSmsFilter> filterMap;
        private final List<TimeSmsFilter> filterList;

        public boolean filter(LocalDateTime ldt, String filterCode) {
            TimeSmsFilter timeSmsFilter = filterMap.get(filterCode);

            System.out.println("filtercode = " + filterCode);
            System.out.println("timeSmsFilter = " + timeSmsFilter);

            return timeSmsFilter.isSendable(ldt);
        }
    }

    @TestConfiguration
    static class TestAppConfig {
        @Bean
        public ProdTimeSmsFilter prodTimeSmsFilter() {
            return new ProdTimeSmsFilter();
        }

        @Bean
        public TestTimeSmsFilter testTimeSmsFilter() {
            return new TestTimeSmsFilter();
        }
    }
}
