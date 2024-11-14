package com.jisutudy.domain.customer.springstudy;

import com.jisutudy.domain.customer.Cust;

public interface CustService {
    void join(Cust cust);
    Cust findMember(Long custId);
    Cust findMember(String phoneNumber);
}
