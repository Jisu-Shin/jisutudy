package com.jisutudy.domain.customer;

// 고객 엔티티
import com.jisutudy.domain.BaseTimeEntity;
import com.jisutudy.domain.Booking;
import com.jisutudy.domain.sms.Sms;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Cust extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="cust_id")
    private Long id;

    private String name;

    @Column(length = 12)
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private CustSmsConsentType smsConsentType;

    @OneToMany(mappedBy = "cust")
    private List<Booking> bookingList = new ArrayList<>();

    @OneToMany(mappedBy = "cust")
    private List<Sms> smsList = new ArrayList<>();

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
