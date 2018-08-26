package com.capgemini.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.capgemini.enums.FlatStatus;
import com.capgemini.exceptions.FlatNotAvaibleExcepion;
import com.capgemini.exceptions.OverReservationLimitException;
import com.capgemini.types.ClientTO;
import com.capgemini.types.FlatTO;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReservationServiceTest {

	@Autowired
	private FlatService flatService;
	
	@Autowired
	private ClientService clientService;
	
	@Autowired
	private BuildingService buildingService;
	
	@Autowired
	private ReservationService reservationService;
	
	@Test
	public void testShouldAddReservation(){
		
		//given
		FlatTO flatTO = new FlatTO();
		flatTO.setAddress("A2");
		flatTO.setStatus(FlatStatus.FREE);
		flatTO = flatService.addFlat(flatTO);
	
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
	
		//when
		FlatTO reservedFlat = reservationService.reserveFlat(flatTO, clientTO2);
		
		FlatTO afrerTestFlatTO = flatService.getFlatById(reservedFlat.getId());
		ClientTO afterTestclientTO = clientService.getClient(clientTO.getId());
		
		//then
		Assert.assertEquals(FlatStatus.FREE, flatTO.getStatus());
		Assert.assertEquals(FlatStatus.RESERVED, reservedFlat.getStatus());

	}
	
	@Test
	public void testShouldAddCancelReservation(){
		
		//given
		FlatTO flatTO = new FlatTO();
		flatTO.setAddress("A2");
		flatTO.setStatus(FlatStatus.FREE);
		flatTO = flatService.addFlat(flatTO);
	
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
	
		FlatTO reservedFlat = reservationService.reserveFlat(flatTO, clientTO2);
		
		FlatTO afrerTestFlatTO = flatService.getFlatById(reservedFlat.getId());
		ClientTO afterTestclientTO = clientService.getClient(clientTO.getId());
		//when
		reservationService.cancelReservation(reservedFlat);
		FlatTO canceledReservationFlat = flatService.getFlatById(reservedFlat.getId());
				
		//then
		Assert.assertEquals(FlatStatus.FREE, flatTO.getStatus());
		Assert.assertEquals(FlatStatus.RESERVED, reservedFlat.getStatus());
		Assert.assertEquals(FlatStatus.FREE, canceledReservationFlat.getStatus());

	}
	
	@Test
	public void testShouldAddReservationWithCoowners(){
		
		//given
		FlatTO flatTO = new FlatTO();
		flatTO.setAddress("A2");
		flatTO.setStatus(FlatStatus.FREE);
		flatTO = flatService.addFlat(flatTO);
	
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
		
		List<ClientTO> coowners = new ArrayList<>();
		coowners.add(clientTO2);
	
		//when
		FlatTO reservedFlat = reservationService.reserveFlatWithCoowner(flatTO, clientTO, coowners);
		FlatTO afterTestFlatTO = flatService.getFlatById(reservedFlat.getId());
		ClientTO afterTestclientTO = clientService.getClient(clientTO.getId());
		ClientTO afterTestclientTO2 = clientService.getClient(clientTO2.getId());
		
		List<Long> reservedFlatId = afterTestclientTO.getOwnedId();
		Long addedOwnerId = afterTestFlatTO.getOwnedClientId();
		List<Long> addedCoownerId = afterTestFlatTO.getCoownedClientId();

		//then
		Assert.assertEquals(FlatStatus.FREE, flatTO.getStatus());
		Assert.assertEquals(FlatStatus.RESERVED, reservedFlat.getStatus());
		Assert.assertEquals(clientTO.getId(), addedOwnerId);
		Assert.assertTrue(addedCoownerId.contains(clientTO2.getId()));
		Assert.assertTrue(reservedFlatId.contains(reservedFlat.getId()));
	}
	
	@Test
	public void testShouldBuyFlatWithFreeStatus(){
		
		//given
		FlatTO flatTO = new FlatTO();
		flatTO.setAddress("A2");
		flatTO.setStatus(FlatStatus.FREE);
		flatTO = flatService.addFlat(flatTO);
	
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
	
		//when
		FlatTO soldFlat = reservationService.buyFlat(flatTO, clientTO);
		FlatTO afterTestFlatTO = flatService.getFlatById(soldFlat.getId());
		
		ClientTO afterTestclientTO = clientService.getClient(clientTO.getId());
		ClientTO afterTestclientTO2 = clientService.getClient(clientTO2.getId());
		
		List<Long> reservedFlatId = afterTestclientTO.getOwnedId();
		Long addedOwnerId = afterTestFlatTO.getOwnedClientId();

		//then
		Assert.assertEquals(FlatStatus.FREE, flatTO.getStatus());
		Assert.assertEquals(FlatStatus.SOLD, soldFlat.getStatus());
		Assert.assertEquals(clientTO.getId(), addedOwnerId);
		Assert.assertTrue(reservedFlatId.contains(soldFlat.getId()));
	}
	
	@Test
	public void testShouldBuyFlatWithReservedStatus(){
		
		//given
		FlatTO flatTO = new FlatTO();
		flatTO.setAddress("A2");
		flatTO.setStatus(FlatStatus.FREE);
		flatTO = flatService.addFlat(flatTO);
	
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
	
		//when
		FlatTO reservedFlat = reservationService.reserveFlat(flatTO, clientTO);
		FlatTO soldFlat = reservationService.buyFlat(flatTO, clientTO);
		FlatTO afterTestFlatTO = flatService.getFlatById(soldFlat.getId());
		
		ClientTO afterTestclientTO = clientService.getClient(clientTO.getId());
		ClientTO afterTestclientTO2 = clientService.getClient(clientTO2.getId());
		
		List<Long> reservedFlatId = afterTestclientTO.getOwnedId();
		Long addedOwnerId = afterTestFlatTO.getOwnedClientId();

		//then
		Assert.assertEquals(FlatStatus.FREE, flatTO.getStatus());
		Assert.assertEquals(FlatStatus.SOLD, soldFlat.getStatus());
		Assert.assertEquals(clientTO.getId(), addedOwnerId);
		Assert.assertTrue(reservedFlatId.contains(soldFlat.getId()));
	}
	
	@Test
	public void testShouldNotBuyFlatWithSoldStatus(){
		
		//given
		FlatTO flatTO = new FlatTO();
		flatTO.setAddress("A2");
		flatTO.setStatus(FlatStatus.FREE);
		flatTO = flatService.addFlat(flatTO);
	
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
	
		//when
		FlatTO reservedFlat = reservationService.reserveFlat(flatTO, clientTO);
		FlatTO soldFlat = reservationService.buyFlat(flatTO, clientTO);
		
		boolean excep = false;
		try {
			soldFlat = reservationService.buyFlat(flatTO, clientTO2);			
		} catch (FlatNotAvaibleExcepion e) {
			excep = true;
		}

		//then
		Assert.assertTrue(excep);

	}
	
	@Test
	public void testShouldNotBuyFlatWithReservedStatusWithOtherClient(){
		
		//given
		FlatTO flatTO = new FlatTO();
		flatTO.setAddress("A2");
		flatTO.setStatus(FlatStatus.FREE);
		flatTO = flatService.addFlat(flatTO);
	
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
	
		//when
		FlatTO reservedFlat = reservationService.reserveFlat(flatTO, clientTO);
		
		boolean excep = false;
		try {
			FlatTO soldFlat = reservationService.buyFlat(flatTO, clientTO2);			
		} catch (FlatNotAvaibleExcepion e) {
			excep = true;
		}

		//then
		Assert.assertTrue(excep);

	}
	
	@Test
	public void testShouldNotReserveFlatOverLimit(){
		
		//given
		FlatTO flatTO = new FlatTO();
		flatTO.setAddress("A2");
		flatTO.setStatus(FlatStatus.FREE);
		flatTO = flatService.addFlat(flatTO);
		
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
	
		//when
		FlatTO reservedFlat = reservationService.reserveFlat(flatTO, clientTO);
		FlatTO reservedFlat2 = reservationService.reserveFlat(flatTO2, clientTO);
		FlatTO reservedFlat3 = reservationService.reserveFlat(flatTO3, clientTO);
		
		boolean excep = false;
		try {
			FlatTO reservedFlat4 = reservationService.reserveFlat(flatTO4, clientTO);			
		} catch (OverReservationLimitException e) {
			excep = true;
		}

		//then
		Assert.assertTrue(excep);

	}
}
