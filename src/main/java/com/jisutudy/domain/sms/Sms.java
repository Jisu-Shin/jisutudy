package com.jisutudy.domain.sms;

import com.jisutudy.domain.BaseTimeEntity;
import com.jisutudy.domain.SmsTemplate;
import com.jisutudy.domain.customer.Cust;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static jakarta.persistence.FetchType.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Sms extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long smsId;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "cust_id")
    private Cust cust;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "sms_tmplt_id")
    private SmsTemplate smsTemplate;

    private String sendPhoneNumber;
    private String smsContent;
    private LocalDateTime sendDt;

    @Enumerated(EnumType.STRING)
    private SmsResult smsResult;

    private Sms(String smsContent, String sendDt) {
        this.smsContent = smsContent;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmm");
        this.sendDt = LocalDateTime.parse(sendDt,formatter);
    }

    // == 생성 메서드 ==
    public static Sms createSms(Cust cust, SmsTemplate smsTemplate, String smsContent, String sendDt) {
        Sms sms = new Sms(smsContent, sendDt);
        sms.setCust(cust);
        sms.setSmsTemplate(smsTemplate);
        sms.setSendPhoneNumber(cust.getPhoneNumber());

        return sms;
    }

    // ==연관관계메서드==
    private void setCust(Cust cust) {
        this.cust = cust;
    }

    private void setSmsTemplate(SmsTemplate smsTemplate) {
        this.smsTemplate = smsTemplate;
    }

    //== setter==
    private void setSendPhoneNumber(String phoneNumber){
        this.sendPhoneNumber = phoneNumber;
    }

    public void setSmsResult(SmsResult smsResult) {
        this.smsResult = smsResult;
    }
}
