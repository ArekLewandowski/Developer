package com.capgemini.repository;

import com.capgemini.types.FlatSearchCriteriaTO;

import java.util.List;

import com.capgemini.domain.FlatEntity;

public interface CustomizedFlatRepository {

	 List<FlatEntity> findFlatByVariousCriteria(FlatSearchCriteriaTO flatSearchCriteriaTO);
}
