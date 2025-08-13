package com.jisutudy.forStudy.beanfind;

import com.jisutudy.service.filter.customerSmsFilter.CustConsentFilter;
import com.jisutudy.service.filter.customerSmsFilter.CustomerSmsFilter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ApplicationContextSameBeanFindTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SameBeanConfig.class);

    @Test
    @DisplayName("타입으로 조회시 같은 타입이 둘 이상 있으면, 중복 오류가 발생한다")
    public void findBeanByTypeDuplicate() throws Exception {
        assertThrows(NoUniqueBeanDefinitionException.class, () -> ac.getBean(CustomerSmsFilter.class));
    }

    @Test
    @DisplayName("타입으로 조회시 같은 타입이 둘 이상 있으면, 빈 이름을 지정하면 된다")
    public void findBeanByName() throws Exception {
        CustomerSmsFilter customerSmsFilter = ac.getBean("customerSmsFilter1", CustomerSmsFilter.class);
        assertThat(customerSmsFilter).isInstanceOf(CustomerSmsFilter.class);
    }

    @Test
    public void findAllBeanByType() throws Exception {
        Map<String, CustomerSmsFilter> beansOfType = ac.getBeansOfType(CustomerSmsFilter.class);
        for (String key : beansOfType.keySet()) {
            System.out.println("key = " + key + " value = " + beansOfType.get(key));
            System.out.println("beansOfType = " + beansOfType);
            assertThat(beansOfType.size()).isEqualTo(2);
        }
    }

    @TestConfiguration
    static class SameBeanConfig {

        @Bean
        public CustomerSmsFilter customerSmsFilter1() {
            return new CustConsentFilter();
        }

        @Bean
        public CustomerSmsFilter customerSmsFilter2() {
            return new CustConsentFilter();
        }
    }
}
