package com.jisutudy.domain.sms;

import com.jisutudy.domain.BaseTimeEntity;
import com.jisutudy.domain.customer.Cust;
import com.jisutudy.domain.customer.JpaCustRepository;
import com.jisutudy.domain.sms.filter.SmsFilter;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.Optional;

import static com.jisutudy.domain.sms.QSms.sms;

@Getter
@NoArgsConstructor
@Entity
public class Sms extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long smsId;
    private Long custId;
    private String sendPhoneNumber;
    private String smsContent;
    private LocalDateTime sendDt;
    private SmsType smsType;
    private SmsResult smsResult;

    @Builder
    public Sms(Long custId, String smsContent, LocalDateTime sendDt, SmsType smsType) {
        this.custId = custId;
        this.smsContent = smsContent;
        this.sendDt = sendDt;
        this.smsType = smsType;
    }

    public Sms(Long smsId, Long custId, String sendPhoneNumber, String smsContent, LocalDateTime sendDt, SmsType smsType) {
        this.smsId = smsId;
        this.custId = custId;
        this.sendPhoneNumber = sendPhoneNumber;
        this.smsContent = smsContent;
        this.sendDt = sendDt;
        this.smsType = smsType;
    }

    public void setCustPhoneNumber(String phoneNumber){
        this.sendPhoneNumber = phoneNumber;
    }

    public void setSmsResult(SmsResult smsResult) {
        this.smsResult = smsResult;
    }
}
