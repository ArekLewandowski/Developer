package com.capgemini.service;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.capgemini.enums.FlatStatus;
import com.capgemini.repository.BuildingRepository;
import com.capgemini.types.BuildingTO;
import com.capgemini.types.ClientTO;
import com.capgemini.types.FlatTO;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BuildingServiceTest {

	@Autowired
	private BuildingService buildingService;
	
	@Autowired
	private FlatService flatService;
	
	@Autowired
	private ClientService clientService;
	
	@Autowired
	private ReservationService reservationService;
	
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
	
	@Test
	public void testShouldGetAverigePriceOfFlatInBuilding() {
		
		//given	
		FlatTO flatTO = new FlatTO();
		flatTO.setAddress("A2");
		flatTO.setPrice(100000);
		flatTO.setStatus(FlatStatus.FREE);
		
		FlatTO flatTO2 = new FlatTO();
		flatTO2.setAddress("A4");
		flatTO2.setPrice(200000);
		flatTO2.setStatus(FlatStatus.FREE);
		flatTO2 = flatService.addFlat(flatTO2);
		
		FlatTO flatTO3 = new FlatTO();
		flatTO3.setAddress("A5");
		flatTO3.setPrice(160000);
		flatTO3.setStatus(FlatStatus.FREE);
		flatTO3 = flatService.addFlat(flatTO3);
		
		FlatTO flatTO4= new FlatTO();
		flatTO4.setAddress("A6");
		flatTO4.setPrice(120000);
		flatTO4.setStatus(FlatStatus.FREE);
		flatTO4 = flatService.addFlat(flatTO4);
		
		FlatTO flatTO5 = new FlatTO(); 
		flatTO5.setAddress("A5");
		flatTO5.setPrice(140000);
		flatTO5.setStatus(FlatStatus.FREE);
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
		
		//when
		flatService.addFlatToBuilding(flatTO.getId(), buildingTO.getId());
		flatService.addFlatToBuilding(flatTO2.getId(), buildingTO2.getId());
		flatService.addFlatToBuilding(flatTO3.getId(), buildingTO.getId());
		flatService.addFlatToBuilding(flatTO4.getId(), buildingTO2.getId());
		flatService.addFlatToBuilding(flatTO5.getId(), buildingTO2.getId());
	
	
		//when
		
		int cost1 = buildingService.getAveragePriceOfApartmentInSelectedBuilding(buildingTO.getId());
		int cost2 = buildingService.getAveragePriceOfApartmentInSelectedBuilding(buildingTO2.getId());

		//then
		Assert.assertEquals(building1, cost1);
		Assert.assertEquals(building2, cost2);
	}
	
	@Test
	public void testShouldGetSumOfAllFlatsofClient() {
		
		//given	
		FlatTO flatTO = new FlatTO();
		flatTO.setAddress("A2");
		flatTO.setPrice(100000);
		flatTO.setStatus(FlatStatus.FREE);
		
		FlatTO flatTO2 = new FlatTO();
		flatTO2.setAddress("A4");
		flatTO2.setPrice(200000);
		flatTO2.setStatus(FlatStatus.FREE);
		flatTO2 = flatService.addFlat(flatTO2);
		
		FlatTO flatTO3 = new FlatTO();
		flatTO3.setAddress("A5");
		flatTO3.setPrice(160000);
		flatTO3.setStatus(FlatStatus.FREE);
		flatTO3 = flatService.addFlat(flatTO3);
		
		FlatTO flatTO4= new FlatTO();
		flatTO4.setAddress("A6");
		flatTO4.setPrice(120000);
		flatTO4.setStatus(FlatStatus.FREE);
		flatTO4 = flatService.addFlat(flatTO4);
		
		FlatTO flatTO5 = new FlatTO(); 
		flatTO5.setAddress("A5");
		flatTO5.setPrice(140000);
		flatTO5.setStatus(FlatStatus.FREE);
		flatTO5 = flatService.addFlat(flatTO5);
		
		BuildingTO buildingTO = new BuildingTO();
		buildingTO.setLocalization("Poznań");
		buildingTO = buildingService.addBuilding(buildingTO);
		
		BuildingTO buildingTO2 = new BuildingTO();
		buildingTO2.setLocalization("Wrocław");
		buildingTO2 = buildingService.addBuilding(buildingTO2);
		
		ClientTO clientTO = ClientTO.builder().firstName("Jan")
				.lastName("Tomaszewski")
				.address("Poznań, ul.Garbary 3")
				.email("jt@wp.pl")
				.build();
		clientTO = clientService.addClient(clientTO);
		
		ClientTO clientTO2 = ClientTO.builder().firstName("Zbigniew")
				.lastName("Boniek")
				.address("Poznań, ul.PZPNu 1")
				.email("zb@wp.pl")
				.build();
		clientTO2 = clientService.addClient(clientTO2);
		
		flatTO = flatService.addFlat(flatTO);
		flatTO2 = flatService.addFlat(flatTO2);
		flatTO3 = flatService.addFlat(flatTO3);
		flatTO4 = flatService.addFlat(flatTO4);
		flatTO5 = flatService.addFlat(flatTO5);
		
		FlatTO reservedFlat = reservationService.reserveFlat(flatTO, clientTO);
		FlatTO reservedFlat2 = reservationService.reserveFlat(flatTO2, clientTO);
		FlatTO reservedFlat3 = reservationService.reserveFlat(flatTO3, clientTO);
		FlatTO reservedFlat4 = reservationService.reserveFlat(flatTO4, clientTO2);
		FlatTO reservedFlat5 = reservationService.reserveFlat(flatTO5, clientTO2);
		
		int sum1 = 100000 + 200000 + 160000;
		int sum2 = 120000 + 140000;
		
		//when
		flatService.addFlatToBuilding(flatTO.getId(), buildingTO.getId());
		flatService.addFlatToBuilding(flatTO2.getId(), buildingTO2.getId());
		flatService.addFlatToBuilding(flatTO3.getId(), buildingTO.getId());
		flatService.addFlatToBuilding(flatTO4.getId(), buildingTO2.getId());
		flatService.addFlatToBuilding(flatTO5.getId(), buildingTO2.getId());

		int cost1 = buildingService.sumAllFlatsOfSelectedClient(clientTO.getId());
		int cost2 = buildingService.sumAllFlatsOfSelectedClient(clientTO2.getId());

		//then
		Assert.assertEquals(sum1, cost1);
		Assert.assertEquals(sum2, cost2);
	}
	
	@Test
	public void testShouldGetMostAvelibleBuilding() {
		
		//given	
		FlatTO flatTO = new FlatTO();
		flatTO.setAddress("A2");
		flatTO.setPrice(100000);
		flatTO.setStatus(FlatStatus.RESERVED);
		
		FlatTO flatTO2 = new FlatTO();
		flatTO2.setAddress("A4");
		flatTO2.setPrice(200000);
		flatTO2.setStatus(FlatStatus.FREE);
		flatTO2 = flatService.addFlat(flatTO2);
		
		FlatTO flatTO3 = new FlatTO();
		flatTO3.setAddress("A5");
		flatTO3.setPrice(160000);
		flatTO3.setStatus(FlatStatus.SOLD);
		flatTO3 = flatService.addFlat(flatTO3);
		
		FlatTO flatTO4= new FlatTO();
		flatTO4.setAddress("A6");
		flatTO4.setPrice(120000);
		flatTO4.setStatus(FlatStatus.FREE);
		flatTO4 = flatService.addFlat(flatTO4);
		
		FlatTO flatTO5 = new FlatTO(); 
		flatTO5.setAddress("A5");
		flatTO5.setPrice(140000);
		flatTO5.setStatus(FlatStatus.FREE);
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
		
		//when
		flatService.addFlatToBuilding(flatTO.getId(), buildingTO.getId());
		flatService.addFlatToBuilding(flatTO2.getId(), buildingTO.getId());
		
		flatService.addFlatToBuilding(flatTO3.getId(), buildingTO2.getId());
		flatService.addFlatToBuilding(flatTO4.getId(), buildingTO2.getId());
		flatService.addFlatToBuilding(flatTO5.getId(), buildingTO2.getId());
		
		List<BuildingTO> buildingTOs = buildingService.mostAvaibleBuilding();

		//then
		Assert.assertEquals(1, buildingTOs.size());
	
	}
}
