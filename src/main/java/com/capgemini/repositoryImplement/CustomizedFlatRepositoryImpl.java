package com.capgemini.repositoryImplement;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.capgemini.domain.FlatEntity;
import com.capgemini.enums.FlatStatus;
import com.capgemini.repository.CustomizedFlatRepository;
import com.capgemini.types.FlatSearchCriteriaTO;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class CustomizedFlatRepositoryImpl implements CustomizedFlatRepository {

	@PersistenceContext
	protected EntityManager entityManager;

	@Override
	public List<FlatEntity> findFlatByVariousCriteria(FlatSearchCriteriaTO flatSearchCriteriaTO) {

		QFlatEntity flat = QFlatEntity.flatEntity;

		JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);

		BooleanBuilder builder = new BooleanBuilder();
		builder.and(flat.status.eq(FlatStatus.FREE));

		if (flatSearchCriteriaTO.getMinSize() != null) {
			builder.and(flat.size.goe(flatSearchCriteriaTO.getMinSize()));
		}
		if (flatSearchCriteriaTO.getMaxSize() != null) {
			builder.and(flat.area.loe(flatSearchCriteriaTO.getMaxSize()));
		}
		if (flatSearchCriteriaTO.getMinRooms() != null) {
			builder.and(flat.roomsCount.goe(flatSearchCriteriaTO.getMinRooms()));
		}
		if (flatSearchCriteriaTO.getMaxRooms() != null) {
			builder.and(flat.roomsCount.loe(flatSearchCriteriaTO.getMaxRooms()));
		}
		if (flatSearchCriteriaTO.getMinBalcons() != null) {
			builder.and(flat.balconyCount.goe(flatSearchCriteriaTO.getMinBalcons()));
		}
		if (flatSearchCriteriaTO.getMaxBalcons() != null) {
			builder.and(flat.balconyCount.loe(flatSearchCriteriaTO.getMaxBalcons()));
		}

		List<FlatEntity> result = queryFactory.selectFrom(flat).where(builder).fetch();

		return result;

	}

}
