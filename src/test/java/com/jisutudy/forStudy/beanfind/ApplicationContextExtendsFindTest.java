package com.jisutudy.forStudy.beanfind;

import com.jisutudy.AppConfig;
import com.jisutudy.service.filter.timeSmsFilter.ProdTimeSmsFilter;
import com.jisutudy.service.filter.timeSmsFilter.TestTimeSmsFilter;
import com.jisutudy.service.filter.timeSmsFilter.TimeSmsFilter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ApplicationContextExtendsFindTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("부모 타입으로 조회시, 자식이 둘 이상 있으면, 중복 오류가 발생한다")
    public void findBeanByParentTypeDuplicate() throws Exception {
        assertThrows(NoUniqueBeanDefinitionException.class, () -> ac.getBean(TimeSmsFilter.class));
    }

    @Test
    @DisplayName("부모 타입으로 조회시, 자식이 둘 이상 있으면 빈 이름을 지정하면 된다")
    public void findBeanByParentTypeBeanName() throws Exception {
        TimeSmsFilter prodTimeSmsFilter = ac.getBean("prodTimeSmsFilter", TimeSmsFilter.class);
        assertThat(prodTimeSmsFilter).isInstanceOf(ProdTimeSmsFilter.class);
    }

    @Test
    @DisplayName("특정 하위 타입으로 조회")
    public void findBeanBySubType() throws Exception {
        TestTimeSmsFilter bean = ac.getBean(TestTimeSmsFilter.class);
        assertThat(bean).isInstanceOf(TestTimeSmsFilter.class);
    }

    @Test
    @DisplayName("부모 타입으로 모두 조회하기")
    public void findAllBeanByParentType() throws Exception {
        Map<String, TimeSmsFilter> beansOfType = ac.getBeansOfType(TimeSmsFilter.class);
        assertThat(beansOfType.size()).isEqualTo(3);
        for (String key : beansOfType.keySet()) {
            System.out.println("key = " + key + " value = " + beansOfType.get(key));
        }
    }

    @Test
    @DisplayName("부모 타입으로 모두 조회하기 - Object")
    public void findAllBeanByObjectType() throws Exception {
        Map<String, Object> beansOfType = ac.getBeansOfType(Object.class);
        for (String key : beansOfType.keySet()) {
            System.out.println("key = " + key + " value = " + beansOfType.get(key));
        }
    }

}
