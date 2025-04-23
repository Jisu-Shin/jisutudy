package com.jisutudy.service.smsTemplateVarBind;

import com.jisutudy.dto.SmsSendRequestDto;
import lombok.*;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BindingDto {
    private Long custId;
    private Long itemId;

    public static BindingDto create(Long custId, SmsSendRequestDto requestDto) {
        BindingDto bindingDto = new BindingDto(custId, requestDto.getItemId());
        return bindingDto;
    }
}
