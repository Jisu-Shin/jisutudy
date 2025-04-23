package com.jisutudy.service.smsTemplateVarBind;

import com.jisutudy.domain.TemplateVariable;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class CustVariableBindImpl implements VariableBinder {
    @Override
    public Map<String, String> getValues(List<TemplateVariable> tmpltVarList, BindingDto bindingDto) {
        Map<String, String> replacements = new HashMap<>();
//        Cust cust = bindingDto.getCust();
//
//        for (TemplateVariable tmpltVar : tmpltVarList) {
//            if (tmpltVar.getKoText().equals("고객명")) {
//                replacements.put("고객명", cust.getName());
//            }
//        }

        return replacements;
    }
}
