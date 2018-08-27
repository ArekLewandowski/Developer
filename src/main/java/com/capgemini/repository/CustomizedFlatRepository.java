package com.capgemini.repository;

import java.util.List;

import com.capgemini.domain.FlatEntity;
import com.capgemini.types.FlatSearchCriteriaTO;

public interface CustomizedFlatRepository {

	List<FlatEntity> findFlatByVariousCriteria(FlatSearchCriteriaTO flatSearchCriteriaTO);

}
