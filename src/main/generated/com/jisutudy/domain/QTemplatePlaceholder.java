package com.jisutudy.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QTemplatePlaceholder is a Querydsl query type for TemplatePlaceholder
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTemplatePlaceholder extends EntityPathBase<TemplatePlaceholder> {

    private static final long serialVersionUID = -2073630599L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QTemplatePlaceholder templatePlaceholder = new QTemplatePlaceholder("templatePlaceholder");

    public final QTemplatePlaceholderPK templatePlaceholderPK;

    public QTemplatePlaceholder(String variable) {
        this(TemplatePlaceholder.class, forVariable(variable), INITS);
    }

    public QTemplatePlaceholder(Path<? extends TemplatePlaceholder> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QTemplatePlaceholder(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QTemplatePlaceholder(PathMetadata metadata, PathInits inits) {
        this(TemplatePlaceholder.class, metadata, inits);
    }

    public QTemplatePlaceholder(Class<? extends TemplatePlaceholder> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.templatePlaceholderPK = inits.isInitialized("templatePlaceholderPK") ? new QTemplatePlaceholderPK(forProperty("templatePlaceholderPK"), inits.get("templatePlaceholderPK")) : null;
    }

}

