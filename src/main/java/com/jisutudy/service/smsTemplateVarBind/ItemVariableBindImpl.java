package com.jisutudy.service.smsTemplateVarBind;

import com.jisutudy.domain.TemplateVariable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class ItemVariableBindImpl implements VariableBinder{

    @Override
    public Map<String, String> getValues(List<TemplateVariable> tmpltVarList, BindingDto bindingDto) {
        Map<String, String> replacements = new HashMap<>();
//        Item item = getItem(bindingDto.getItemId());
//
//        for (TemplateVariable tmpltVar : tmpltVarList) {
//            if (tmpltVar.getKoText().equals("공연명")) {
//                replacements.put("공연명", item.getName());
//            }
//
//            if (tmpltVar.getKoText().equals("공연가격")) {
//                replacements.put("공연가격", String.valueOf(item.getPrice()));
//            }
//        }
//
        return replacements;
    }

}
