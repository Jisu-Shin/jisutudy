package com.jisutudy.z_forStudy.dependencyInjection;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = DITestConfig.class)
@Slf4j
public class DIPerformanceTest {

    @Autowired
    private ConstructorInjectionService constructorService;

    @Autowired
    private SetterInjectionService setterService;

    @Autowired
    private FieldInjectionService fieldService;

    @Autowired
    private MethodInjectionService methodService;

    private static final int ITERATION_COUNT = 10000;
    
    @Test
    @DisplayName("DI 방식별 생명주기 로그 확인")
    public void testDILifeCycle() throws Exception {
        log.info("=== DI 생명주기 테스트 시작 ===");

        // 객체가 정상적으로 주입되었는지 검증
        assertThat(constructorService).isInstanceOf(ConstructorInjectionService.class);
        assertThat(setterService).isInstanceOf(SetterInjectionService.class);
        assertThat(fieldService).isInstanceOf(FieldInjectionService.class);
        assertThat(methodService).isInstanceOf(MethodInjectionService.class);

        log.info("=== DI 생명주기 테스트 완료 ===");

    }

    private void printPerformanceResults(Map<String, Long> results) {
        log.info("=== DI 성능 비교 결과 ({}회 실행) ===", ITERATION_COUNT);

        results.entrySet()
                .stream()
                .sorted(Map.Entry.<String, Long>comparingByValue())
                .forEach(entry -> {
                    log.info("{}: {}ms", entry.getKey(), entry.getValue());
                });

        // 최고 성능 vs 최저 성능 비교
        long fastest = Collections.min(results.values());
        long slowest = Collections.max(results.values());
        double performanceDiff = ((double) (slowest - fastest) / fastest) * 100;

        log.info("성능 차이: {}% (최고 vs 최저)", String.format("%.2f",performanceDiff));
    }

    private long measureExecutionTime(Runnable operation) {
        long startTime = System.nanoTime();
        for (int i = 0; i < ITERATION_COUNT; i++) {
            operation.run();
        }
        long endTime = System.nanoTime();
        return (endTime - startTime) / 1_000_000;
    }
}

/*
 Initialized JPA EntityManagerFactory for persistence unit 'default'
 [DI 실험] ========== 생성자 주입 ==========
 [DI 실험] Bean 생성 시작: ConstructorInjectionService
 [DI 실험] 의존성 주입 완료: ConstructorInjectionService
 [DI 실험] Bean 생성 종료: ConstructorInjectionService (소요 시간: 0.317791ms)
 [DI 실험] ========== 수정자 주입 ==========
 [DI 실험] Bean 생성 시작: SetterInjectionService
 [DI 실험] 의존성 주입 중: JpaSmsTemplateRepository
 [DI 실험] 의존성 주입 중: JpaTemplateVariableRepository
 [DI 실험] Bean 생성 종료: SetterInjectionService (소요 시간: 1.315583ms)
 [DI 실험] ========== 필드 주입 ==========
 [DI 실험] Bean 생성 시작: FieldInjectionService
 	  필드 주입 smsTmpltRepository 확인 - null
 	  필드 주입 tmpltVarRepository 확인 - null
 [DI 실험] 의존성 주입 완료: FieldInjectionService
 	  필드 주입 smsTmpltRepository 확인 - org.springframework.data.jpa.repository.support.SimpleJpaRepository@3299ee2c
 	  필드 주입 tmpltVarRepository 확인 - org.springframework.data.jpa.repository.support.SimpleJpaRepository@5817b0d1
 [DI 실험] Bean 생성 종료: FieldInjectionService (소요 시간: 3.336708ms)
 [DI 실험] ========== 일반메서드 주입 ==========
 [DI 실험] Bean 생성 시작: MethodInjectionService
 [DI 실험] 의존성 주입 완료: MethodInjectionService
 [DI 실험] Bean 생성 종료: MethodInjectionService (소요 시간: 0.462541ms)
*/