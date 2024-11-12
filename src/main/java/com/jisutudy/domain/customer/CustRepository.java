package com.jisutudy.domain.customer;

public interface CustRepository {
    void save(Cust cust);
    Cust findById(Long custId);
    Cust findByPhoneNumber(String phoneNumber);
}
