package com.jisutudy.domain.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustServiceImpl implements CustService {

    private final CustRepository repository;

    @Autowired
    public CustServiceImpl(CustRepository custRepository) {
        this.repository = custRepository;
    }

    @Override
    public void join(Cust cust) {
        repository.save(cust);
    }

    @Override
    public Cust findMember(Long custId) {
        return repository.findById(custId);
    }

    @Override
    public Cust findMember(String phoneNumber) {
        return repository.findByPhoneNumber(phoneNumber);
    }
}
