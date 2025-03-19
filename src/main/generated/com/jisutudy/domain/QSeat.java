package com.jisutudy.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QSeat is a Querydsl query type for Seat
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSeat extends EntityPathBase<Seat> {

    private static final long serialVersionUID = 1022022181L;

    public static final QSeat seat = new QSeat("seat");

    public final EnumPath<SeatGrade> grade = createEnum("grade", SeatGrade.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ListPath<String, StringPath> seatNumber = this.<String, StringPath>createList("seatNumber", String.class, StringPath.class, PathInits.DIRECT2);

    public final NumberPath<Integer> seatPrice = createNumber("seatPrice", Integer.class);

    public QSeat(String variable) {
        super(Seat.class, forVariable(variable));
    }

    public QSeat(Path<? extends Seat> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSeat(PathMetadata metadata) {
        super(Seat.class, metadata);
    }

}

