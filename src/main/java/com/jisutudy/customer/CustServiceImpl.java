package com.jisutudy.customer;

public class CustServiceImpl implements CustService{

    private final CustRepository repository = new MemoryCustRepository();

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
