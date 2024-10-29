package com.jisutudy.customer;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MemoryCustRepository implements CustRepository{

    // 동시성 이슈로 HashMap대신 ConcurrentHashMap 사용
    private static Map<Long, Cust> store = new ConcurrentHashMap<>();

    @Override
    public void save(Cust cust) {
        store.put(cust.getId(), cust);
    }

    @Override
    public Cust findById(Long custId) {
        return store.get(custId);
    }

    @Override
    public Cust findByPhoneNumber(String phoneNumber) {
        Cust findCust = null;
        for (Cust c : store.values()) {
            if (c.getPhoneNumber().equals(phoneNumber)) {
                findCust = c;
            }
        }
        return findCust;
    }
}
