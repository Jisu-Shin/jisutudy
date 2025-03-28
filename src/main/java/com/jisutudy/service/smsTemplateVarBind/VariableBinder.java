package com.jisutudy.service.smsTemplateVarBind;

import com.jisutudy.domain.TemplateVariable;
import com.jisutudy.domain.customer.Cust;

import java.util.List;
import java.util.Map;

public interface VariableBinder {

    public Map<String, String> getValues(List<TemplateVariable> tmpltVarList, BindingDto bindingDto);
}
