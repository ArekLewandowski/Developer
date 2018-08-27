package com.capgemini.service;

import java.util.List;

import com.capgemini.types.BuildingTO;
import com.capgemini.types.FlatSearchCriteriaTO;
import com.capgemini.types.FlatTO;

public interface FlatService {

	FlatTO addFlat(FlatTO flatTO);

	FlatTO removeFlat(Long id);

	List<FlatTO> findAll();

	List<FlatTO> getFlatByBuilding(BuildingTO buildingTO);

	FlatTO getFlatById(Long id);

	List<FlatTO> getFlatByStatus(String status);

	List<FlatTO> getFlatByFloor(int floor);

	List<FlatTO> getFlatBySizeFromTO(int min, int max);

	List<FlatTO> getFlatByRoomsFromTO(int min, int max);

	List<FlatTO> getFlatByBalcoonsFromTO(int min, int max);

	List<FlatTO> disabledAppropriateFlats();

	FlatTO addFlatToBuilding(Long flatId, Long buildingId);

	FlatTO removeFlatFromBuilding(Long flatId);

	FlatTO updateFlat(FlatTO flatTO);

	List<FlatTO> findFlatByStatusAndBuilding(String status, Long BuildingId);

	List<FlatTO> findFlatByVariousCriteria(FlatSearchCriteriaTO flatSearchCriteriaTO);
}
