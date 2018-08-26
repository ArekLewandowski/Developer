package com.capgemini.repository;

import java.util.List;

import com.capgemini.domain.BuildingEntity;
import com.capgemini.enums.FlatStatus;

public interface BuildingRepositoryCustom {

	int countSumOfFlatsBoughtByClient(Long clientId);

	int showAveragePriceOfFlatInTheBuilding(Long buildingId);

	Long countNumberOfFlatsByStatus(FlatStatus Status, Long buildingId);

	List<BuildingEntity> findBuildingsWithMaxmimumNumberFreeFlats();

}
