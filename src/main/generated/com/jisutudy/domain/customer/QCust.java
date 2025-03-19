package com.jisutudy.domain.customer;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCust is a Querydsl query type for Cust
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCust extends EntityPathBase<Cust> {

    private static final long serialVersionUID = 1001430659L;

    public static final QCust cust = new QCust("cust");

    public final com.jisutudy.domain.QBaseTimeEntity _super = new com.jisutudy.domain.QBaseTimeEntity(this);

    public final ListPath<com.jisutudy.domain.Booking, com.jisutudy.domain.QBooking> bookingList = this.<com.jisutudy.domain.Booking, com.jisutudy.domain.QBooking>createList("bookingList", com.jisutudy.domain.Booking.class, com.jisutudy.domain.QBooking.class, PathInits.DIRECT2);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedDate = _super.modifiedDate;

    public final StringPath name = createString("name");

    public final StringPath phoneNumber = createString("phoneNumber");

    public final EnumPath<CustSmsConsentType> smsConsentType = createEnum("smsConsentType", CustSmsConsentType.class);

    public final ListPath<com.jisutudy.domain.sms.Sms, com.jisutudy.domain.sms.QSms> smsList = this.<com.jisutudy.domain.sms.Sms, com.jisutudy.domain.sms.QSms>createList("smsList", com.jisutudy.domain.sms.Sms.class, com.jisutudy.domain.sms.QSms.class, PathInits.DIRECT2);

    public QCust(String variable) {
        super(Cust.class, forVariable(variable));
    }

    public QCust(Path<? extends Cust> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCust(PathMetadata metadata) {
        super(Cust.class, metadata);
    }

}

