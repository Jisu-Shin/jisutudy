package com.jisutudy.domain.sms;

import com.jisutudy.domain.BaseTimeEntity;
import com.jisutudy.domain.SmsTemplate;
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

    private Long custId;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "sms_tmplt_id")
    private SmsTemplate smsTemplate;

    private String sendPhoneNumber;
    private String smsContent;
    private LocalDateTime sendDt;

    @Enumerated(EnumType.STRING)
    private SmsResult smsResult;

    private Sms(Long custId, String smsContent, String sendDt, String sendPhoneNumber) {
        this.custId = custId;
        this.smsContent = smsContent;
        this.sendPhoneNumber = sendPhoneNumber;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmm");
        this.sendDt = LocalDateTime.parse(sendDt,formatter);
    }

    // == 생성 메서드 ==
    public static Sms createSms(Long custId, SmsTemplate smsTemplate, String smsContent, String sendDt, String phoneNumber) {
        Sms sms = new Sms(custId,smsContent, sendDt,phoneNumber);
        sms.setSmsTemplate(smsTemplate);

        return sms;
    }

    // ==연관관계메서드==
    private void setSmsTemplate(SmsTemplate smsTemplate) {
        this.smsTemplate = smsTemplate;
    }

    //== setter==

    public void setSmsResult(SmsResult smsResult) {
        this.smsResult = smsResult;
    }
}
