package com.capgemini.mappers;

import java.util.List;

import com.capgemini.domain.BuildingEntity;
import com.capgemini.domain.FlatEntity;
import com.capgemini.types.BuildingTO;

public class BuildingMapper {

	public static BuildingTO map2TO(BuildingEntity buildingEntity) {
		BuildingTO buildingTO = BuildingTO.builder()
				.id(buildingEntity.getId())
				.description(buildingEntity.getDescription())
				.elevator(buildingEntity.isElevator())
				.localization(buildingEntity.getLocalization())
				.flatsSum(buildingEntity.getFlatsSum())
				.floors(buildingEntity.getFloors())
				.build();
		List<FlatEntity> flats = buildingEntity.getFlats();
		for (FlatEntity flatEntity : flats) {
			buildingTO.addFlatId(flatEntity.getId());
		}
		return buildingTO;
	}
	
	@SuppressWarnings("static-access")
	public static BuildingEntity map2Entity(BuildingTO buildingTO, BuildingEntity buildingEntity, List<FlatEntity> flats) {
		buildingEntity.builder()
		.description(buildingTO.getDescription())
		.localization(buildingTO.getLocalization())
		.elevator(buildingTO.isElevator())
		.flatsSum(buildingTO.getFlatsSum())
		.floors(buildingTO.getFlatsSum())
		.build();
		for (FlatEntity flatEntity : flats) {
			buildingEntity.addFlat(flatEntity);
		}
		return buildingEntity;
	}
	
	public static BuildingEntity map2Entity(BuildingTO buildingTO) {
		BuildingEntity buildingEntity = new BuildingEntity();
		buildingEntity = map2Entity(buildingTO, buildingEntity, null);
		return buildingEntity;
	}
}
