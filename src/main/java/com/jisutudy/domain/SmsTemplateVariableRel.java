package com.jisutudy.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SmsTemplateVariableRel {

    @EmbeddedId
    private SmsTemplateVariableRelPK smsTemplateVariableRelPK;

    private SmsTemplateVariableRel(SmsTemplateVariableRelPK smsTemplateVariableRelPK) {
        this.smsTemplateVariableRelPK = smsTemplateVariableRelPK;
    }

    public static SmsTemplateVariableRel createTemplate(SmsTemplateVariableRelPK pk) {
        SmsTemplateVariableRel smsTemplateVariableRel = new SmsTemplateVariableRel(pk);
        return smsTemplateVariableRel;
    }



}
