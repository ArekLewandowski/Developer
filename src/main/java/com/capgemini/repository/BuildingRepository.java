package com.capgemini.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.capgemini.domain.BuildingEntity;

@Repository
public interface BuildingRepository extends JpaRepository<BuildingEntity, Long> {

	BuildingEntity save(BuildingEntity buildingEntity);

	void delete(Long id);

	void delete(BuildingEntity bEntity);

	BuildingEntity findOne(Long id);

	List<BuildingEntity> findByLocalization(String localization);

	List<BuildingEntity> findByFloors(int floors);

	List<BuildingEntity> findAll();

	@Query("select sum(f.price) from FlatEntity f join f.owner o where o.id = :clientId")
	Integer sumAllBoughtFlatsByClient(@Param("clientId") Long clientId);

	@Query("select avg(f.price) from FlatEntity f where f.building.id = :buildingId")
	Integer averageCostOfFlatInBuilding(@Param("buildingId") Long buildingId);

	@Query("select avg(f.price) from BuildingEntity b join b.flats f on f.building.id = :buildingId")
	Integer averagePriceOfFlatInBuilding(@Param("buildingId") Long buildingId);
}
