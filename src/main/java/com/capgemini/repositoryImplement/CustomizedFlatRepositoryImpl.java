//package com.capgemini.repositoryImplement;
//
//import java.util.List;
//
//import javax.persistence.TypedQuery;
//
//import com.capgemini.domain.FlatEntity;
//import com.capgemini.repository.CustomizedFlatRepository;
//import com.capgemini.types.FlatSearchCriteriaTO;
//
//public class CustomizedFlatRepositoryImpl implements CustomizedFlatRepository {
//
//	@Override
//	public List<FlatEntity> findFlatByVariousCriteria(FlatSearchCriteriaTO flatSearchCriteriaTO) {
//		StringBuilder queryBuilder = new StringBuilder();
//        queryBuilder.append("select a from ApartmentEntity a where ");
//        boolean canAppendQueryByAnd = false;
//
//        if (flatSearchCriteriaTO.getMinSize() != null) {
//            queryBuilder.append("a.apartmentSize >= :minApartmentSize");
//            canAppendQueryByAnd = true;
//        }
//        if (flatSearchCriteriaTO.getMaxSize() != null) {
//            if (canAppendQueryByAnd) {
//                queryBuilder.append(" and ");
//            }
//            queryBuilder.append("a.apartmentSize <= :maxApartmentSize");
//            canAppendQueryByAnd = true;
//        }
//        if (flatSearchCriteriaTO.getMinRooms() != null) {
//            if (canAppendQueryByAnd) {
//                queryBuilder.append(" and ");
//            }
//            queryBuilder.append("a.roomNo >= :minRoomNo");
//            canAppendQueryByAnd = true;
//        }
//        if (flatSearchCriteriaTO.getMaxRooms() != null) {
//            if (canAppendQueryByAnd) {
//                queryBuilder.append(" and ");
//            }
//            queryBuilder.append("a.roomNo <= :maxRoomNo");
//            canAppendQueryByAnd = true;
//        }
//        if (flatSearchCriteriaTO.getMinBalcons() != null) {
//            if (canAppendQueryByAnd) {
//                queryBuilder.append(" and ");
//            }
//            queryBuilder.append("a.balconyNo >= :minBalconyNo");
//            canAppendQueryByAnd = true;
//        }
//        if (flatSearchCriteriaTO.getMaxBalcons() != null) {
//            if (canAppendQueryByAnd) {
//                queryBuilder.append(" and ");
//            }
//            queryBuilder.append("a.balconyNo <= :maxBalconyNo");
//        }
//
//        TypedQuery<FlatEntity> query = entityManager.createQuery(queryBuilder.toString(), FlatEntity.class);
//
//        if (flatSearchCriteriaTO.getMinSize() != null) {
//            query.setParameter("minApartmentSize", flatSearchCriteriaTO.getMinSize());
//        }
//        if (flatSearchCriteriaTO.getMaxSize() != null) {
//            query.setParameter("maxApartmentSize", flatSearchCriteriaTO.getMaxSize());
//        }
//        if (flatSearchCriteriaTO.getMinRooms() != null) {
//            query.setParameter("minRoomNo", flatSearchCriteriaTO.getMinRooms());
//        }
//        if (flatSearchCriteriaTO.getMaxRooms() != null) {
//            query.setParameter("maxRoomNo", flatSearchCriteriaTO.getMaxRooms());
//        }
//        if (flatSearchCriteriaTO.getMinBalcons() != null) {
//            query.setParameter("minBalconyNo", flatSearchCriteriaTO.getMinBalcons());
//        }
//        if (flatSearchCriteriaTO.getMaxBalcons() != null) {
//            query.setParameter("maxBalconyNo", flatSearchCriteriaTO.getMaxBalcons());
//        }
//
//        return query.getResultList();
//    }
//
//}
