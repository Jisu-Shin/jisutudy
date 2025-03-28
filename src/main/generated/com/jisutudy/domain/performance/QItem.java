package com.jisutudy.domain.performance;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QItem is a Querydsl query type for Item
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QItem extends EntityPathBase<Item> {

    private static final long serialVersionUID = 1076250161L;

    public static final QItem item = new QItem("item");

    public final ListPath<com.jisutudy.domain.Booking, com.jisutudy.domain.QBooking> bookingList = this.<com.jisutudy.domain.Booking, com.jisutudy.domain.QBooking>createList("bookingList", com.jisutudy.domain.Booking.class, com.jisutudy.domain.QBooking.class, PathInits.DIRECT2);

    public final ListPath<com.jisutudy.domain.Category, com.jisutudy.domain.QCategory> categories = this.<com.jisutudy.domain.Category, com.jisutudy.domain.QCategory>createList("categories", com.jisutudy.domain.Category.class, com.jisutudy.domain.QCategory.class, PathInits.DIRECT2);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public final NumberPath<Integer> price = createNumber("price", Integer.class);

    public final NumberPath<Integer> stockQuantity = createNumber("stockQuantity", Integer.class);

    public QItem(String variable) {
        super(Item.class, forVariable(variable));
    }

    public QItem(Path<? extends Item> path) {
        super(path.getType(), path.getMetadata());
    }

    public QItem(PathMetadata metadata) {
        super(Item.class, metadata);
    }

}

