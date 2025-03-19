package com.jisutudy.domain.sms;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QSms is a Querydsl query type for Sms
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSms extends EntityPathBase<Sms> {

    private static final long serialVersionUID = -1381365980L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QSms sms = new QSms("sms");

    public final com.jisutudy.domain.QBaseTimeEntity _super = new com.jisutudy.domain.QBaseTimeEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final com.jisutudy.domain.customer.QCust cust;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedDate = _super.modifiedDate;

    public final DateTimePath<java.time.LocalDateTime> sendDt = createDateTime("sendDt", java.time.LocalDateTime.class);

    public final StringPath sendPhoneNumber = createString("sendPhoneNumber");

    public final StringPath smsContent = createString("smsContent");

    public final NumberPath<Long> smsId = createNumber("smsId", Long.class);

    public final EnumPath<SmsResult> smsResult = createEnum("smsResult", SmsResult.class);

    public final com.jisutudy.domain.QSmsTemplate smsTemplate;

    public QSms(String variable) {
        this(Sms.class, forVariable(variable), INITS);
    }

    public QSms(Path<? extends Sms> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QSms(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QSms(PathMetadata metadata, PathInits inits) {
        this(Sms.class, metadata, inits);
    }

    public QSms(Class<? extends Sms> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.cust = inits.isInitialized("cust") ? new com.jisutudy.domain.customer.QCust(forProperty("cust")) : null;
        this.smsTemplate = inits.isInitialized("smsTemplate") ? new com.jisutudy.domain.QSmsTemplate(forProperty("smsTemplate")) : null;
    }

}

