package com.jisutudy.domain.performance;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QSport is a Querydsl query type for Sport
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSport extends EntityPathBase<Sport> {

    private static final long serialVersionUID = -986857450L;

    public static final QSport sport = new QSport("sport");

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

    public QSport(String variable) {
        super(Sport.class, forVariable(variable));
    }

    public QSport(Path<? extends Sport> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSport(PathMetadata metadata) {
        super(Sport.class, metadata);
    }

}

