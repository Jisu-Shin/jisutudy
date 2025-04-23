package com.jisutudy.service.smsTemplateVarBind;

import com.jisutudy.client.CustApiService;
import com.jisutudy.domain.TemplateVariable;
import com.jisutudy.dto.CustListResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class CustVariableBindImpl implements VariableBinder {

    private final CustApiService custApiService;

    @Override
    public Map<String, String> getValues(List<TemplateVariable> tmpltVarList, BindingDto bindingDto) {
        Map<String, String> replacements = new HashMap<>();
        CustListResponseDto cust = custApiService.getCust(bindingDto.getCustId());

        for (TemplateVariable tmpltVar : tmpltVarList) {
            if (tmpltVar.getKoText().equals("고객명")) {
                replacements.put("고객명", cust.getName());
            }
        }

        return replacements;
    }
}
