package com.capgemini.service;

import javax.transaction.Transactional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.capgemini.types.ClientTO;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ClientServiceTest {
	
	@Autowired
	private ClientService clientService;
	
	@Test
	public void testShouldAddClientAndGet(){
	
		//given
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
		ClientTO gettedClient = clientService.getClient(clientTO.getId());
		ClientTO gettedClient2 = clientService.getClient(clientTO2.getId());
		
		//then
		Assert.assertEquals(clientTO.getId(), gettedClient.getId());
		Assert.assertEquals(clientTO2.getId(), gettedClient2.getId());
	}

}
