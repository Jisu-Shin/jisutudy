package com.jisutudy.forStudy.autowired;

import com.jisutudy.IntegrationTestConfig;
import com.jisutudy.service.filter.TimeSmsFilter;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class AllBeanTest {

    @Test
    void findAllBean() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(IntegrationTestConfig.class, FilterService.class);
        FilterService filterService = ac.getBean(FilterService.class);
        LocalDateTime ldt = LocalDateTime.of(LocalDate.now(), LocalTime.of(21,00));
        boolean prodResult = filterService.filter(ldt, "prodTimeSmsFilter");
        System.out.println("prodResult = " + prodResult);
        assertThat(prodResult).isFalse();

//        boolean testResult = filterService.filter(ldt, "testTimeSmsFilter");
//        System.out.println("testResult = " + testResult);
//        assertThat(testResult).isTrue();

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
}
