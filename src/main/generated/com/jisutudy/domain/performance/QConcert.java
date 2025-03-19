package com.jisutudy.domain.performance;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QConcert is a Querydsl query type for Concert
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QConcert extends EntityPathBase<Concert> {

    private static final long serialVersionUID = -527404344L;

    public static final QConcert concert = new QConcert("concert");

    public final QItem _super = new QItem(this);

    //inherited
    public final ListPath<com.jisutudy.domain.Booking, com.jisutudy.domain.QBooking> bookingList = _super.bookingList;

    //inherited
    public final ListPath<com.jisutudy.domain.Category, com.jisutudy.domain.QCategory> categories = _super.categories;

    //inherited
    public final NumberPath<Long> id = _super.id;

    //inherited
    public final StringPath name = _super.name;

    //inherited
    public final NumberPath<Integer> price = _super.price;

    //inherited
    public final NumberPath<Integer> stockQuantity = _super.stockQuantity;

    public QConcert(String variable) {
        super(Concert.class, forVariable(variable));
    }

    public QConcert(Path<? extends Concert> path) {
        super(path.getType(), path.getMetadata());
    }

    public QConcert(PathMetadata metadata) {
        super(Concert.class, metadata);
    }

}

