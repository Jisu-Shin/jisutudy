package com.jisutudy.z_forStudy.dependencyInjection;

import com.jisutudy.repository.JpaSmsTemplateRepository;
import com.jisutudy.repository.JpaTemplateVariableRepository;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class ConstructorInjectionService {
    private final JpaSmsTemplateRepository smsTmpltRepository;
    private final JpaTemplateVariableRepository tmpltVarRepository;
    private long startTime;

    @Autowired
    public ConstructorInjectionService(JpaSmsTemplateRepository smsTmpltRepository, JpaTemplateVariableRepository tmpltVarRepository) {
        this.smsTmpltRepository = smsTmpltRepository;
        this.tmpltVarRepository = tmpltVarRepository;
        log.info("[DI 실험] ========== 생성자 주입 ==========");
        log.info("[DI 실험] Bean 생성 시작: ConstructorInjectionService");
        log.info("[DI 실험] 의존성 주입 완료: ConstructorInjectionService");
        startTime = System.nanoTime();
    }

    @PostConstruct
    public void init() {
        log.info("[DI 실험] @PostConstruct 실행 : ConstructorInjectionService (소요 시간: {}ms)", (System.nanoTime()-startTime)/1_000_000.0);
    }

    public void create() {
        log.info("ConstructorInjectionService.create");
    }
}
