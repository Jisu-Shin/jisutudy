package com.jisutudy;

import com.jisutudy.service.filter.customerSmsFilter.CustConsentFilter;
import com.jisutudy.service.filter.customerSmsFilter.CustomerSmsFilter;
import com.jisutudy.service.filter.timeSmsFilter.ProdTimeSmsFilter;
import com.jisutudy.service.filter.timeSmsFilter.TimeSmsFilter;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.*;

@TestConfiguration
@ComponentScan(
        basePackages = "com.jisutudy.service.filter")
@PropertySource("classpath:application.properties")
public class IntegrationTestConfig {

    @Bean
    public TimeSmsFilter timeSmsFilter() {
        return new ProdTimeSmsFilter();
    }

    @Bean
    public CustomerSmsFilter customerSmsFilter() {return new CustConsentFilter(); }
}