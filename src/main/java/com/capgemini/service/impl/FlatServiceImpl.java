package com.capgemini.service.impl;

import java.util.List;

import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.domain.BuildingEntity;
import com.capgemini.domain.FlatEntity;
import com.capgemini.enums.FlatStatus;
import com.capgemini.mappers.BuildingMapper;
import com.capgemini.mappers.FlatMapper;
import com.capgemini.repository.BuildingRepository;
import com.capgemini.repository.FlatRepository;
import com.capgemini.service.FlatService;
import com.capgemini.types.BuildingTO;
import com.capgemini.types.FlatTO;

@Service
@Transactional
public class FlatServiceImpl implements FlatService {

	@Autowired
	private FlatRepository flatRepository;

	@Autowired
	private BuildingRepository buildingRepository;

	@Override
	public FlatTO addFlat(FlatTO flatTO) {
		FlatEntity flatEntity = FlatMapper.map2Entity(flatTO);
		flatEntity = flatRepository.save(flatEntity);
		return FlatMapper.map2TO(flatEntity);
	}

	@Override
	public FlatTO updateFlat(FlatTO flatTO) {
		FlatEntity flatEntity = flatRepository.findOne(flatTO.getId());
		if (flatEntity.getVersion() != flatTO.getVersion()) {
			throw new OptimisticLockException();
		}
		flatEntity = FlatMapper.map2Entity(flatTO, flatEntity);
		flatEntity = flatRepository.save(flatEntity);
		return FlatMapper.map2TO(flatEntity);
	}

	@Override
	public FlatTO addFlatToBuilding(Long flatId, Long buildingId) {
		FlatEntity flatEntity = flatRepository.findOne(flatId);
		BuildingEntity buildingEntity = buildingRepository.findOne(buildingId);
		flatEntity.setBuilding(buildingEntity);
		buildingEntity.addFlat(flatEntity);
		buildingEntity.setFlatsSum(buildingEntity.getFlatsSum() + 1);
		FlatTO flatTO = FlatMapper.map2TO(flatEntity);
		BuildingTO buildindTO = BuildingMapper.map2TO(buildingEntity);
		return flatTO;
	}

	@Override
	public FlatTO removeFlatFromBuilding(Long flatId) {
		FlatEntity flatEntity = flatRepository.findOne(flatId);
		BuildingEntity buildingEntity = buildingRepository.findOne(flatEntity.getBuilding().getId());
		flatEntity.setBuilding(null);
		buildingEntity.getFlats().remove(flatEntity);
		buildingEntity.setFlatsSum(buildingEntity.getFlatsSum() - 1);
		FlatTO flatTO = FlatMapper.map2TO(flatEntity);
		BuildingTO buildindTO = BuildingMapper.map2TO(buildingEntity);
		return flatTO;
	}

	@Override
	public FlatTO removeFlat(Long id) {
		FlatEntity flatEntity = flatRepository.getOne(id);
		FlatTO flatTO = FlatMapper.map2TO(flatEntity);
		flatRepository.delete(id);
		return flatTO;
	}

	@Override
	public List<FlatTO> findAll() {
		List<FlatEntity> flatEntities = flatRepository.findAll();
		List<FlatTO> flatTOs = FlatMapper.map2TOs(flatEntities);
		return flatTOs;
	}

	@Override
	public List<FlatTO> getFlatByBuilding(BuildingTO buildingTO) {
		BuildingEntity buildingEntity = buildingRepository.findOne(buildingTO.getId());
		List<FlatEntity> flatEntities = flatRepository.findByBuilding(buildingEntity);
		List<FlatTO> flatTOs = FlatMapper.map2TOs(flatEntities);
		return flatTOs;
	}

	@Override
	public FlatTO getFlatById(Long id) {
		FlatEntity flatEntity = flatRepository.findOne(id);
		return FlatMapper.map2TO(flatEntity);
	}

	@Override
	public List<FlatTO> getFlatByStatus(FlatStatus status) {
		List<FlatEntity> flatEntities = flatRepository.findByStatus(status);
		List<FlatTO> flatTOs = FlatMapper.map2TOs(flatEntities);
		return flatTOs;
	}

	@Override
	public List<FlatTO> getFlatByFloor(int floor) {
		List<FlatEntity> flatEntities = flatRepository.findByFloor(floor);
		List<FlatTO> flatTOs = FlatMapper.map2TOs(flatEntities);
		return flatTOs;
	}

	@Override
	public List<FlatTO> getFlatBySizeFromTO(int min, int max) {
		List<FlatEntity> flatEntities = flatRepository.findBySizeBetween(min, max);
		List<FlatTO> flatTOs = FlatMapper.map2TOs(flatEntities);
		return flatTOs;
	}

	@Override
	public List<FlatTO> getFlatByRoomsFromTO(int min, int max) {
		List<FlatEntity> flatEntities = flatRepository.findByRoomsBetween(min, max);
		List<FlatTO> flatTOs = FlatMapper.map2TOs(flatEntities);
		return flatTOs;
	}

	@Override
	public List<FlatTO> getFlatByBalcoonsFromTO(int min, int max) {
		List<FlatEntity> flatEntities = flatRepository.findByBalcoonsBetween(min, max);
		List<FlatTO> flatTOs = FlatMapper.map2TOs(flatEntities);
		return flatTOs;
	}

	@Override
	public List<FlatTO> disabledAppropriateFlats() {
		List<FlatEntity> flatEntities = flatRepository.findDisabledAppropriateFlats();
		List<FlatTO> flats = FlatMapper.map2TOs(flatEntities);
		return flats;
	}

	@Override
	public List<FlatTO> findFlatByStatusAndBuilding(FlatStatus status, Long BuildingId) {
		List<FlatEntity> flatEntities = flatRepository.findFlatByStatusAndBuilding(status, BuildingId);
		List<FlatTO> flatTOs = FlatMapper.map2TOs(flatEntities);
		return flatTOs;
	}

}
