package com.capgemini.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.capgemini.domain.BuildingEntity;
import com.capgemini.domain.ClientEntity;
import com.capgemini.domain.FlatEntity;

@Repository
public interface ClientRepository extends JpaRepository<ClientEntity, Long> {
	
	@SuppressWarnings("unchecked")
	ClientEntity save(ClientEntity clientEntity);
	
	void delete(Long id);
	
	void delete(ClientEntity clientEntity);
	
	ClientEntity findOne(Long id);
	
	ClientEntity getOne(Long id);

	List<ClientEntity> findAll();
}
