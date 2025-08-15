package com.jisutudy.forStudy.dependencyInjection;

import com.jisutudy.domain.SmsTemplate;
import com.jisutudy.domain.SmsType;
import com.jisutudy.repository.JpaSmsTemplateRepository;
import com.jisutudy.repository.JpaTemplateVariableRepository;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class SetterInjectionService {
    private JpaSmsTemplateRepository smsTmpltRepository;
    private JpaTemplateVariableRepository tmpltVarRepository;
    private long startTime;

    public SetterInjectionService() {
        log.info("[DI 실험] ========== 수정자 주입 ==========");
        log.info("[DI 실험] Bean 생성 시작: SetterInjectionService");
        startTime = System.nanoTime();
    }

    @Autowired
    public void setSmsTmpltRepository(JpaSmsTemplateRepository smsTmpltRepository) {
        this.smsTmpltRepository = smsTmpltRepository;
        log.info("[DI 실험] 의존성 주입 중: JpaSmsTemplateRepository");
    }

    @Autowired
    public void setTmpltVarRepository(JpaTemplateVariableRepository tmpltVarRepository) {
        this.tmpltVarRepository = tmpltVarRepository;

        log.info("[DI 실험] 의존성 주입 중: JpaTemplateVariableRepository");
    }

    @PostConstruct
    public void init() {
        log.info("[DI 실험] @PostConstruct 실행 : SetterInjectionService (소요 시간: {}ms)", (System.nanoTime()-startTime)/1_000_000.0);
    }

    public void create() {
        log.info("SetterInjectionService.create");
    }
}
