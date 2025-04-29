package com.jisutudy.client;

import com.jisutudy.dto.CustListResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class CustApiService {
    private final WebClient webClient;

    @Autowired
    public CustApiService(@Qualifier("custWebClient") WebClient webClient) {
        this.webClient = webClient;
    }

    public Mono<CustListResponseDto> getCust(Long custId) {
        return webClient.get()
                .uri("/api/custs/{id}", custId)
                .retrieve()
                .bodyToMono(CustListResponseDto.class);
    }
}
