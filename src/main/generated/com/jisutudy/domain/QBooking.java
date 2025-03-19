package com.jisutudy.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBooking is a Querydsl query type for Booking
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBooking extends EntityPathBase<Booking> {

    private static final long serialVersionUID = -1863970759L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBooking booking = new QBooking("booking");

    public final DateTimePath<java.time.LocalDateTime> bookDt = createDateTime("bookDt", java.time.LocalDateTime.class);

    public final NumberPath<Integer> count = createNumber("count", Integer.class);

    public final com.jisutudy.domain.customer.QCust cust;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.jisutudy.domain.performance.QItem item;

    public final EnumPath<BookingStatus> status = createEnum("status", BookingStatus.class);

    public QBooking(String variable) {
        this(Booking.class, forVariable(variable), INITS);
    }

    public QBooking(Path<? extends Booking> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBooking(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBooking(PathMetadata metadata, PathInits inits) {
        this(Booking.class, metadata, inits);
    }

    public QBooking(Class<? extends Booking> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.cust = inits.isInitialized("cust") ? new com.jisutudy.domain.customer.QCust(forProperty("cust")) : null;
        this.item = inits.isInitialized("item") ? new com.jisutudy.domain.performance.QItem(forProperty("item")) : null;
    }

}

