package com.capgemini.mappers;

import java.util.LinkedList;
import java.util.List;

import com.capgemini.domain.BuildingEntity;
import com.capgemini.domain.FlatEntity;
import com.capgemini.types.BuildingTO;

public class BuildingMapper {

	public static BuildingTO map2TO(BuildingEntity buildingEntity) {
		BuildingTO buildingTO = BuildingTO.builder().id(buildingEntity.getId())
				.description(buildingEntity.getDescription()).elevator(buildingEntity.isElevator())
				.localization(buildingEntity.getLocalization()).flatsSum(buildingEntity.getFlatsSum())
				.floors(buildingEntity.getFloors()).version(buildingEntity.getVersion()).flatsId(new LinkedList<>())
				.build();
		List<FlatEntity> flats = buildingEntity.getFlats();
		if (flats.size() != 0) {
			for (FlatEntity flatEntity : flats) {
				buildingTO.addFlatId(flatEntity.getId());
			}
		}
		return buildingTO;
	}

	public static BuildingEntity map2Entity(BuildingTO buildingTO, BuildingEntity buildingEntity) {
		buildingEntity.setDescription(buildingTO.getDescription());
		buildingEntity.setLocalization(buildingTO.getLocalization());
		buildingEntity.setElevator(buildingTO.isElevator());
		buildingEntity.setFlatsSum(buildingTO.getFlatsSum());
		buildingEntity.setFloors(buildingTO.getFloors());
		buildingEntity.setVersion(buildingTO.getVersion());
		return buildingEntity;
	}

	public static BuildingEntity map2Entity(BuildingTO buildingTO) {
		BuildingEntity buildingEntity = new BuildingEntity();
		buildingEntity = map2Entity(buildingTO, buildingEntity);
		return buildingEntity;
	}

	public static List<BuildingEntity> map2Entities(List<BuildingTO> buildingTOs) {
		List<BuildingEntity> buildingEntities = new LinkedList<>();
		for (BuildingTO buildingTO : buildingTOs) {
			buildingEntities.add(map2Entity(buildingTO));
		}
		return buildingEntities;
	}

	public static List<BuildingTO> map2TOs(List<BuildingEntity> buildingEntities) {
		List<BuildingTO> buildingTOs = new LinkedList<>();
		for (BuildingEntity buildingEntity : buildingEntities) {
			buildingTOs.add(BuildingMapper.map2TO(buildingEntity));
		}
		return buildingTOs;
	}
}
