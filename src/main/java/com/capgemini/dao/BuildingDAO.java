package com.capgemini.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.capgemini.domain.BuildingEntity;

@Repository
public interface BuildingDAO extends CrudRepository<BuildingEntity, Long> {
	
	BuildingEntity save(BuildingEntity buildingEntity);
	
	BuildingEntity findOne(Long id);
	
	BuildingEntity getOne(Long id);

	List<BuildingEntity> findByLocalization(String localization);

	List<BuildingEntity> findAll();
}
