package com.jisutudy;

import com.jisutudy.customer.CustRepository;
import com.jisutudy.customer.CustService;
import com.jisutudy.customer.CustServiceImpl;
import com.jisutudy.customer.MemoryCustRepository;
import com.jisutudy.sms.MemorySmsRepository;
import com.jisutudy.sms.SmsRepository;
import com.jisutudy.sms.SmsService;
import com.jisutudy.sms.SmsServiceImpl;
import com.jisutudy.sms.filter.SmsFilter;
import com.jisutudy.sms.filter.SmsFilterImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public SmsService smsService() {
        return new SmsServiceImpl(
                smsRepository(),
                custRepository(),
                smsFilter()
        );
    }

    @Bean
    public CustService custService() {
        return new CustServiceImpl(custRepository());
    }

    @Bean
    public SmsFilter smsFilter() {
        return  new SmsFilterImpl(
                smsRepository(),
                custRepository()
        );
    }

    @Bean
    public CustRepository custRepository() {
        return new MemoryCustRepository();
    }

    @Bean
    public SmsRepository smsRepository() {
        return new MemorySmsRepository();
    }

}
