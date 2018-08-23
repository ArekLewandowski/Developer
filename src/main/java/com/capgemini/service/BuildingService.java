package com.capgemini.service;

import java.util.List;

import com.capgemini.types.BuildingTO;

public interface BuildingService {

	BuildingTO addBuilding(BuildingTO buildingTO);
	
	BuildingTO removeBuilding(Long id);
	
	List<BuildingTO> findAll();
	
	List<BuildingTO> getBuildingByLocalization(String localization);

	BuildingTO getBuildingById(Long id);

	List<BuildingTO> getBuildingByFloors(int floors);
}
