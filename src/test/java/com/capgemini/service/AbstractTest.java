package com.capgemini.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.capgemini.types.FlatTO;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AbstractTest {

	@Autowired
	private FlatService flatService;

	@Test
	public void testShouldIncrementVersion() {

		// given
		FlatTO flatTO = new FlatTO();
		flatTO.setAddress("A1");
		flatTO.setBalcoons(2);
		flatTO.setFloor(0);
		flatTO.setRooms(2);
		flatTO.setSize(46);
		flatTO.setStatus("FREE");
		flatTO.setPrice(205000);
		flatTO = flatService.addFlat(flatTO);

		long version1 = flatTO.getVersion();

		// when
		FlatTO gettedFlat = flatService.getFlatById(flatTO.getId());
		gettedFlat.setBalcoons(5);
		flatService.updateFlat(gettedFlat);
		FlatTO updatedFlat = flatService.getFlatById(flatTO.getId());

		long version2 = updatedFlat.getVersion();

		// then
		Assert.assertEquals(0, version1);
		Assert.assertEquals(1, version2);
	}

}
