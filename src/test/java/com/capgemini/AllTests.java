package com.capgemini;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.capgemini.service.BuildingServiceTest;
import com.capgemini.service.ClientServiceTest;
import com.capgemini.service.DAOTests;
import com.capgemini.service.FlatServiceTest;
import com.capgemini.service.ReservationServiceTest;
import com.capgemini.service.ServiceTestSuite;

@RunWith(Suite.class)
@SuiteClasses({	BuildingServiceTest.class, 
	ClientServiceTest.class, 
	DAOTests.class, 
	FlatServiceTest.class, 
	ReservationServiceTest.class})
public class AllTests {

}
