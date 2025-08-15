package com.jisutudy.forStudy.dependencyInjection;

import com.jisutudy.domain.SmsTemplate;
import com.jisutudy.domain.SmsType;
import com.jisutudy.repository.JpaSmsTemplateRepository;
import com.jisutudy.repository.JpaTemplateVariableRepository;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class MethodInjectionService {
    private JpaSmsTemplateRepository smsTmpltRepository;
    private JpaTemplateVariableRepository tmpltVarRepository;

    private long startTime;

    public MethodInjectionService() {
        log.info("[DI 실험] ========== 일반메서드 주입 ==========");
        log.info("[DI 실험] Bean 생성 시작: MethodInjectionService");
        startTime = System.nanoTime();
    }

    @PostConstruct
    public void init() {
        log.info("[DI 실험] @PostConstruct 실행 : MethodInjectionService (소요 시간: {}ms)", (System.nanoTime()-startTime)/1_000_000.0);
    }

    @Autowired
    public void injectDenpendencies(JpaSmsTemplateRepository smsTmpltRepository, JpaTemplateVariableRepository tmpltVarRepository) {
        this.smsTmpltRepository = smsTmpltRepository;
        this.tmpltVarRepository = tmpltVarRepository;
        log.info("[DI 실험] 의존성 주입 완료: MethodInjectionService");
    }

    public void create() {
        log.info("MethodInjectionService.create");
    }
}
