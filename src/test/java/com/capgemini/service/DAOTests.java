package com.capgemini.service;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.capgemini.domain.BuildingEntity;
import com.capgemini.repository.BuildingRepository;
import com.capgemini.types.BuildingTO;
import com.capgemini.types.FlatTO;

@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest
public class DAOTests {

	@Autowired
	private BuildingService buildingService;

	@Autowired
	private BuildingRepository buildingRepository;

	@Autowired
	private FlatService flatService;

	@Test
	public void testShouldAddBuilding() {

		// given
		BuildingEntity bEntity = new BuildingEntity();
		bEntity.setDescription("Poznań. Wilda");
		bEntity.setElevator(true);
		bEntity.setFlatsSum(10);
		bEntity.setFloors(3);
		bEntity.setLocalization("ul.Rolna 1");
		BuildingEntity addedBuilding = buildingRepository.save(bEntity);

		// when
		int exceptedfloors = 3;
		int floors = addedBuilding.getFloors();
		// then
		Assert.assertEquals(exceptedfloors, floors);
	}

	@Test
	public void testShouldGetBuilding() {

		// given
		BuildingEntity bEntity = new BuildingEntity();
		bEntity.setDescription("Poznań. Wilda");
		bEntity.setElevator(true);
		bEntity.setFlatsSum(10);
		bEntity.setFloors(3);
		bEntity.setLocalization("ul.Rolna 1");
		BuildingEntity addedBuilding = buildingRepository.save(bEntity);

		BuildingEntity bEntity2 = new BuildingEntity();
		bEntity.setDescription("Poznań. Debiec");
		bEntity.setElevator(true);
		bEntity.setFlatsSum(15);
		bEntity.setFloors(4);
		bEntity.setLocalization("ul.Jasna 5");
		BuildingEntity addedBuilding2 = buildingRepository.save(bEntity2);

		// when
		int exceptedfloors = addedBuilding.getFloors();
		BuildingEntity gettedBuilding = buildingRepository.getOne(addedBuilding.getId());
		// then
		Assert.assertEquals(exceptedfloors, gettedBuilding.getFloors());
	}

	@Test
	public void testShouldGetBuildingByLocalization() {

		// given
		BuildingEntity bEntity = new BuildingEntity();
		bEntity.setDescription("Poznań. Wilda");
		bEntity.setElevator(true);
		bEntity.setFlatsSum(10);
		bEntity.setFloors(3);
		bEntity.setLocalization("ul.Rolna 1");
		BuildingEntity addedBuilding = buildingRepository.save(bEntity);

		// when
		int exceptedfloors = addedBuilding.getFloors();
		List<BuildingEntity> gettedBuildings = buildingRepository.findByLocalization("ul.Rolna 1");
		// then
		Assert.assertEquals(exceptedfloors, gettedBuildings.get(0).getFloors());
	}

	@Test
	public void testShouldGetSumPriceOfClientFlats() {

		// given
		FlatTO flatTO = new FlatTO();
		flatTO.setAddress("A2");
		flatTO.setPrice(100000);
		flatTO.setStatus("FREE");

		FlatTO flatTO2 = new FlatTO();
		flatTO2.setAddress("A4");
		flatTO2.setPrice(200000);
		flatTO2.setStatus("FREE");
		flatTO2 = flatService.addFlat(flatTO2);

		FlatTO flatTO3 = new FlatTO();
		flatTO3.setAddress("A5");
		flatTO3.setPrice(160000);
		flatTO3.setStatus("FREE");
		flatTO3 = flatService.addFlat(flatTO3);

		FlatTO flatTO4 = new FlatTO();
		flatTO4.setAddress("A6");
		flatTO4.setPrice(120000);
		flatTO4.setStatus("FREE");
		flatTO4 = flatService.addFlat(flatTO4);

		FlatTO flatTO5 = new FlatTO();
		flatTO5.setAddress("A5");
		flatTO5.setPrice(140000);
		flatTO5.setStatus("FREE");
		flatTO5 = flatService.addFlat(flatTO5);

		BuildingTO buildingTO = new BuildingTO();
		buildingTO.setLocalization("Poznań");
		buildingTO = buildingService.addBuilding(buildingTO);

		BuildingTO buildingTO2 = new BuildingTO();
		buildingTO2.setLocalization("Wrocław");
		buildingTO2 = buildingService.addBuilding(buildingTO2);

		flatTO = flatService.addFlat(flatTO);
		flatTO2 = flatService.addFlat(flatTO2);
		flatTO3 = flatService.addFlat(flatTO3);
		flatTO4 = flatService.addFlat(flatTO4);
		flatTO5 = flatService.addFlat(flatTO5);

		int building1 = (100000 + 160000) / 2;
		int building2 = (200000 + 120000 + 140000) / 3;

		// when
		flatService.addFlatToBuilding(flatTO.getId(), buildingTO.getId());
		flatService.addFlatToBuilding(flatTO2.getId(), buildingTO2.getId());
		flatService.addFlatToBuilding(flatTO3.getId(), buildingTO.getId());
		flatService.addFlatToBuilding(flatTO4.getId(), buildingTO2.getId());
		flatService.addFlatToBuilding(flatTO5.getId(), buildingTO2.getId());

		int cost1 = buildingRepository.averagePriceOfFlatInBuilding(buildingTO.getId());
		int cost2 = buildingRepository.averagePriceOfFlatInBuilding(buildingTO2.getId());

		// then
		Assert.assertEquals(building1, cost1);
		Assert.assertEquals(building2, cost2);
	}
}
