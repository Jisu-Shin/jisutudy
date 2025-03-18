package com.jisutudy.domain;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@EqualsAndHashCode
public class TemplatePlaceholderPK {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="sms_tmplt_id")
    private SmsTemplate smsTemplate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="plhd_id")
    private Placeholder placeholder;
}
