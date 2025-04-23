package com.jisutudy.client;

import com.jisutudy.dto.CustListResponseDto;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@RequiredArgsConstructor
public class CustApiService {
    private final RestTemplate restTemplate;

    @Value("${service.cust}")
    private String custUrl;

    private String baseUrl;
    private UriComponentsBuilder builder;

    @PostConstruct
    private void init() {
        baseUrl = "http://" + custUrl + "/api/custs";
        builder = UriComponentsBuilder.fromUriString(baseUrl);
    }

    public CustListResponseDto getCust(Long custId) {
        builder.pathSegment(String.valueOf(custId));

        ResponseEntity<CustListResponseDto> response = restTemplate.getForEntity(
                builder.toUriString(),
                CustListResponseDto.class
        );

        return response.getBody();
    }
}
