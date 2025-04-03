package com.jisutudy.service.smsTemplateVarBind;

import com.jisutudy.domain.SmsTemplate;
import com.jisutudy.domain.TemplateVariable;
import com.jisutudy.domain.TemplateVariableType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class SmsTmpltVarBindImpl implements SmsTmpltVarBinder {

    private final Map<String, VariableBinder> variableBinderMap;

    @Override
    public String bind(SmsTemplate smsTemplate, BindingDto bindingDto) {

        if (smsTemplate.getTmpltVarRelList().size() == 0) {
            return smsTemplate.getTemplateContent();
        }

        Map<String, String> replacements = new HashMap<>();

//         템플릿변수... 치환
        // TODO 반복적인 문장인데 조금 더 .. 클린 코드할 수 있는 방법은?
        // TODO QueryDsl 이랑 Stream 비교해보기

        Map<TemplateVariableType, List<TemplateVariable>> grouping = smsTemplate.getTmpltVarRelList().stream().
                collect(Collectors.groupingBy(
                        rel -> rel.getTemplateVariable().getVariableType(),
                        Collectors.mapping(rel -> rel.getTemplateVariable(), Collectors.toList())
                ));

        for (Map.Entry<TemplateVariableType, List<TemplateVariable>> entry : grouping.entrySet()) {
            if (entry.getValue().size() > 0) {
                VariableBinder variableBinding = variableBinderMap.get(entry.getKey().getClassName());
                variableBinding.getValues(entry.getValue(), bindingDto).forEach((k, v) -> replacements.put(k, v));
            }
        }

        String target = smsTemplate.getTemplateContent();
        for (Map.Entry<String, String> entry : replacements.entrySet()) {
            target = target.replaceAll("#\\{" + entry.getKey() + "\\}", entry.getValue());
        }

        return target;
    }


}
