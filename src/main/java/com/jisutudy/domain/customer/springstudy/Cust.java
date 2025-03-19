package com.jisutudy.domain.customer.springstudy;

// 고객 엔티티
import com.jisutudy.domain.BaseTimeEntity;
import com.jisutudy.domain.Booking;
import com.jisutudy.domain.customer.CustSmsConsentType;
import com.jisutudy.domain.sms.Sms;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Cust extends BaseTimeEntity {

    private Long id;
    private String name;
    private String phoneNumber;
    private CustSmsConsentType smsConsentType;

    @Builder
    public Cust(String name, String phoneNumber, CustSmsConsentType smsConsentType) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.smsConsentType = smsConsentType;
    }

    public Cust(Long id, String name, String phoneNumber, CustSmsConsentType smsConsentType) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.smsConsentType = smsConsentType;
    }

    public void update(String phoneNumber, CustSmsConsentType type) {
        this.phoneNumber = phoneNumber;
        this.smsConsentType = type;
    }
}
