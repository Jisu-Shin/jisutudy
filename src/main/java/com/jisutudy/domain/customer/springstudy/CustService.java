package com.jisutudy.domain.customer.springstudy;


public interface CustService {
    void join(Cust cust);
    Cust findMember(Long custId);
    Cust findMember(String phoneNumber);
}
