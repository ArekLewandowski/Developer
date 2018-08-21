package com.capgemini.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.capgemini.domain.FlatEntity;

@Repository
public interface FlatDAO extends CrudRepository<FlatEntity, Long> {

//	@SuppressWarnings("unchecked")
//	FlatEntity save(FlatEntity flatEntity);
//	
//	FlatEntity findOne(Long id);
//	
//	FlatEntity getOne(Long id);
//	
//	List<FlatEntity> findAll();
//	
//	List<FlatEntity> findByBuildingEntity();
}
