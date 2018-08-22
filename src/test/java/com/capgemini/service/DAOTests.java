package com.capgemini.service;

import javax.transaction.Transactional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.capgemini.dao.BuildingRepository;
import com.capgemini.domain.BuildingEntity;
@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest
public class DAOTests {

	@Autowired
	private BuildingRepository buildingDAO;
	
	@Test
	public void testShouldAddBuilding() {
		
		//given
		BuildingEntity bEntity = new BuildingEntity();
		bEntity.setDescription("Poznań. Wilda");
		bEntity.setElevator(true);
		bEntity.setFlatsSum(10);
		bEntity.setFloors(3);
		bEntity.setLocalization("ul.Rolna 1");
		BuildingEntity addedBuilding = buildingDAO.save(bEntity);
	
		//when
		int exceptedfloors = 3;
		int floors = addedBuilding.getFloors();
		//then
		Assert.assertEquals(exceptedfloors, floors);
	}
	
	@Test
	public void testShouldGetBuilding() {
		
		//given
		BuildingEntity bEntity = new BuildingEntity();
		bEntity.setDescription("Poznań. Wilda");
		bEntity.setElevator(true);
		bEntity.setFlatsSum(10);
		bEntity.setFloors(3);
		bEntity.setLocalization("ul.Rolna 1");
		BuildingEntity addedBuilding = buildingDAO.save(bEntity);
		
		BuildingEntity bEntity2 = new BuildingEntity();
		bEntity.setDescription("Poznań. Debiec");
		bEntity.setElevator(true);
		bEntity.setFlatsSum(15);
		bEntity.setFloors(4);
		bEntity.setLocalization("ul.Jasna 5");
		BuildingEntity addedBuilding2 = buildingDAO.save(bEntity2);
	
		//when
		int exceptedfloors = addedBuilding.getFloors();
		BuildingEntity gettedBuilding = buildingDAO.getOne(addedBuilding.getId());
		//then
		Assert.assertEquals(exceptedfloors, gettedBuilding.getFloors());
	}
	
	@Test
	public void testShouldGetBuildingByLocalization() {
		
		//given
		BuildingEntity bEntity = new BuildingEntity();
		bEntity.setDescription("Poznań. Wilda");
		bEntity.setElevator(true);
		bEntity.setFlatsSum(10);
		bEntity.setFloors(3);
		bEntity.setLocalization("ul.Rolna 1");
		BuildingEntity addedBuilding = buildingDAO.save(bEntity);
	
		//when
		int exceptedfloors = addedBuilding.getFloors();
		BuildingEntity gettedBuilding = buildingDAO.findByLocalization("ul.Rolna 1");
		//then
		Assert.assertEquals(exceptedfloors, gettedBuilding.getFloors());
	}
}
