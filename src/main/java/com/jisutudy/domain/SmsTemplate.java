package com.jisutudy.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SmsTemplate {

    @Id
    @GeneratedValue
    @Column(name = "sms_tmplt_id")
    private Long id;

    private String templateContent;

    @Enumerated(EnumType.STRING)
    private SmsType smsType;

    @OneToMany(mappedBy = "smsTemplate", cascade = CascadeType.PERSIST)
    private List<SmsTmpltVarRel> tmpltVarRelList = new ArrayList<>();

    private SmsTemplate(String templateContent, SmsType smsType) {
        this.templateContent = templateContent;
        this.smsType = smsType;
    }

    // ==생성 메서드==
    public static SmsTemplate createSmsTemplate(String templateContent, SmsType smsType) {
        SmsTemplate smsTemplate = new SmsTemplate(templateContent, smsType);
        return smsTemplate;
    }

    // ==연관 메서드==
    public void addRelation(TemplateVariable tmpltVar) {
        this.tmpltVarRelList.add(SmsTmpltVarRel.create(this, tmpltVar));
    }
}
