package com.jisutudy.service.smsTemplateVarBind;

import com.jisutudy.domain.TemplateVariable;
import com.jisutudy.domain.customer.Cust;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class CustVariableBindImpl implements VariableBinder {
    @Override
    public Map<String, String> getValues(List<TemplateVariable> tmpltVarList, Cust cust) {
        Map<String, String> replacements = new HashMap<>();

        for (TemplateVariable tmpltVar : tmpltVarList) {
            if (tmpltVar.getKoText().equals("고객명")) {
                replacements.put("고객명", cust.getName());
            }
        }

        return replacements;
    }
}
