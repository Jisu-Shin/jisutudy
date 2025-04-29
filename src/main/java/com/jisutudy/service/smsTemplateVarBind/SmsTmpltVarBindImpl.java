package com.jisutudy.service.smsTemplateVarBind;

import com.jisutudy.domain.SmsTemplate;
import com.jisutudy.domain.TemplateVariable;
import com.jisutudy.domain.TemplateVariableType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
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

        // TODO QueryDsl 이랑 Stream 비교해보기
        Map<TemplateVariableType, List<TemplateVariable>> grouping = smsTemplate.getTmpltVarRelList().stream().
                collect(Collectors.groupingBy(
                        rel -> rel.getTemplateVariable().getVariableType(),
                        Collectors.mapping(rel -> rel.getTemplateVariable(), Collectors.toList())
                ));

        log.info("@@@@@ 템플릿 변수 유형별로 그룹핑 완료");

        for (Map.Entry<TemplateVariableType, List<TemplateVariable>> entry : grouping.entrySet()) {
            if (entry.getValue().size() > 0) {
                VariableBinder variableBinding = variableBinderMap.get(entry.getKey().getClassName());
                log.info("@@@@@ 바인드임플에서 값 가져오기 전");
                variableBinding.getValues(entry.getValue(), bindingDto).forEach((k, v) -> replacements.put(k, v));
                log.info("@@@@@ 바인드임플에서 값 가져온 후 ");
            }
        }

        String target = smsTemplate.getTemplateContent();
        for (Map.Entry<String, String> entry : replacements.entrySet()) {
            target = target.replaceAll("#\\{" + entry.getKey() + "\\}", entry.getValue());
        }

        return target;
    }


}
