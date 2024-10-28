package com.jisutudy.customer;

// 고객 엔티티
public class Cust {
    private Long id;
    private String phoneNumber;
    private MsgConsentType msgConsentType;

    public Cust(Long id, String phoneNumber, MsgConsentType msgConsentType) {
        this.id = id;
        this.phoneNumber = phoneNumber;
        this.msgConsentType = msgConsentType;
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

    public MsgConsentType getMsgConsentType() {
        return msgConsentType;
    }

    public void setMsgConsentType(MsgConsentType msgConsentType) {
        this.msgConsentType = msgConsentType;
    }
}
