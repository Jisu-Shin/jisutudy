package com.jisutudy.service.smsTemplateVarBind;

import com.jisutudy.domain.TemplateVariable;
import com.jisutudy.domain.customer.Cust;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class ItemVariableBindImpl implements VariableBinder{

    @Override
    public Map<String, String> getValues(List<TemplateVariable> tmpltVarList, Cust cust) {
        return Map.of();
    }
}
