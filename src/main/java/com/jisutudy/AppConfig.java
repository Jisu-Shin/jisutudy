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

public class AppConfig {
    public SmsService smsService() {
        return new SmsServiceImpl(
                getSmsRepository(),
                getCustRepository(),
                smsFilter()
        );
    }

    public CustService custService() {
        return new CustServiceImpl(getCustRepository());
    }

    public SmsFilter smsFilter() {
        return  new SmsFilterImpl(
                getSmsRepository(),
                getCustRepository()
        );
    }

    public CustRepository getCustRepository() {
        return new MemoryCustRepository();
    }

    public SmsRepository getSmsRepository() {
        return new MemorySmsRepository();
    }

}
