package com.capgemini.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.dao.ClientRepository;
import com.capgemini.dao.FlatRepository;
import com.capgemini.domain.ClientEntity;
import com.capgemini.domain.FlatEntity;
import com.capgemini.enums.FLAT_STATUS;
import com.capgemini.service.ReservationService;
import com.capgemini.types.ClientTO;
import com.capgemini.types.FlatTO;

@Service
public class ReservationServiceImpl implements ReservationService{
	
	@Autowired
	FlatRepository flatRepository;
	
	@Autowired
	ClientRepository clientRepository;

	@Override
	public FlatTO reserveFlat(FlatTO flatTO, ClientTO clientTO) {
		FlatEntity flatEntity = flatRepository.getOne(flatTO.getId());
		ClientEntity clientEntity = clientRepository.findOne(clientTO.getId());
		flatEntity.setStatus(FLAT_STATUS.RESERVED);
		flatEntity.setOwner(clientEntity);
		clientEntity.addOwned(flatEntity);
		return flatTO;
	}
	
	@Override
	public FlatTO reserveFlatWithCoowner(FlatTO flatTO, ClientTO clientTO, List<ClientTO> coowners) {
		FlatEntity flatEntity = flatRepository.getOne(flatTO.getId());
		ClientEntity clientEntity = clientRepository.findOne(clientTO.getId());
		flatEntity.setStatus(FLAT_STATUS.RESERVED);
		return null;
	}

	@Override
	public FlatTO buyFlat(FlatTO flatTO, ClientTO clientTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FlatTO cancelReservation(FlatTO flatTO, ClientTO clientTO) {
		// TODO Auto-generated method stub
		return null;
	}

}
