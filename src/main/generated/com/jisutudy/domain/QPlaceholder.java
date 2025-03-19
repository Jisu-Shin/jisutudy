package com.jisutudy.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QPlaceholder is a Querydsl query type for Placeholder
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPlaceholder extends EntityPathBase<Placeholder> {

    private static final long serialVersionUID = -1574134445L;

    public static final QPlaceholder placeholder = new QPlaceholder("placeholder");

    public final StringPath enText = createString("enText");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath koText = createString("koText");

    public QPlaceholder(String variable) {
        super(Placeholder.class, forVariable(variable));
    }

    public QPlaceholder(Path<? extends Placeholder> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPlaceholder(PathMetadata metadata) {
        super(Placeholder.class, metadata);
    }

}

