package com.jisutudy.z_forStudy.dependencyInjection;


import com.jisutudy.repository.JpaSmsTemplateRepository;
import com.jisutudy.repository.JpaTemplateVariableRepository;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;


@Slf4j
public class FieldInjectionService {
    @Autowired private JpaSmsTemplateRepository smsTmpltRepository;
    @Autowired private JpaTemplateVariableRepository tmpltVarRepository;
    private long startTime;

    public FieldInjectionService() {
        log.info("[DI 실험] ========== 필드 주입 ==========");
        log.info("[DI 실험] Bean 생성 시작: FieldInjectionService");

        log.info("\t  필드 주입 smsTmpltRepository 확인 - {}", smsTmpltRepository);
        log.info("\t  필드 주입 tmpltVarRepository 확인 - {}", tmpltVarRepository);

        startTime = System.nanoTime();

    }

    @PostConstruct
    public void init() {
        log.info("[DI 실험] 의존성 주입 완료: FieldInjectionService");
        log.info("\t  필드 주입 smsTmpltRepository 확인 - {}", smsTmpltRepository);
        log.info("\t  필드 주입 tmpltVarRepository 확인 - {}", tmpltVarRepository);

        log.info("[DI 실험] @PostConstruct 실행 : FieldInjectionService (소요 시간: {}ms)", (System.nanoTime()-startTime)/1_000_000.0);
    }

    public void create() {
        log.info("FieldInjectionService.create");
    }
}
