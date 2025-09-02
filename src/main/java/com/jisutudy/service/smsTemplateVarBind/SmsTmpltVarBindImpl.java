package com.jisutudy.service.smsTemplateVarBind;

import com.jisutudy.domain.SmsTemplate;
import com.jisutudy.domain.SmsTmpltVarRel;
import com.jisutudy.domain.TemplateVariable;
import com.jisutudy.domain.TemplateVariableType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Component
@RequiredArgsConstructor
public class SmsTmpltVarBindImpl implements SmsTmpltVarBinder {

    private final Map<String, VariableBinder> variableBinderMap;

    @Override
    public String bind(SmsTemplate smsTemplate, BindingDto bindingDto) {
        List<SmsTmpltVarRel> tmpltVarRelList = smsTemplate.getTmpltVarRelList();
        if (tmpltVarRelList.size() == 0) {
            return smsTemplate.getTemplateContent();
        }

        // TODO QueryDsl 이랑 Stream 비교해보기
        // 변수 유형별로 그룹핑
        Map<TemplateVariableType, List<TemplateVariable>> grouping = tmpltVarRelList.stream()
                .collect(Collectors.groupingBy(
                        rel -> rel.getTemplateVariable().getVariableType(),
                        Collectors.mapping(SmsTmpltVarRel::getTemplateVariable, Collectors.toList())
                ));
        log.info("@@@@@ 템플릿 변수 유형별로 그룹핑 완료");

        // 치환 맵 생성
        Map<String, String> replacements = grouping.entrySet().stream()
                .flatMap(entry -> {
                    VariableBinder binder = variableBinderMap.get(entry.getKey().getClassName());
                    if (binder == null) {
                        // todo Exception 필요
                        log.warn("VariableBinder not found for type: {}", entry.getKey());
                        return Stream.empty();
                    }
                    return binder.getValues(entry.getValue(), bindingDto).entrySet().stream();
                })
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        for (Map.Entry<TemplateVariableType, List<TemplateVariable>> entry : grouping.entrySet()) {
            if (entry.getValue().size() > 0) {
                VariableBinder variableBinding = variableBinderMap.get(entry.getKey().getClassName());
                log.info("@@@@@ 바인드임플에서 값 가져오기 전");
                variableBinding.getValues(entry.getValue(), bindingDto).forEach((k, v) -> replacements.put(k, v));
                log.info("@@@@@ 바인드임플에서 값 가져온 후 ");
            }
        }

        String messageContent = TemplateVariableUtils.replaceVariables(smsTemplate.getTemplateContent(), replacements);
        return messageContent;
    }
}
