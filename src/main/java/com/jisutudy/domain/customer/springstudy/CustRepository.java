package com.jisutudy.domain.customer.springstudy;

public interface CustRepository {
    void save(Cust cust);
    Cust findById(Long custId);
    Cust findByPhoneNumber(String phoneNumber);
}
