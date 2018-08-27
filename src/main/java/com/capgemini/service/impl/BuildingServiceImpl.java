package com.capgemini.service.impl;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.domain.BuildingEntity;
import com.capgemini.mappers.BuildingMapper;
import com.capgemini.repository.BuildingRepository;
import com.capgemini.repository.FlatRepository;
import com.capgemini.service.BuildingService;
import com.capgemini.types.BuildingTO;

@Service
@Transactional
public class BuildingServiceImpl implements BuildingService {

	@Autowired
	BuildingRepository buildingRepository;
	
	@Autowired
	FlatRepository flatRepository;
	
	@Override
	public BuildingTO addBuilding(BuildingTO buildingTO) {
		BuildingEntity bEntity = BuildingMapper.map2Entity(buildingTO);
		bEntity = buildingRepository.save(bEntity);
		return BuildingMapper.map2TO(bEntity);
	}
	
	@Override
	public BuildingTO updateBuilding(BuildingTO buildingTO) {
		BuildingEntity currentEntity = buildingRepository.findOne(buildingTO.getId());
		if (currentEntity.getVersion() != buildingTO.getVersion()) {
			throw new OptimisticLockException(); 
		}
		BuildingEntity bEntity = BuildingMapper.map2Entity(buildingTO, currentEntity);
		bEntity = buildingRepository.save(bEntity);
		return BuildingMapper.map2TO(bEntity);
	}

	@Override
	public BuildingTO removeBuilding(Long id) {
		BuildingTO deletedBuilding = BuildingMapper.map2TO(buildingRepository.findOne(id));
		buildingRepository.delete(id);
		return deletedBuilding;
	}

	@Override
	public List<BuildingTO> findAll() {
		List<BuildingTO> buildingTOs = new LinkedList<>();
		List<BuildingEntity> buildingEntities = buildingRepository.findAll();
		for (BuildingEntity buildingEntity : buildingEntities) {
			buildingTOs.add(BuildingMapper.map2TO(buildingEntity));
		}
		return buildingTOs;
	}
	
	@Override
	public BuildingTO getBuildingById(Long id){
		return BuildingMapper.map2TO(buildingRepository.findOne(id));
	}

	@Override
	public List<BuildingTO> getBuildingByLocalization(String localization) {
		List<BuildingEntity> buildingEntities = buildingRepository.findByLocalization(localization);
		if (buildingEntities == null) {
			return null;
		}
		List<BuildingTO> buildingTOs = new LinkedList<>();
		for (BuildingEntity buildingEntity : buildingEntities) {
			buildingTOs.add(BuildingMapper.map2TO(buildingEntity));
		}
		return buildingTOs;
	}
	
	@Override
	public List<BuildingTO> getBuildingByFloors(int floors) {
		List<BuildingEntity> buildingEntities = buildingRepository.findByFloors(floors);
		if (buildingEntities == null) {
			return null;
		}
		List<BuildingTO> buildingTOs = new LinkedList<>();
		for (BuildingEntity buildingEntity : buildingEntities) {
			buildingTOs.add(BuildingMapper.map2TO(buildingEntity));
		}
		return buildingTOs;
	}

	@Override
	public Integer getAveragePriceOfApartmentInSelectedBuilding(Long buildingId) {
		int averidgePrice = buildingRepository.averageCostOfFlatInBuilding(buildingId);
		return averidgePrice;
	}

	@Override
	public Integer sumAllFlatsOfSelectedClient(Long clientId) {
		int sumOfClientFlats = buildingRepository.sumAllBoughtFlatsByClient(clientId);
		return sumOfClientFlats;
	}
	
	@Override
	public List<Long> mostAvaibleBuilding() {
		List<Long> numbersOfFlats = flatRepository.getMaxOfFreeFlatsInBuilding();
		Long max = numbersOfFlats.get(0);
		List<Long> buildings = flatRepository.getMostAvelibleBuildings(max);
		return buildings;
	}
}
