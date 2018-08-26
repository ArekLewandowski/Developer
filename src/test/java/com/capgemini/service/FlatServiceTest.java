package com.capgemini.service;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.capgemini.enums.FlatStatus;
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
	public void testShouldAddAndGetFlat() {

		// given
		FlatTO flatTO = new FlatTO();
		flatTO.setAddress("A1");
		flatTO.setBalcoons(2);
		flatTO.setFloor(0);
		flatTO.setRooms(2);
		flatTO.setSize(46);
		flatTO.setStatus(FlatStatus.FREE);
		flatTO.setPrice(205000);
		flatTO = flatService.addFlat(flatTO);

		// when
		FlatTO addedflatTO = flatService.getFlatById(flatTO.getId());

		// then
		Assert.assertEquals(addedflatTO.getRooms(), flatTO.getRooms());
	}

	@Test
	public void testShouldGetFlatByFloor() {

		// given
		FlatTO flatTO = new FlatTO();
		flatTO.setAddress("A2");
		flatTO.setFloor(1);
		flatTO.setStatus(FlatStatus.FREE);
		flatTO = flatService.addFlat(flatTO);

		// when
		List<FlatTO> addedflatTOs = flatService.getFlatByFloor(1);

		// then
		Assert.assertEquals(addedflatTOs.get(0).getFloor(), flatTO.getFloor());
	}

	@Test
	public void testShouldGetFlatByStatus() {

		// given
		List<FlatTO> preAddedflatTOs = flatService.getFlatByStatus(FlatStatus.FREE);
		List<FlatTO> preAddedflatTOs2 = flatService.getFlatByStatus(FlatStatus.SOLD);

		FlatTO flatTO = new FlatTO();
		flatTO.setAddress("A2");
		flatTO.setStatus(FlatStatus.FREE);
		flatTO = flatService.addFlat(flatTO);

		// when
		List<FlatTO> addedflatTOs = flatService.getFlatByStatus(FlatStatus.FREE);
		List<FlatTO> addedflatTOs2 = flatService.getFlatByStatus(FlatStatus.SOLD);

		// then
		Assert.assertEquals(addedflatTOs.get(0).getStatus(), flatTO.getStatus());
		Assert.assertEquals(preAddedflatTOs.size() + 1, addedflatTOs.size());
		Assert.assertEquals(preAddedflatTOs2.size(), addedflatTOs2.size());
	}

	@Test
	public void testShouldAddAndGetFlatToBuilding() {

		// given
		FlatTO flatTO = new FlatTO();
		flatTO.setAddress("A3");
		flatTO.setRooms(3);
		flatTO.setStatus(FlatStatus.FREE);
		flatTO = flatService.addFlat(flatTO);

		BuildingTO buildingTO = new BuildingTO();
		buildingTO.setLocalization("Poznań");
		buildingTO = buildingService.addBuilding(buildingTO);

		// when
		flatService.addFlatToBuilding(flatTO.getId(), buildingTO.getId());
		List<FlatTO> addedflatTO = flatService.getFlatByBuilding(buildingTO);

		// then
		System.out.println(addedflatTO.toString());
		Assert.assertEquals(addedflatTO.get(0).getRooms(), flatTO.getRooms());
	}

	@Test
	public void testShouldGetFlatByBuilding() {
		//
		// List<FlatTO> preAddedflatTO =
		// flatService.getFlatByBuilding(buildingTO);
		// List<FlatTO> preAddedflatTO2 =
		// flatService.getFlatByBuilding(buildingTO2);

		// given
		FlatTO flatTO = new FlatTO();
		flatTO.setAddress("A2");
		flatTO.setStatus(FlatStatus.FREE);

		FlatTO flatTO2 = new FlatTO();
		flatTO2.setAddress("A4");
		flatTO2.setStatus(FlatStatus.FREE);
		flatTO2 = flatService.addFlat(flatTO2);

		FlatTO flatTO3 = new FlatTO();
		flatTO3.setAddress("A5");
		flatTO3.setStatus(FlatStatus.FREE);
		flatTO3 = flatService.addFlat(flatTO3);

		BuildingTO buildingTO = new BuildingTO();
		buildingTO.setLocalization("Poznań");
		buildingTO = buildingService.addBuilding(buildingTO);

		BuildingTO buildingTO2 = new BuildingTO();
		buildingTO2.setLocalization("Poznań");
		buildingTO2 = buildingService.addBuilding(buildingTO2);

		flatTO = flatService.addFlat(flatTO);
		flatTO2 = flatService.addFlat(flatTO2);
		flatTO3 = flatService.addFlat(flatTO3);

		// when
		flatService.addFlatToBuilding(flatTO.getId(), buildingTO.getId());
		flatService.addFlatToBuilding(flatTO2.getId(), buildingTO2.getId());
		flatService.addFlatToBuilding(flatTO3.getId(), buildingTO.getId());
		List<FlatTO> addedflatTO = flatService.getFlatByBuilding(buildingTO);
		List<FlatTO> addedflatTO2 = flatService.getFlatByBuilding(buildingTO2);

		// then
		Assert.assertEquals(2, addedflatTO.size());
		Assert.assertEquals(1, addedflatTO2.size());
	}

	@Test
	public void testShouldGetFlatRooms() {

		List<FlatTO> preaddedflatTO = flatService.getFlatByRoomsFromTO(2, 3);

		// given
		FlatTO flatTO = new FlatTO();
		flatTO.setAddress("A2");
		flatTO.setRooms(3);
		flatTO.setStatus(FlatStatus.FREE);

		FlatTO flatTO2 = new FlatTO();
		flatTO2.setAddress("A4");
		flatTO2.setRooms(1);
		flatTO2.setStatus(FlatStatus.FREE);
		flatTO2 = flatService.addFlat(flatTO2);

		FlatTO flatTO3 = new FlatTO();
		flatTO3.setAddress("A5");
		flatTO3.setRooms(4);
		flatTO3.setStatus(FlatStatus.FREE);
		flatTO3 = flatService.addFlat(flatTO3);

		FlatTO flatTO4 = new FlatTO();
		flatTO4.setAddress("A6");
		flatTO4.setRooms(2);
		flatTO4.setStatus(FlatStatus.FREE);
		flatTO4 = flatService.addFlat(flatTO4);

		BuildingTO buildingTO = new BuildingTO();
		buildingTO.setFlatsSum(0);
		buildingTO.setLocalization("Poznań");
		buildingTO = buildingService.addBuilding(buildingTO);

		BuildingTO buildingTO2 = new BuildingTO();
		buildingTO2.setFlatsSum(0);
		buildingTO2.setLocalization("Poznań");
		buildingTO2 = buildingService.addBuilding(buildingTO2);

		flatTO = flatService.addFlat(flatTO);
		flatTO2 = flatService.addFlat(flatTO2);
		flatTO3 = flatService.addFlat(flatTO3);
		flatTO4 = flatService.addFlat(flatTO4);

		// when
		flatService.addFlatToBuilding(flatTO.getId(), buildingTO.getId());
		flatService.addFlatToBuilding(flatTO2.getId(), buildingTO2.getId());
		flatService.addFlatToBuilding(flatTO3.getId(), buildingTO.getId());
		flatService.addFlatToBuilding(flatTO4.getId(), buildingTO.getId());
		List<FlatTO> addedflatTO = flatService.getFlatByRoomsFromTO(2, 3);

		// then
		for (FlatTO flatTO5 : addedflatTO) {
			Assert.assertTrue(flatTO5.getRooms() == 2 || flatTO5.getRooms() == 3);
		}
	}

	@Test
	public void testShouldGetFlatBySize() {

		List<FlatTO> preAddedflatTO2 = flatService.getFlatBySizeFromTO(40, 60);

		// given
		FlatTO flatTO = new FlatTO();
		flatTO.setAddress("A2");
		flatTO.setSize(59);
		flatTO.setStatus(FlatStatus.FREE);

		FlatTO flatTO2 = new FlatTO();
		flatTO2.setAddress("A4");
		flatTO2.setSize(22);
		flatTO2.setStatus(FlatStatus.FREE);
		flatTO2 = flatService.addFlat(flatTO2);

		FlatTO flatTO3 = new FlatTO();
		flatTO3.setAddress("A5");
		flatTO3.setSize(55);
		flatTO3.setStatus(FlatStatus.FREE);
		flatTO3 = flatService.addFlat(flatTO3);

		FlatTO flatTO4 = new FlatTO();
		flatTO4.setAddress("A6");
		flatTO4.setSize(45);
		flatTO4.setStatus(FlatStatus.FREE);
		flatTO4 = flatService.addFlat(flatTO4);

		BuildingTO buildingTO = new BuildingTO();
		buildingTO.setFlatsSum(0);
		buildingTO.setLocalization("Poznań");
		buildingTO = buildingService.addBuilding(buildingTO);

		BuildingTO buildingTO2 = new BuildingTO();
		buildingTO2.setFlatsSum(0);
		buildingTO2.setLocalization("Poznań");
		buildingTO2 = buildingService.addBuilding(buildingTO2);

		flatTO = flatService.addFlat(flatTO);
		flatTO2 = flatService.addFlat(flatTO2);
		flatTO3 = flatService.addFlat(flatTO3);
		flatTO4 = flatService.addFlat(flatTO4);

		// when
		flatService.addFlatToBuilding(flatTO.getId(), buildingTO.getId());
		flatService.addFlatToBuilding(flatTO2.getId(), buildingTO2.getId());
		flatService.addFlatToBuilding(flatTO3.getId(), buildingTO.getId());
		flatService.addFlatToBuilding(flatTO4.getId(), buildingTO.getId());
		List<FlatTO> addedflatTO2 = flatService.getFlatBySizeFromTO(40, 50);

		// then
		System.out.println("Test4: Dodane mieszkania. Oczekiwwane: " + addedflatTO2.size());
		System.out.println(addedflatTO2.toString());
		for (FlatTO flatTO5 : addedflatTO2) {
			Assert.assertTrue(flatTO5.getSize() > 40 || flatTO5.getSize() < 50);
		}
	}

	@Test
	public void testShouldGetAndIncrementFlatsSum() {

		List<FlatTO> preaddedflatTO = flatService.getFlatByRoomsFromTO(2, 3);
		List<FlatTO> preAddedflatTO2 = flatService.getFlatBySizeFromTO(40, 60);

		// given
		FlatTO flatTO = new FlatTO();
		flatTO.setAddress("A2");
		flatTO.setStatus(FlatStatus.FREE);
		flatTO = flatService.addFlat(flatTO);

		FlatTO flatTO2 = new FlatTO();
		flatTO2.setAddress("A4");
		flatTO2.setStatus(FlatStatus.FREE);
		flatTO2 = flatService.addFlat(flatTO2);

		FlatTO flatTO3 = new FlatTO();
		flatTO3.setAddress("A5");
		flatTO3.setStatus(FlatStatus.FREE);
		flatTO3 = flatService.addFlat(flatTO3);

		FlatTO flatTO4 = new FlatTO();
		flatTO4.setAddress("A6");
		flatTO4.setStatus(FlatStatus.FREE);
		flatTO4 = flatService.addFlat(flatTO4);

		BuildingTO buildingTO = new BuildingTO();
		buildingTO.setFlatsSum(0);
		buildingTO.setLocalization("Poznań");
		buildingTO = buildingService.addBuilding(buildingTO);

		BuildingTO buildingTO2 = new BuildingTO();
		buildingTO2.setFlatsSum(0);
		buildingTO2.setLocalization("Poznań");
		buildingTO2 = buildingService.addBuilding(buildingTO2);

		flatTO = flatService.addFlat(flatTO);
		flatTO2 = flatService.addFlat(flatTO2);
		flatTO3 = flatService.addFlat(flatTO3);
		flatTO4 = flatService.addFlat(flatTO4);

		// when
		flatService.addFlatToBuilding(flatTO.getId(), buildingTO.getId());
		flatService.addFlatToBuilding(flatTO2.getId(), buildingTO2.getId());
		flatService.addFlatToBuilding(flatTO3.getId(), buildingTO.getId());
		flatService.addFlatToBuilding(flatTO4.getId(), buildingTO.getId());

		int flats = buildingService.getBuildingById(buildingTO.getId()).getFlatsSum();
		int flats2 = buildingService.getBuildingById(buildingTO2.getId()).getFlatsSum();

		// then
		Assert.assertEquals(3, flats);
		Assert.assertEquals(1, flats2);
	}

	@Test
	public void testShouldRemoveAndDecrementFlatsSum() {

		// given
		FlatTO flatTO = new FlatTO();
		flatTO.setAddress("A2");
		flatTO.setStatus(FlatStatus.FREE);

		FlatTO flatTO2 = new FlatTO();
		flatTO2.setAddress("A4");
		flatTO2.setStatus(FlatStatus.FREE);

		FlatTO flatTO3 = new FlatTO();
		flatTO3.setAddress("A5");
		flatTO3.setStatus(FlatStatus.FREE);

		FlatTO flatTO4 = new FlatTO();
		flatTO4.setAddress("A6");
		flatTO4.setStatus(FlatStatus.FREE);

		BuildingTO buildingTO = new BuildingTO();
		buildingTO.setFlatsSum(0);
		buildingTO.setLocalization("Poznań");
		buildingTO = buildingService.addBuilding(buildingTO);

		BuildingTO buildingTO2 = new BuildingTO();
		buildingTO2.setFlatsSum(0);
		buildingTO2.setLocalization("Poznań");
		buildingTO2 = buildingService.addBuilding(buildingTO2);

		flatTO = flatService.addFlat(flatTO);
		flatTO2 = flatService.addFlat(flatTO2);
		flatTO3 = flatService.addFlat(flatTO3);
		flatTO4 = flatService.addFlat(flatTO4);

		// when
		// we add three flats to the database
		flatService.addFlatToBuilding(flatTO.getId(), buildingTO.getId());
		flatService.addFlatToBuilding(flatTO3.getId(), buildingTO.getId());
		flatService.addFlatToBuilding(flatTO4.getId(), buildingTO.getId());

		// we add one flat to another building
		flatService.addFlatToBuilding(flatTO2.getId(), buildingTO2.getId());

		// then
		// we remove one flat from first building
		flatService.removeFlatFromBuilding(flatTO4.getId());

		int flats = buildingService.getBuildingById(buildingTO.getId()).getFlatsSum();
		int flats2 = buildingService.getBuildingById(buildingTO2.getId()).getFlatsSum();

		Assert.assertEquals(2, flats);
		Assert.assertEquals(1, flats2);
	}

	@Test
	public void testShouldShowDisabledApprFlats() {

		List<FlatTO> flats = flatService.disabledAppropriateFlats();
		System.out.println(flats.size());
		// given
		FlatTO flatTO = new FlatTO();
		flatTO.setAddress("A2");
		flatTO.setFloor(0);
		flatTO.setStatus(FlatStatus.FREE);

		FlatTO flatTO2 = new FlatTO();
		flatTO2.setAddress("A4");
		flatTO2.setFloor(1);
		flatTO2.setStatus(FlatStatus.FREE);

		FlatTO flatTO3 = new FlatTO();
		flatTO3.setAddress("A5");
		flatTO3.setFloor(1);
		flatTO3.setStatus(FlatStatus.FREE);

		FlatTO flatTO4 = new FlatTO();
		flatTO4.setAddress("A6");
		flatTO4.setFloor(0);
		flatTO4.setStatus(FlatStatus.FREE);

		BuildingTO buildingTO = new BuildingTO();
		buildingTO.setFlatsSum(0);
		buildingTO.setElevator(true);
		buildingTO.setLocalization("Poznań");
		buildingTO = buildingService.addBuilding(buildingTO);

		BuildingTO buildingTO2 = new BuildingTO();
		buildingTO2.setFlatsSum(0);
		buildingTO2.setElevator(false);
		buildingTO2.setLocalization("Poznań");
		buildingTO2 = buildingService.addBuilding(buildingTO2);

		flatTO = flatService.addFlat(flatTO);
		flatTO2 = flatService.addFlat(flatTO2);
		flatTO3 = flatService.addFlat(flatTO3);
		flatTO4 = flatService.addFlat(flatTO4);

		// when
		// we add two flats to the database
		flatService.addFlatToBuilding(flatTO.getId(), buildingTO.getId());
		flatService.addFlatToBuilding(flatTO2.getId(), buildingTO.getId());

		// we add two flat to another building
		flatService.addFlatToBuilding(flatTO3.getId(), buildingTO2.getId());
		flatService.addFlatToBuilding(flatTO4.getId(), buildingTO2.getId());

		List<FlatTO> flats1 = flatService.disabledAppropriateFlats();
		System.out.println(flats1.size());

		Assert.assertEquals(flats.size() + 3, flats1.size());
	}

	@Test
	public void testShouldGetFlatByBuildingAndStatus() {

		// given
		FlatTO flatTO = new FlatTO();
		flatTO.setAddress("A2");
		flatTO.setPrice(100000);
		flatTO.setStatus(FlatStatus.FREE);
		flatTO = flatService.addFlat(flatTO);

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

		FlatTO flatTO4 = new FlatTO();
		flatTO4.setAddress("A6");
		flatTO4.setPrice(120000);
		flatTO4.setStatus(FlatStatus.FREE);
		flatTO4 = flatService.addFlat(flatTO4);

		FlatTO flatTO5 = new FlatTO();
		flatTO5.setAddress("A5");
		flatTO5.setPrice(140000);
		flatTO5.setStatus(FlatStatus.RESERVED);
		flatTO5 = flatService.addFlat(flatTO5);

		BuildingTO buildingTO = new BuildingTO();
		buildingTO.setLocalization("Poznań");
		buildingTO = buildingService.addBuilding(buildingTO);

		BuildingTO buildingTO2 = new BuildingTO();
		buildingTO2.setLocalization("Wrocław");
		buildingTO2 = buildingService.addBuilding(buildingTO2);

		List<FlatTO> flatsPre1 = flatService.findFlatByStatusAndBuilding(FlatStatus.FREE, buildingTO.getId());
		List<FlatTO> flatsPre2 = flatService.findFlatByStatusAndBuilding(FlatStatus.FREE, buildingTO2.getId());

		flatTO = flatService.addFlat(flatTO);
		flatTO2 = flatService.addFlat(flatTO2);
		flatTO3 = flatService.addFlat(flatTO3);
		flatTO4 = flatService.addFlat(flatTO4);
		flatTO5 = flatService.addFlat(flatTO5);

		// when
		flatService.addFlatToBuilding(flatTO.getId(), buildingTO.getId());
		flatService.addFlatToBuilding(flatTO2.getId(), buildingTO2.getId());
		flatService.addFlatToBuilding(flatTO3.getId(), buildingTO.getId());
		flatService.addFlatToBuilding(flatTO4.getId(), buildingTO2.getId());
		flatService.addFlatToBuilding(flatTO5.getId(), buildingTO2.getId());

		// when

		List<FlatTO> flats1 = flatService.findFlatByStatusAndBuilding(FlatStatus.FREE, buildingTO.getId());
		List<FlatTO> flats2 = flatService.findFlatByStatusAndBuilding(FlatStatus.FREE, buildingTO2.getId());

		// then
		Assert.assertEquals(flats1.size()+2, flats1.size());
		Assert.assertEquals(flats2.size()+2, flats2.size());
	}
}
