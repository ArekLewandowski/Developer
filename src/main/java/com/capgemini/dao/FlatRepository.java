package com.capgemini.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capgemini.domain.BuildingEntity;
import com.capgemini.domain.FlatEntity;
import com.capgemini.enums.FLAT_STATUS;

@Repository
public interface FlatRepository extends JpaRepository<FlatEntity, Long> {

	@SuppressWarnings("unchecked")
	FlatEntity save(FlatEntity flatEntity);
	
	void delete(Long id);
	
	void delete(FlatEntity bEntity);
	
	FlatEntity findOne(Long id);
	
	FlatEntity getOne(Long id);
	
	List<FlatEntity> findAll();
	
	List<FlatEntity> findByBuilding(BuildingEntity building);

	List<FlatEntity> findByStatus(FLAT_STATUS status);

	List<FlatEntity> findByFloor(int floor);

	List<FlatEntity> findBySizeBetween(int min, int max);
	
	List<FlatEntity> findByRoomsBetween(int min, int max);
	
	List<FlatEntity> findByBalcoonsBetween(int min, int max);
	
}
