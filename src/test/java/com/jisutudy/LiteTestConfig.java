package com.jisutudy;

import com.jisutudy.service.filter.ProdTimeSmsFilter;
import com.jisutudy.service.filter.TimeSmsFilter;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class LiteTestConfig {

    @Bean
    public TimeSmsFilter liteTimeSmsFilter() {
        return new ProdTimeSmsFilter();
    }
}
