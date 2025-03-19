package com.jisutudy.domain.performance;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QMusical is a Querydsl query type for Musical
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMusical extends EntityPathBase<Musical> {

    private static final long serialVersionUID = -65733326L;

    public static final QMusical musical = new QMusical("musical");

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

    public QMusical(String variable) {
        super(Musical.class, forVariable(variable));
    }

    public QMusical(Path<? extends Musical> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMusical(PathMetadata metadata) {
        super(Musical.class, metadata);
    }

}

