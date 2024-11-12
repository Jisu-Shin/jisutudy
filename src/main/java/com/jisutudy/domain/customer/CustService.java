package com.jisutudy.domain.customer;

public interface CustService {
    void join(Cust cust);
    Cust findMember(Long custId);
    Cust findMember(String phoneNumber);
}
