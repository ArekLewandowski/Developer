package com.capgemini.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.capgemini.domain.ClientEntity;
import com.capgemini.domain.FlatEntity;

@Repository
public interface ClientRepository extends JpaRepository<ClientEntity, Long> {

	@SuppressWarnings("unchecked")
	ClientEntity save(ClientEntity clientEntity);

	void delete(Long id);

	void delete(ClientEntity clientEntity);

	ClientEntity findOne(Long id);

	List<ClientEntity> findAll();
	
	@Query("select c from ClientEntity c join c.owned owned where (select count(*) from owned where owned.status = 'SOLD')>1 Group by c.id")
	List<ClientEntity> findClientsWithMoreThan1Flat();

}
