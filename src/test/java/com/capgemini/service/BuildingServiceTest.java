package com.capgemini.service;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.capgemini.types.BuildingTO;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BuildingServiceTest {

	@Autowired
	private BuildingService buildingService;
	
	@Test
	public void testShouldAddAndGetBuilding(){
		
		//given
		BuildingTO buildingTO = BuildingTO.builder()
				.description("Poznań. Wilda")
				.elevator(true)
				.flatsSum(10)
				.floors(3)
				.localization("ul.Rolna 1")
				.build();
		buildingTO = buildingService.addBuilding(buildingTO);
		
		BuildingTO buildingTO2 = BuildingTO.builder()
				.description("Poznań. Grunwald")
				.elevator(true)
				.flatsSum(20)
				.floors(6)
				.localization("ul.Kolorowa 1")
				.build();
		buildingTO = buildingService.addBuilding(buildingTO2);
		
		//when
		BuildingTO gettedBuilding = buildingService.getBuildingById(buildingTO.getId());
		
		//then
		Assert.assertEquals(buildingTO, gettedBuilding);
	}
	
	@Test
	public void testShouldFindAllBuildings(){
		
		//given
		List<BuildingTO> preAddedBuildings = buildingService.findAll();
		BuildingTO buildingTO = BuildingTO.builder()
				.description("Poznań. Wilda")
				.elevator(true)
				.flatsSum(10)
				.floors(3)
				.localization("ul.Rolna 1")
				.build();
		buildingTO = buildingService.addBuilding(buildingTO);
		
		BuildingTO buildingTO2 = BuildingTO.builder()
				.description("Poznań. Grunwald")
				.elevator(true)
				.flatsSum(20)
				.floors(6)
				.localization("ul.Kolorowa 1")
				.build();
		buildingTO = buildingService.addBuilding(buildingTO2);
		
		//when
		List<BuildingTO> gettedBuildings = buildingService.findAll();
		
		//then
		Assert.assertEquals(preAddedBuildings.size()+2, gettedBuildings.size());
	}
	
	@Test
	public void testShouldGetBuildingByLocalization(){
		
		//given
		BuildingTO buildingTO = BuildingTO.builder()
				.description("Poznań. Wilda")
				.elevator(true)
				.flatsSum(10)
				.floors(3)
				.localization("ul.Rolna 1")
				.build();
		buildingTO = buildingService.addBuilding(buildingTO);
		
		BuildingTO buildingTO2 = BuildingTO.builder()
				.description("Poznań. Grunwald")
				.elevator(true)
				.flatsSum(20)
				.floors(6)
				.localization("ul.Kolorowa 1")
				.build();
		buildingTO2 = buildingService.addBuilding(buildingTO2);
		
		//when
		List<BuildingTO> gettedBuildings = buildingService.getBuildingByLocalization(buildingTO2.getLocalization());
		
		//then
		Assert.assertEquals(1, gettedBuildings.size());
		Assert.assertEquals(6, gettedBuildings.get(0).getFloors());
	}
	
	@Test
	public void testShouldGetBuildingByFloors(){
		
		//given
		BuildingTO buildingTO = BuildingTO.builder()
				.description("Poznań. Wilda")
				.elevator(true)
				.flatsSum(10)
				.floors(3)
				.localization("ul.Rolna 1")
				.build();
		buildingTO = buildingService.addBuilding(buildingTO);
		
		BuildingTO buildingTO2 = BuildingTO.builder()
				.description("Poznań. Grunwald")
				.elevator(true)
				.flatsSum(20)
				.floors(6)
				.localization("ul.Kolorowa 1")
				.build();
		buildingTO2 = buildingService.addBuilding(buildingTO2);
		
		//when
		List<BuildingTO> gettedBuildings = buildingService.getBuildingByFloors(buildingTO2.getFloors());
		
		//then
		Assert.assertEquals(2, gettedBuildings.size());
		Assert.assertEquals(6, gettedBuildings.get(0).getFloors());
	}
}
