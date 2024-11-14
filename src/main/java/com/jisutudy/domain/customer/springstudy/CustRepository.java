package com.jisutudy.domain.customer.springstudy;

import com.jisutudy.domain.customer.Cust;

public interface CustRepository {
    void save(Cust cust);
    Cust findById(Long custId);
    Cust findByPhoneNumber(String phoneNumber);
}
