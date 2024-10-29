package com.jisutudy.customer;

// 고객 엔티티
public class Cust {
    private Long id;
    private String phoneNumber;
    private CustSmsConsentType smsConsentType;

    public Cust(Long id, String phoneNumber, CustSmsConsentType smsConsentType) {
        this.id = id;
        this.phoneNumber = phoneNumber;
        this.smsConsentType = smsConsentType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public CustSmsConsentType getMsgConsentType() {
        return smsConsentType;
    }

    public void setMsgConsentType(CustSmsConsentType smsConsentType) {
        this.smsConsentType = smsConsentType;
    }
}
