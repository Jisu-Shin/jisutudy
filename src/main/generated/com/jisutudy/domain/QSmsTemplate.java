package com.jisutudy.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QSmsTemplate is a Querydsl query type for SmsTemplate
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSmsTemplate extends EntityPathBase<SmsTemplate> {

    private static final long serialVersionUID = -1269261101L;

    public static final QSmsTemplate smsTemplate = new QSmsTemplate("smsTemplate");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ListPath<com.jisutudy.domain.sms.Sms, com.jisutudy.domain.sms.QSms> smsList = this.<com.jisutudy.domain.sms.Sms, com.jisutudy.domain.sms.QSms>createList("smsList", com.jisutudy.domain.sms.Sms.class, com.jisutudy.domain.sms.QSms.class, PathInits.DIRECT2);

    public final EnumPath<com.jisutudy.domain.sms.SmsType> smsType = createEnum("smsType", com.jisutudy.domain.sms.SmsType.class);

    public final StringPath templateContent = createString("templateContent");

    public final ListPath<SmsTmpltVarRel, QSmsTmpltVarRel> tmpltVarRelList = this.<SmsTmpltVarRel, QSmsTmpltVarRel>createList("tmpltVarRelList", SmsTmpltVarRel.class, QSmsTmpltVarRel.class, PathInits.DIRECT2);

    public QSmsTemplate(String variable) {
        super(SmsTemplate.class, forVariable(variable));
    }

    public QSmsTemplate(Path<? extends SmsTemplate> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSmsTemplate(PathMetadata metadata) {
        super(SmsTemplate.class, metadata);
    }

}

