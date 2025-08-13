package com.jisutudy.forStudy.dependencyInjection;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.test.context.TestComponent;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

@TestConfiguration
@ComponentScan(
        basePackages = "com.jisutudy",
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = TestConfiguration.class)
)
@Slf4j
public class DITestConfig {

    @Bean
    public SetterInjectionSmsTmpltService setterInjectionSmsTmpltService() {
        return new SetterInjectionSmsTmpltService();
    }

    @Bean
    public FieldInjectionSmsTmpltService fieldInjectionSmsTmpltService() {
        return new FieldInjectionSmsTmpltService();
    }

    @Bean
    public MethodInjectionSmsTmpltService methodInjectionSmsTmpltService() {
        return new MethodInjectionSmsTmpltService();
    }
}
