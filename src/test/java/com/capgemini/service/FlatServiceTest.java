package com.capgemini.service;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.capgemini.enums.FLAT_STATUS;
import com.capgemini.types.BuildingTO;
import com.capgemini.types.FlatTO;

import javassist.tools.framedump;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FlatServiceTest {
	
	@Autowired
	private FlatService flatService;
	
	@Autowired
	private BuildingService buildingService;
	
	@Test
	public void testShouldAddAndGetFlat(){
		
		//given
		FlatTO flatTO = new FlatTO();
		flatTO.setAddress("A1");
		flatTO.setBalcoons(2);
		flatTO.setFloor(0);
		flatTO.setRooms(2);
		flatTO.setSize(46);
		flatTO.setStatus(FLAT_STATUS.FREE);
		flatTO.setPrice(205000);
		flatTO = flatService.addFlat(flatTO);
		
		//when
		FlatTO addedflatTO = flatService.getFlatById(flatTO.getId());
		
		//then
		Assert.assertEquals(addedflatTO.getRooms(), flatTO.getRooms());
	}
	
	@Test
	public void testShouldGetFlatByFloor(){
		
		//given
		FlatTO flatTO = new FlatTO();
		flatTO.setAddress("A2");
		flatTO.setBalcoons(1);
		flatTO.setFloor(1);
		flatTO.setRooms(3);
		flatTO.setSize(55);
		flatTO.setStatus(FLAT_STATUS.FREE);
		flatTO.setPrice(26000);
		flatTO = flatService.addFlat(flatTO);
		
		//when
		List<FlatTO> addedflatTOs = flatService.getFlatByFloor(1);
		
		//then
		Assert.assertEquals(addedflatTOs.get(0).getFloor(), flatTO.getFloor());
	}
	
	@Test
	public void testShouldGetFlatByStatus(){
		
		//given
		List<FlatTO> preAddedflatTOs = flatService.getFlatByStatus(FLAT_STATUS.FREE);
		List<FlatTO> preAddedflatTOs2 = flatService.getFlatByStatus(FLAT_STATUS.SOLD);
		
		FlatTO flatTO = new FlatTO();
		flatTO.setAddress("A2");
		flatTO.setBalcoons(1);
		flatTO.setFloor(1);
		flatTO.setRooms(3);
		flatTO.setSize(55);
		flatTO.setStatus(FLAT_STATUS.FREE);
		flatTO.setPrice(26000);
		flatTO = flatService.addFlat(flatTO);
		
		//when
		List<FlatTO> addedflatTOs = flatService.getFlatByStatus(FLAT_STATUS.FREE);
		List<FlatTO> addedflatTOs2 = flatService.getFlatByStatus(FLAT_STATUS.SOLD);
		
		//then
		Assert.assertEquals(addedflatTOs.get(0).getStatus(), flatTO.getStatus());
		Assert.assertEquals(preAddedflatTOs.size()+1, addedflatTOs.size());
		Assert.assertEquals(preAddedflatTOs2.size(), addedflatTOs2.size());
	}
	
	@Test
	public void testShouldAddAndGetFlatToBuilding(){
		
		//given
		FlatTO flatTO = new FlatTO();
		flatTO.setAddress("A3");
		flatTO.setBalcoons(2);
		flatTO.setFloor(0);
		flatTO.setRooms(3);
		flatTO.setSize(46);
		flatTO.setStatus(FLAT_STATUS.FREE);
		flatTO.setPrice(215000);
		flatTO = flatService.addFlat(flatTO);
		
		BuildingTO buildingTO = new BuildingTO();
		buildingTO.setDescription("New");
		buildingTO.setElevator(true);
		buildingTO.setFlatsSum(10);
		buildingTO.setFloors(3);
		buildingTO.setLocalization("Poznań");
		buildingTO = buildingService.addBuilding(buildingTO);
		
		//when
		flatService.addFlatToBuilding(flatTO.getId(), buildingTO.getId());
		List<FlatTO> addedflatTO = flatService.getFlatByBuilding(buildingTO);
		
		//then
		System.out.println(addedflatTO.toString());
		Assert.assertEquals(addedflatTO.get(0).getRooms(), flatTO.getRooms());
	}
	
	@Test
	public void testShouldGetFlatByBuilding(){
//		
//		List<FlatTO> preAddedflatTO = flatService.getFlatByBuilding(buildingTO);
//		List<FlatTO> preAddedflatTO2 = flatService.getFlatByBuilding(buildingTO2);
		
		//given
		FlatTO flatTO = new FlatTO();
		flatTO.setAddress("A2");
		flatTO.setBalcoons(1);
		flatTO.setFloor(1);
		flatTO.setRooms(3);
		flatTO.setSize(55);
		flatTO.setStatus(FLAT_STATUS.FREE);
		flatTO.setPrice(26000);
		
		FlatTO flatTO2 = new FlatTO();
		flatTO2.setAddress("A4");
		flatTO2.setBalcoons(1);
		flatTO2.setFloor(2);
		flatTO2.setRooms(1);
		flatTO2.setSize(22);
		flatTO2.setStatus(FLAT_STATUS.FREE);
		flatTO2.setPrice(115000);
		flatTO2 = flatService.addFlat(flatTO2);
		
		FlatTO flatTO3 = new FlatTO();
		flatTO3.setAddress("A5");
		flatTO3.setBalcoons(1);
		flatTO3.setFloor(2);
		flatTO3.setRooms(3);
		flatTO3.setSize(55);
		flatTO3.setStatus(FLAT_STATUS.FREE);
		flatTO3.setPrice(305000);
		flatTO3 = flatService.addFlat(flatTO3);
		
		BuildingTO buildingTO = new BuildingTO();
		buildingTO.setDescription("New Apartments");
		buildingTO.setElevator(true);
		buildingTO.setFlatsSum(10);
		buildingTO.setFloors(3);
		buildingTO.setLocalization("Poznań");
		buildingTO = buildingService.addBuilding(buildingTO);
		
		BuildingTO buildingTO2 = new BuildingTO();
		buildingTO2.setDescription("New Apartments");
		buildingTO2.setElevator(true);
		buildingTO2.setFlatsSum(10);
		buildingTO2.setFloors(3);
		buildingTO2.setLocalization("Poznań");
		buildingTO2 = buildingService.addBuilding(buildingTO2);
		
		flatTO = flatService.addFlat(flatTO);
		flatTO2 = flatService.addFlat(flatTO2);
		flatTO3 = flatService.addFlat(flatTO3);
		
		//when
		flatService.addFlatToBuilding(flatTO.getId(), buildingTO.getId());
		flatService.addFlatToBuilding(flatTO2.getId(), buildingTO2.getId());
		flatService.addFlatToBuilding(flatTO3.getId(), buildingTO.getId());
		List<FlatTO> addedflatTO = flatService.getFlatByBuilding(buildingTO);
		List<FlatTO> addedflatTO2 = flatService.getFlatByBuilding(buildingTO2);
		
		//then
		System.out.println("Test1: Dodane mieszkania. Oczekiwwane: "+ addedflatTO.size());
		System.out.println(addedflatTO.toString());
		System.out.println("Test2: Dodane mieszkania. Oczekiwwane: "+ addedflatTO2.size());
		System.out.println(addedflatTO2.toString());
		
		Assert.assertEquals(2, addedflatTO.size());
		Assert.assertEquals(1, addedflatTO2.size());
	}
	
	@Test
	public void testShouldGetFlatRoomsOrSize(){
//		
		List<FlatTO> preaddedflatTO = flatService.getFlatByRoomsFromTO(2, 3);
		List<FlatTO> preAddedflatTO2 = flatService.getFlatBySizeFromTO(40, 60);
		
		//given
		FlatTO flatTO = new FlatTO();
		flatTO.setAddress("A2");
		flatTO.setBalcoons(2);
		flatTO.setFloor(1);
		flatTO.setRooms(3);
		flatTO.setSize(59);
		flatTO.setStatus(FLAT_STATUS.FREE);
		flatTO.setPrice(26000);
		
		FlatTO flatTO2 = new FlatTO();
		flatTO2.setAddress("A4");
		flatTO2.setBalcoons(2);
		flatTO2.setFloor(2);
		flatTO2.setRooms(1);
		flatTO2.setSize(22);
		flatTO2.setStatus(FLAT_STATUS.FREE);
		flatTO2.setPrice(115000);
		flatTO2 = flatService.addFlat(flatTO2);
		
		FlatTO flatTO3 = new FlatTO();
		flatTO3.setAddress("A5");
		flatTO3.setBalcoons(0);
		flatTO3.setFloor(2);
		flatTO3.setRooms(4);
		flatTO3.setSize(55);
		flatTO3.setStatus(FLAT_STATUS.FREE);
		flatTO3.setPrice(305000);
		flatTO3 = flatService.addFlat(flatTO3);
		
		FlatTO flatTO4 = new FlatTO();
		flatTO4.setAddress("A6");
		flatTO4.setBalcoons(3);
		flatTO4.setFloor(4);
		flatTO4.setRooms(2);
		flatTO4.setSize(45);
		flatTO4.setStatus(FLAT_STATUS.FREE);
		flatTO4.setPrice(215000);
		flatTO4 = flatService.addFlat(flatTO4);
		
		BuildingTO buildingTO = new BuildingTO();
		buildingTO.setDescription("New Apartments");
		buildingTO.setElevator(true);
		buildingTO.setFlatsSum(0);
		buildingTO.setFloors(3);
		buildingTO.setLocalization("Poznań");
		buildingTO = buildingService.addBuilding(buildingTO);
		
		BuildingTO buildingTO2 = new BuildingTO();
		buildingTO2.setDescription("New Apartments");
		buildingTO2.setElevator(true);
		buildingTO2.setFlatsSum(0);
		buildingTO2.setFloors(3);
		buildingTO2.setLocalization("Poznań");
		buildingTO2 = buildingService.addBuilding(buildingTO2);
		
		flatTO = flatService.addFlat(flatTO);
		flatTO2 = flatService.addFlat(flatTO2);
		flatTO3 = flatService.addFlat(flatTO3);
		flatTO4 = flatService.addFlat(flatTO4);
		
		//when
		flatService.addFlatToBuilding(flatTO.getId(), buildingTO.getId());
		flatService.addFlatToBuilding(flatTO2.getId(), buildingTO2.getId());
		flatService.addFlatToBuilding(flatTO3.getId(), buildingTO.getId());
		flatService.addFlatToBuilding(flatTO4.getId(), buildingTO.getId());
		List<FlatTO> addedflatTO = flatService.getFlatByRoomsFromTO(2, 3);
		List<FlatTO> addedflatTO2 = flatService.getFlatBySizeFromTO(40, 50);
			
		//then
		System.out.println("Test3: Dodane mieszkania. Oczekiwwane: "+ addedflatTO.size());
		System.out.println(addedflatTO.toString());
		System.out.println("Test4: Dodane mieszkania. Oczekiwwane: "+ addedflatTO2.size());
		System.out.println(addedflatTO2.toString());
		for (FlatTO flatTO5 : addedflatTO) {
			Assert.assertTrue(flatTO5.getRooms() == 2 || flatTO5.getRooms() == 3);
		}
		for (FlatTO flatTO5 : addedflatTO2) {
			Assert.assertTrue(flatTO5.getSize() > 40 || flatTO5.getSize() < 50);
		}
	}

	@Test
	public void testShouldGetAndIncrementFlatsSum(){
//		
		List<FlatTO> preaddedflatTO = flatService.getFlatByRoomsFromTO(2, 3);
		List<FlatTO> preAddedflatTO2 = flatService.getFlatBySizeFromTO(40, 60);
		
		//given
		FlatTO flatTO = new FlatTO();
		flatTO.setAddress("A2");
		flatTO.setBalcoons(2);
		flatTO.setFloor(1);
		flatTO.setRooms(3);
		flatTO.setSize(59);
		flatTO.setStatus(FLAT_STATUS.FREE);
		flatTO.setPrice(26000);
		
		FlatTO flatTO2 = new FlatTO();
		flatTO2.setAddress("A4");
		flatTO2.setBalcoons(2);
		flatTO2.setFloor(2);
		flatTO2.setRooms(1);
		flatTO2.setSize(22);
		flatTO2.setStatus(FLAT_STATUS.FREE);
		flatTO2.setPrice(115000);
		flatTO2 = flatService.addFlat(flatTO2);
		
		FlatTO flatTO3 = new FlatTO();
		flatTO3.setAddress("A5");
		flatTO3.setBalcoons(0);
		flatTO3.setFloor(2);
		flatTO3.setRooms(4);
		flatTO3.setSize(55);
		flatTO3.setStatus(FLAT_STATUS.FREE);
		flatTO3.setPrice(305000);
		flatTO3 = flatService.addFlat(flatTO3);
		
		FlatTO flatTO4 = new FlatTO();
		flatTO4.setAddress("A6");
		flatTO4.setBalcoons(3);
		flatTO4.setFloor(4);
		flatTO4.setRooms(2);
		flatTO4.setSize(45);
		flatTO4.setStatus(FLAT_STATUS.FREE);
		flatTO4.setPrice(215000);
		flatTO4 = flatService.addFlat(flatTO4);
		
		BuildingTO buildingTO = new BuildingTO();
		buildingTO.setDescription("New Apartments");
		buildingTO.setElevator(true);
		buildingTO.setFlatsSum(0);
		buildingTO.setFloors(3);
		buildingTO.setLocalization("Poznań");
		buildingTO = buildingService.addBuilding(buildingTO);
		
		BuildingTO buildingTO2 = new BuildingTO();
		buildingTO2.setDescription("New Apartments");
		buildingTO2.setElevator(true);
		buildingTO2.setFlatsSum(0);
		buildingTO2.setFloors(3);
		buildingTO2.setLocalization("Poznań");
		buildingTO2 = buildingService.addBuilding(buildingTO2);
		
		flatTO = flatService.addFlat(flatTO);
		flatTO2 = flatService.addFlat(flatTO2);
		flatTO3 = flatService.addFlat(flatTO3);
		flatTO4 = flatService.addFlat(flatTO4);
		
		//when
		int flatsIn1Building = 0;
		int flatsIn2Building = 0;
		flatService.addFlatToBuilding(flatTO.getId(), buildingTO.getId());
		flatsIn1Building++;
		flatService.addFlatToBuilding(flatTO2.getId(), buildingTO2.getId());
		flatsIn2Building++;
		flatService.addFlatToBuilding(flatTO3.getId(), buildingTO.getId());
		flatsIn1Building++;
		flatService.addFlatToBuilding(flatTO4.getId(), buildingTO.getId());
		flatsIn1Building++;
		int flats = buildingService.getBuildingById(buildingTO.getId()).getFlatsSum();
		int flats2 = buildingService.getBuildingById(buildingTO2.getId()).getFlatsSum();
		
		
		//then

		System.out.println("Suma mieszkań Budynek 1:" +flats);
		System.out.println("Suma mieszkań Budynek 2:" +flats2);


		Assert.assertEquals(flatsIn1Building, flats);
		Assert.assertEquals(flatsIn2Building, flats2);
	}

}
