package com.jisutudy.service.smsTemplateVarBind;

import com.jisutudy.client.ItemApiService;
import com.jisutudy.domain.TemplateVariable;
import com.jisutudy.domain.TemplateVariableType;
import com.jisutudy.dto.ItemGetResponseDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ItemVariableBindImplTest {

    @Mock
    ItemApiService itemApiService;

    @InjectMocks
    ItemVariableBindImpl itemVariableBind;

    @Test
    public void 공연변수() throws Exception {
        //given
        String itemName = "2NE1 콘서트";
        int itemPrice = 200000;

        ItemGetResponseDto itemGetResponseDto = new ItemGetResponseDto();
        itemGetResponseDto.setName(itemName);
        itemGetResponseDto.setPrice(itemPrice);

        when(itemApiService.getItem(any())).thenReturn(itemGetResponseDto);

        List<TemplateVariable> templateVariableList = new ArrayList<>();
        templateVariableList.add(TemplateVariable.create("itemName","공연명", TemplateVariableType.ITEM));
        templateVariableList.add(TemplateVariable.create("itemPrice","공연가격", TemplateVariableType.ITEM));

        BindingDto bindingDto = new BindingDto();

        //when
        Map<String, String> resultMap = itemVariableBind.getValues(templateVariableList, bindingDto);

        //then
        assertEquals(itemName, resultMap.get("공연명"));
        assertEquals(String.valueOf(itemPrice), resultMap.get("공연가격"));
    }

    @Test
    public void 공연변수값이_없는경우() throws Exception {
        //given
        ItemGetResponseDto itemGetResponseDto = new ItemGetResponseDto();
        when(itemApiService.getItem(any())).thenReturn(itemGetResponseDto);

        List<TemplateVariable> templateVariableList = new ArrayList<>();
        templateVariableList.add(TemplateVariable.create("itemPlace","공연장소", TemplateVariableType.ITEM));

        BindingDto bindingDto = new BindingDto();

        //when
        assertThrows(IllegalStateException.class, ()->itemVariableBind.getValues(templateVariableList, bindingDto));

        //then
    }

}