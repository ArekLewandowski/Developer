package com.capgemini.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capgemini.domain.ClientEntity;

@Repository
public interface ClientRepository extends JpaRepository<ClientEntity, Long> {

	@SuppressWarnings("unchecked")
	ClientEntity save(ClientEntity clientEntity);

	void delete(Long id);

	void delete(ClientEntity clientEntity);

	ClientEntity findOne(Long id);

	List<ClientEntity> findAll();

}
