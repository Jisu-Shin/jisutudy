package com.jisutudy.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SmsTmpltVarRel {

    @EmbeddedId
    private SmsTmpltVarRelId smsTmpltVarRelId;

    @MapsId("smsTmpltId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sms_tmplt_id")
    private SmsTemplate smsTemplate;

    @MapsId("tmpltVarId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tmplt_var_id")
    private TemplateVariable templateVariable;

    private SmsTmpltVarRel(SmsTmpltVarRelId smsTmpltVarRelId) {
        this.smsTmpltVarRelId = smsTmpltVarRelId;
    }

    // == 연관관계 메서드 ==
    public void setSmsTemplate(SmsTemplate smsTemplate) {
        this.smsTemplate = smsTemplate;
        this.smsTemplate.getTmpltVarRelList().add(this);
    }

    public void setTemplateVariable(TemplateVariable templateVariable) {
        this.templateVariable = templateVariable;
        this.templateVariable.getTmpltVarRelList().add(this);
    }


    public static SmsTmpltVarRel create(SmsTemplate smsTemplate, TemplateVariable templateVariable) {
        SmsTmpltVarRelId id = new SmsTmpltVarRelId(smsTemplate.getId(), templateVariable.getId());

        SmsTmpltVarRel smsTmpltVarRel = new SmsTmpltVarRel(id);
        smsTmpltVarRel.setSmsTemplate(smsTemplate);
        smsTmpltVarRel.setTemplateVariable(templateVariable);

        return smsTmpltVarRel;
    }
}
