package com.jisutudy;

import com.jisutudy.service.filter.ProdTimeSmsFilter;
import com.jisutudy.service.filter.TimeSmsFilter;
import org.springframework.context.annotation.*;

@Configuration
@ComponentScan(
        basePackages = "com.jisutudy")
@PropertySource("classpath:application.properties")
public class TestAppConfig {

//    @Bean
//    public static PropertySourcesPlaceholderConfigurer propertyConfig() {
//        return new PropertySourcesPlaceholderConfigurer();
//    }

    @Bean
    public TimeSmsFilter timeSmsFilter() {
        return new ProdTimeSmsFilter();
    }
}