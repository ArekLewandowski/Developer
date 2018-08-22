package com.capgemini.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capgemini.domain.BuildingEntity;

@Repository
public interface BuildingRepository extends JpaRepository<BuildingEntity, Long> {
	
	BuildingEntity save(BuildingEntity buildingEntity);
	
	void delete(Long id);
	
	void delete(BuildingEntity bEntity);
	
	BuildingEntity findOne(Long id);
	
	BuildingEntity getOne(Long id);

	BuildingEntity findByLocalization(String localization);

	List<BuildingEntity> findAll();
}
