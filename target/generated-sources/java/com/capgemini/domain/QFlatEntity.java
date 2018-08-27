package com.capgemini.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QFlatEntity is a Querydsl query type for FlatEntity
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QFlatEntity extends EntityPathBase<FlatEntity> {

    private static final long serialVersionUID = 1973610418L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QFlatEntity flatEntity = new QFlatEntity("flatEntity");

    public final QAbstractEntity _super = new QAbstractEntity(this);

    public final StringPath address = createString("address");

    public final NumberPath<Integer> balcoons = createNumber("balcoons", Integer.class);

    public final QBuildingEntity building;

    public final ListPath<ClientEntity, QClientEntity> coowner = this.<ClientEntity, QClientEntity>createList("coowner", ClientEntity.class, QClientEntity.class, PathInits.DIRECT2);

    //inherited
    public final DateTimePath<java.util.Date> created = _super.created;

    public final NumberPath<Integer> floor = createNumber("floor", Integer.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QClientEntity owner;

    public final NumberPath<Integer> price = createNumber("price", Integer.class);

    public final NumberPath<Integer> rooms = createNumber("rooms", Integer.class);

    public final NumberPath<Integer> size = createNumber("size", Integer.class);

    public final StringPath status = createString("status");

    //inherited
    public final DateTimePath<java.util.Date> updated = _super.updated;

    public final NumberPath<Long> version = createNumber("version", Long.class);

    public QFlatEntity(String variable) {
        this(FlatEntity.class, forVariable(variable), INITS);
    }

    public QFlatEntity(Path<? extends FlatEntity> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QFlatEntity(PathMetadata metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QFlatEntity(PathMetadata metadata, PathInits inits) {
        this(FlatEntity.class, metadata, inits);
    }

    public QFlatEntity(Class<? extends FlatEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.building = inits.isInitialized("building") ? new QBuildingEntity(forProperty("building")) : null;
        this.owner = inits.isInitialized("owner") ? new QClientEntity(forProperty("owner")) : null;
    }

}

