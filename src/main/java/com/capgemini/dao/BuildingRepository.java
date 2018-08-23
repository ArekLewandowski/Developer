package com.capgemini.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capgemini.domain.BuildingEntity;
import com.capgemini.domain.FlatEntity;

@Repository
public interface BuildingRepository extends JpaRepository<BuildingEntity, Long> {
	
	BuildingEntity save(BuildingEntity buildingEntity);
	
	void delete(Long id);
	
	void delete(BuildingEntity bEntity);
	
	BuildingEntity findOne(Long id);

	List<BuildingEntity> findByLocalization(String localization);
	
	List<BuildingEntity> findByFloors(int floors);

	List<BuildingEntity> findAll();

}
