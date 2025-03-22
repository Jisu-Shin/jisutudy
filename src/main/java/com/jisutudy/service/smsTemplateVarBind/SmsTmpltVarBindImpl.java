package com.jisutudy.service.smsTemplateVarBind;

import com.jisutudy.domain.SmsTemplate;
import com.jisutudy.domain.TemplateVariable;
import com.jisutudy.domain.TemplateVariableType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class SmsTmpltVarBindImpl implements SmsTmpltVarBinder {

    private final Map<String, VariableBinder> variableBinderMap;

    @Override
    public String bind() {
        // 템플릿포매터 클래스 생성

        String smsTemplate = "";

        // for 문 -----
        TemplateVariable variable = null;
        // TODO 반복적인 문장인데 조금 더 .. 클린 코드할 수 있는 방법은?
        VariableBinder variableBinder = null;
        if (TemplateVariableType.CUST == variable.getVariableType()) {
            variableBinder = variableBinderMap.get(TemplateVariableType.CUST.getClassName());
            
        } else if (TemplateVariableType.ITEM == variable.getVariableType()) {
            variableBinder = variableBinderMap.get(TemplateVariableType.ITEM.getClassName());
        }

        Map<String, String> replacements = new HashMap<>();
        Map<String, String> findValues = variableBinder.getValues();
        findValues.forEach((key,value) -> replacements.put(key,value));

        //------

        for (Map.Entry<String, String> entry : replacements.entrySet()) {
            smsTemplate = smsTemplate.replace("#{" + entry.getKey() + "}", entry.getValue());
        }

        return smsTemplate;
    }
}
