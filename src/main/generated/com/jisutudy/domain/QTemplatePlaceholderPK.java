package com.jisutudy.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QTemplatePlaceholderPK is a Querydsl query type for TemplatePlaceholderPK
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QTemplatePlaceholderPK extends BeanPath<TemplatePlaceholderPK> {

    private static final long serialVersionUID = 105822260L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QTemplatePlaceholderPK templatePlaceholderPK = new QTemplatePlaceholderPK("templatePlaceholderPK");

    public final QPlaceholder placeholder;

    public final QSmsTemplate smsTemplate;

    public QTemplatePlaceholderPK(String variable) {
        this(TemplatePlaceholderPK.class, forVariable(variable), INITS);
    }

    public QTemplatePlaceholderPK(Path<? extends TemplatePlaceholderPK> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QTemplatePlaceholderPK(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QTemplatePlaceholderPK(PathMetadata metadata, PathInits inits) {
        this(TemplatePlaceholderPK.class, metadata, inits);
    }

    public QTemplatePlaceholderPK(Class<? extends TemplatePlaceholderPK> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.placeholder = inits.isInitialized("placeholder") ? new QPlaceholder(forProperty("placeholder")) : null;
        this.smsTemplate = inits.isInitialized("smsTemplate") ? new QSmsTemplate(forProperty("smsTemplate")) : null;
    }

}

