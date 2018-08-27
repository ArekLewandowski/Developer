package com.capgemini.repositoryImplement;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.capgemini.domain.BuildingEntity;
import com.capgemini.domain.FlatEntity;
import com.capgemini.domain.QBuildingEntity;
import com.capgemini.domain.QFlatEntity;
import com.capgemini.repository.CustomizedFlatRepository;
import com.capgemini.types.FlatSearchCriteriaTO;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryFactory;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class CustomizedFlatRepositoryImpl implements CustomizedFlatRepository {

	@PersistenceContext
	protected EntityManager entityManager;

	@Override
	public List<FlatEntity> findFlatByVariousCriteria(FlatSearchCriteriaTO flatSearchCriteriaTO) {

		QFlatEntity flatEntity = QFlatEntity.flatEntity;

		JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);

		BooleanBuilder builder = new BooleanBuilder();
		builder.and(flatEntity.status.eq("FREE"));

		if (flatSearchCriteriaTO.getMinSize() != null) {
			builder.and(flatEntity.size.goe(flatSearchCriteriaTO.getMinSize()));
		}
		if (flatSearchCriteriaTO.getMaxSize() != null) {
			builder.and(flatEntity.size.loe(flatSearchCriteriaTO.getMaxSize()));
		}
		if (flatSearchCriteriaTO.getMinRooms() != null) {
			builder.and(flatEntity.rooms.goe(flatSearchCriteriaTO.getMinRooms()));
		}
		if (flatSearchCriteriaTO.getMaxRooms() != null) {
			builder.and(flatEntity.rooms.loe(flatSearchCriteriaTO.getMaxRooms()));
		}
		if (flatSearchCriteriaTO.getMinBalcons() != null) {
			builder.and(flatEntity.balcoons.goe(flatSearchCriteriaTO.getMinBalcons()));
		}
		if (flatSearchCriteriaTO.getMaxBalcons() != null) {
			builder.and(flatEntity.balcoons.loe(flatSearchCriteriaTO.getMaxBalcons()));
		}

		List<FlatEntity> result = queryFactory.selectFrom(flatEntity).where(builder).fetch();

		return result;
	}
}
