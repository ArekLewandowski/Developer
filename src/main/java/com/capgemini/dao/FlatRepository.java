package com.capgemini.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capgemini.domain.BuildingEntity;
import com.capgemini.domain.FlatEntity;

@Repository
public interface FlatRepository extends JpaRepository<FlatEntity, Long> {

	@SuppressWarnings("unchecked")
	FlatEntity save(FlatEntity flatEntity);
	
	void delete(Long id);
	
	void delete(FlatEntity bEntity);
	
	FlatEntity findOne(Long id);
	
	FlatEntity getOne(Long id);
	
	List<FlatEntity> findAll();
}
