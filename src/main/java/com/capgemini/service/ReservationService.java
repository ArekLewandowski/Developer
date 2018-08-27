package com.capgemini.service;

import java.util.List;

import com.capgemini.types.ClientTO;
import com.capgemini.types.FlatTO;

public interface ReservationService {

	String getFlatStatus(Long flatId);

	ClientTO getFlatOwner(Long flatId);

	List<ClientTO> getFlatCoowner(Long flatId);

	FlatTO reserveFlat(FlatTO flatTO, ClientTO clientTO);

	FlatTO buyFlat(FlatTO flatTO, ClientTO clientTO);

	FlatTO cancelReservation(FlatTO flatTO);

	FlatTO reserveFlatWithCoowner(FlatTO flatTO, ClientTO clientTO, List<ClientTO> coowners);

	FlatTO buyFlatWithCoowners(FlatTO flatTO, ClientTO clientTO, List<ClientTO> coowners);
}
