package com.jisutudy.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jisutudy.domain.CustSmsConsentType;
import com.jisutudy.dto.CustInfo;
import com.jisutudy.dto.SmsSendRequestDto;
import com.jisutudy.repository.JpaSmsRepository;
import com.jisutudy.repository.JpaSmsTemplateRepository;
import com.jisutudy.service.SmsFactory;
import com.jisutudy.service.SmsService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(SmsApiController.class)
class SmsApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SmsService smsService;

    @MockBean
    private JpaSmsTemplateRepository jpaSmsTemplateRepository;

    @MockBean
    private JpaSmsRepository jpaSmsRepository;

    @MockBean
    private SmsFactory smsFactory;

    @Test
    @DisplayName("sms 정상발송")
    public void sendSms() throws Exception {
        //given
        SmsSendRequestDto requestDto = SmsSendRequestDto.builder()
                .custIdList(List.of(new CustInfo(1L, "01012345678", CustSmsConsentType.ALL_DENY.toString())))
                .sendDt("202509060928")
                .templateId(1L)
                .build();
        when(smsService.send(any())).thenReturn(true);

        //when
        mockMvc.perform(post("/api/sms/send")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(requestDto)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("true"));

        //then
    }
}