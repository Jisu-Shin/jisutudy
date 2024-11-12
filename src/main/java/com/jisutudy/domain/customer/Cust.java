package com.jisutudy.domain.customer;

// 고객 엔티티
import com.jisutudy.domain.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class Cust extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(length = 12)
    private String phoneNumber;

    @Enumerated
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
