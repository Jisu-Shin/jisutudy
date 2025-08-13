package com.jisutudy.forStudy.autowired;

import com.jisutudy.domain.sms.Sms;
import jakarta.annotation.Nullable;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Optional;

public class AutoWiredTest {
    @Test
    public void AutoWiredOption() throws Exception {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);

    }
    static class TestBean {
        @Autowired(required = false)
        public void setNoBean1(Sms sms) {
            System.out.println("setNoBean1 = " + sms);
        }

        @Autowired
        public void setNoBean2(@Nullable Sms sms) {
            System.out.println("setNoBean2 = " + sms);
        }

        @Autowired
        public void setNoBean3(Optional<Sms> sms) {
            System.out.println("setNoBean3 = " + sms);
        }
    }
}
