package com.capgemini.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QClientEntity is a Querydsl query type for ClientEntity
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QClientEntity extends EntityPathBase<ClientEntity> {

    private static final long serialVersionUID = 71561476L;

    public static final QClientEntity clientEntity = new QClientEntity("clientEntity");

    public final QAbstractEntity _super = new QAbstractEntity(this);

    public final StringPath address = createString("address");

    public final ListPath<FlatEntity, QFlatEntity> coowned = this.<FlatEntity, QFlatEntity>createList("coowned", FlatEntity.class, QFlatEntity.class, PathInits.DIRECT2);

    //inherited
    public final DateTimePath<java.util.Date> created = _super.created;

    public final StringPath email = createString("email");

    public final StringPath firstName = createString("firstName");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath lastName = createString("lastName");

    public final ListPath<FlatEntity, QFlatEntity> owned = this.<FlatEntity, QFlatEntity>createList("owned", FlatEntity.class, QFlatEntity.class, PathInits.DIRECT2);

    public final StringPath phone = createString("phone");

    //inherited
    public final DateTimePath<java.util.Date> updated = _super.updated;

    public final NumberPath<Long> version = createNumber("version", Long.class);

    public QClientEntity(String variable) {
        super(ClientEntity.class, forVariable(variable));
    }

    public QClientEntity(Path<? extends ClientEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QClientEntity(PathMetadata metadata) {
        super(ClientEntity.class, metadata);
    }

}

