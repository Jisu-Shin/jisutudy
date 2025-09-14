package com.jisutudy.z_forStudy.dependencyInjection;

import com.jisutudy.repository.JpaSmsTemplateRepository;
import com.jisutudy.repository.JpaTemplateVariableRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
@Slf4j
public class DITestConfig {

    @Autowired
    JpaSmsTemplateRepository jpaSmsTemplateRepository;

    @Autowired
    JpaTemplateVariableRepository jpaTemplateVariableRepository;

    @Bean
    public ConstructorInjectionService constructorInjectionService() {return new ConstructorInjectionService(jpaSmsTemplateRepository, jpaTemplateVariableRepository); }

    @Bean
    public SetterInjectionService setterInjectionSmsTmpltService() {
        return new SetterInjectionService();
    }

    @Bean
    public FieldInjectionService fieldInjectionSmsTmpltService() {
        return new FieldInjectionService();
    }

    @Bean
    public MethodInjectionService methodInjectionSmsTmpltService() {
        return new MethodInjectionService();
    }
}
