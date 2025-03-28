package com.jisutudy.service.smsTemplateVarBind;

import com.jisutudy.domain.customer.Cust;
import com.jisutudy.web.dto.SmsSendRequestDto;
import lombok.*;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BindingDto {
    private Cust cust;
    private Long itemId;

    public static BindingDto create(Cust cust, SmsSendRequestDto requestDto) {
        BindingDto bindingDto = new BindingDto(cust, requestDto.getItemId());
        return bindingDto;
    }
}
