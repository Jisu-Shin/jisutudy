package com.jisutudy.forStudy.autowired;

import com.jisutudy.AppConfig;
import com.jisutudy.domain.sms.filter.TimeSmsFilter;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class AllBeanTest {

    @Test
    void findAllBean() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class, FilterService.class);
        FilterService filterService = ac.getBean(FilterService.class);
        LocalDateTime ldt = LocalDateTime.of(LocalDate.now(), LocalTime.of(21,00));
        boolean testResult = filterService.filter(ldt, "testTimeSmsFilter");
        boolean prodResult = filterService.filter(ldt, "prodTimeSmsFilter");

        System.out.println("testResult = " + testResult);
        System.out.println("prodResult = " + prodResult);

        assertThat(testResult).isTrue();
        assertThat(prodResult).isFalse();

    }

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
