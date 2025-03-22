package com.jisutudy.domain;

import lombok.Getter;

@Getter
public enum TemplateVariableType {
    CUST("고객", "CustVariableBindImpl")
    ,ITEM("공연", "itemVariableBindImpl")
    ;

    private final String displayName;
    private final String className;

    TemplateVariableType(String displayName, String className){
        this.displayName = displayName;
        this.className = className;
    }
}
