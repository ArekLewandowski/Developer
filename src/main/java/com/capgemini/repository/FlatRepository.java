package com.capgemini.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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

	List<FlatEntity> findByBuilding(BuildingEntity building);

	List<FlatEntity> findByStatus(String status);

	List<FlatEntity> findByFloor(int floor);

	List<FlatEntity> findBySizeBetween(int min, int max);

	List<FlatEntity> findByRoomsBetween(int min, int max);

	List<FlatEntity> findByBalcoonsBetween(int min, int max);

	@Query("select f from FlatEntity f where upper(f.status) LIKE :status and f.building.id = :buildingId")
	List<FlatEntity> findFlatByStatusAndBuilding(@Param("status") String status, @Param("buildingId") Long buildingId);

	@Query("select f from FlatEntity f join f.building b where b.elevator = true or f.floor = 0")
	List<FlatEntity> findDisabledAppropriateFlats();

	@Query("select count(*) from FlatEntity f where upper(f.status) LIKE 'FREE' group by f.building order by count(*) desc")
	List<Long> getMaxOfFreeFlatsInBuilding();

	@Query("select f.building.id from FlatEntity f where upper(f.status) LIKE 'FREE' and count(*) = :max group by f.building")
	List<Long> getMostAvelibleBuildings(@Param("max") long max);

}
