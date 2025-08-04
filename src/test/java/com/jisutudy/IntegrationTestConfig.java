package com.jisutudy;

import com.jisutudy.service.filter.CustConsentFilter;
import com.jisutudy.service.filter.CustomerSmsFilter;
import com.jisutudy.service.filter.ProdTimeSmsFilter;
import com.jisutudy.service.filter.TimeSmsFilter;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.*;

@TestConfiguration
@ComponentScan(
        basePackages = "com.jisutudy")
@PropertySource("classpath:application.properties")
public class IntegrationTestConfig {

//    @Bean
//    public static PropertySourcesPlaceholderConfigurer propertyConfig() {
//        return new PropertySourcesPlaceholderConfigurer();
//    }

    @Bean
    public TimeSmsFilter timeSmsFilter() {
        return new ProdTimeSmsFilter();
    }

    @Bean
    public CustomerSmsFilter customerSmsFilter() {return new CustConsentFilter(); }
}