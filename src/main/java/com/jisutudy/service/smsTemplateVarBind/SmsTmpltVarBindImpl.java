package com.jisutudy.service.smsTemplateVarBind;

import com.jisutudy.domain.SmsTemplate;
import com.jisutudy.domain.SmsTmpltVarRel;
import com.jisutudy.domain.TemplateVariable;
import com.jisutudy.domain.TemplateVariableType;
import com.jisutudy.domain.customer.Cust;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class SmsTmpltVarBindImpl implements SmsTmpltVarBinder {

    private final Map<String, VariableBinder> variableBinderMap;

    @Override
    public String bind(SmsTemplate smsTemplate, Cust cust) {
        Map<String, String> replacements = new HashMap<>();

//         템플릿변수... 치환
        // TODO 반복적인 문장인데 조금 더 .. 클린 코드할 수 있는 방법은?
        // TODO QueryDsl 이랑 Stream 비교해보기
        Map<TemplateVariableType, String> tmpltVarMap = Arrays.stream(TemplateVariableType.values())
                .collect(Collectors.toMap(e -> e, TemplateVariableType::getClassName));

        for (Map.Entry<TemplateVariableType, String> entry : tmpltVarMap.entrySet()) {

            List<TemplateVariable> tmpltVarList = smsTemplate.getTmpltVarRelList().stream()
                    .filter(rel -> rel.getTemplateVariable().getVariableType() == entry.getKey())
                    .map(SmsTmpltVarRel::getTemplateVariable)
                    .collect(Collectors.toList());

            VariableBinder variableBinding = null;
            if (tmpltVarList.size() > 0) {
                variableBinding = variableBinderMap.get(entry.getValue());
                variableBinding.getValues(tmpltVarList, cust).forEach((k, v) -> replacements.put(k, v));
            }
        }

        String target = smsTemplate.getTemplateContent();
        for (Map.Entry<String, String> entry : replacements.entrySet()) {
            target = target.replaceAll("#\\{" + entry.getKey() + "\\}", entry.getValue());
        }

        return target;
    }


}
