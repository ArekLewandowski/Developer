package com.capgemini.service;

import java.util.List;

import javax.persistence.OptimisticLockException;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.capgemini.domain.ClientEntity;
import com.capgemini.repository.ClientRepository;
import com.capgemini.types.ClientTO;
import com.capgemini.types.FlatTO;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ClientServiceTest {

	@Autowired
	private ClientService clientService;

	@Autowired
	private ClientRepository clientRepository;

	@Autowired
	private FlatService flatService;

	@Autowired
	private ReservationService reservationService;

	@Test
	public void testShouldAddClientAndGet() {

		// given
		ClientTO clientTO = ClientTO.builder().firstName("Jan").lastName("Tomaszewski").address("Poznań, ul.Garbary 3")
				.email("jt@wp.pl").build();
		clientTO = clientService.addClient(clientTO);

		ClientTO clientTO2 = ClientTO.builder().firstName("Zbigniew").lastName("Boniek").address("Poznań, ul.PZPNu 1")
				.email("zb@wp.pl").build();
		clientTO2 = clientService.addClient(clientTO2);

		// when
		ClientTO gettedClient = clientService.getClient(clientTO.getId());
		ClientTO gettedClient2 = clientService.getClient(clientTO2.getId());

		// then
		Assert.assertEquals(clientTO.getId(), gettedClient.getId());
		Assert.assertEquals(clientTO2.getId(), gettedClient2.getId());
	}

	@Test
	public void testShouldThrowOptimisticLockException() {

		// given
		ClientTO clientTO = ClientTO.builder().firstName("Jan").lastName("Tomaszewski").address("Poznań, ul.Garbary 3")
				.email("jt@wp.pl").build();
		clientTO = clientService.addClient(clientTO);

		ClientTO clientTO2 = ClientTO.builder().firstName("Zbigniew").lastName("Boniek").address("Poznań, ul.PZPNu 1")
				.email("zb@wp.pl").build();
		clientTO2 = clientService.addClient(clientTO2);

		boolean exception = false;

		// when
		ClientTO gettedClient = clientService.getClient(clientTO.getId());
		Long version1 = gettedClient.getVersion();

		gettedClient.setLastName("Lato");
		gettedClient.setVersion(2L);
		try {
			clientService.updateClient(gettedClient);
		} catch (OptimisticLockException e) {
			exception = true;
		}

		gettedClient = clientService.getClient(gettedClient.getId());

		// then
		Assert.assertTrue(exception);

	}

	@Test
	public void testShouldFindClientWithmoreThan1Flat() {

		// given
		FlatTO flatTO = new FlatTO();
		flatTO.setAddress("A2");
		flatTO.setStatus("FREE");
		flatTO = flatService.addFlat(flatTO);

		FlatTO flatTO2 = new FlatTO();
		flatTO2.setAddress("A3");
		flatTO2.setStatus("FREE");
		flatTO2 = flatService.addFlat(flatTO2);

		FlatTO flatTO3 = new FlatTO();
		flatTO3.setAddress("A4");
		flatTO3.setStatus("FREE");
		flatTO3 = flatService.addFlat(flatTO3);

		ClientTO clientTO = ClientTO.builder().firstName("Jan").lastName("Tomaszewski").address("Poznań, ul.Garbary 3")
				.email("jt@wp.pl").build();
		clientTO = clientService.addClient(clientTO);

		ClientTO clientTO2 = ClientTO.builder().firstName("Zbigniew").lastName("Boniek").address("Poznań, ul.PZPNu 1")
				.email("zb@wp.pl").build();
		clientTO2 = clientService.addClient(clientTO2);

		// when
		reservationService.buyFlat(flatTO, clientTO);
		reservationService.buyFlat(flatTO2, clientTO);
		reservationService.buyFlat(flatTO3, clientTO2);


		List<ClientTO> clientsWithMoreFlats = clientService.findClientsWithMoreThan1Flat();
		 
		//then
		Assert.assertFalse(clientsWithMoreFlats.isEmpty());

	}

}
