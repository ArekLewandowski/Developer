package com.capgemini.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBuildingEntity is a Querydsl query type for BuildingEntity
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QBuildingEntity extends EntityPathBase<BuildingEntity> {

    private static final long serialVersionUID = -1928202323L;

    public static final QBuildingEntity buildingEntity = new QBuildingEntity("buildingEntity");

    public final QAbstractEntity _super = new QAbstractEntity(this);

    //inherited
    public final DateTimePath<java.util.Date> created = _super.created;

    public final StringPath description = createString("description");

    public final BooleanPath elevator = createBoolean("elevator");

    public final ListPath<FlatEntity, QFlatEntity> flats = this.<FlatEntity, QFlatEntity>createList("flats", FlatEntity.class, QFlatEntity.class, PathInits.DIRECT2);

    public final NumberPath<Integer> flatsSum = createNumber("flatsSum", Integer.class);

    public final NumberPath<Integer> floors = createNumber("floors", Integer.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath localization = createString("localization");

    //inherited
    public final DateTimePath<java.util.Date> updated = _super.updated;

    public final NumberPath<Long> version = createNumber("version", Long.class);

    public QBuildingEntity(String variable) {
        super(BuildingEntity.class, forVariable(variable));
    }

    public QBuildingEntity(Path<? extends BuildingEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBuildingEntity(PathMetadata metadata) {
        super(BuildingEntity.class, metadata);
    }

}

