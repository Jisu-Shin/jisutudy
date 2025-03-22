package com.jisutudy.domain;

import com.jisutudy.web.dto.TemplateVariableDto;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TemplateVariable {

    @Id @GeneratedValue
    @Column(name = "tmplt_var_id")
    private Long id;
    private String enText;
    private String koText;

    @Enumerated(EnumType.STRING)
    private TemplateVariableType variableType;

    private TemplateVariable(String enText, String koText, TemplateVariableType type) {
        this.enText = enText;
        this.koText = koText;
        this.variableType = type;
    }

    public static TemplateVariable createPlaceholder(TemplateVariableDto dto){
        TemplateVariable templateVariable = new TemplateVariable(dto.getEnText(), dto.getKoText(), dto.getVariableType());
        return templateVariable;
    }
}
