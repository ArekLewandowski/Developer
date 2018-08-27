package com.capgemini.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.domain.ClientEntity;
import com.capgemini.domain.FlatEntity;
import com.capgemini.exceptions.FlatNotAvaibleExcepion;
import com.capgemini.exceptions.OverReservationLimitException;
import com.capgemini.mappers.ClientMapper;
import com.capgemini.mappers.FlatMapper;
import com.capgemini.repository.ClientRepository;
import com.capgemini.repository.FlatRepository;
import com.capgemini.service.ReservationService;
import com.capgemini.types.ClientTO;
import com.capgemini.types.FlatTO;

@Service
@Transactional
public class ReservationServiceImpl implements ReservationService {

	private static final int RESERVATION_LIMIT = 3;

	@Autowired
	private FlatRepository flatRepository;

	@Autowired
	private ClientRepository clientRepository;

	@Override
	public FlatTO reserveFlat(FlatTO flatTO, ClientTO clientTO) {
		flatTO = reserveFlatWithCoowner(flatTO, clientTO, new ArrayList<>());
		return flatTO;
	}

	@Override
	public FlatTO reserveFlatWithCoowner(FlatTO flatTO, ClientTO clientTO, List<ClientTO> coowners) {
		FlatEntity flatEntity = flatRepository.getOne(flatTO.getId());
		ClientEntity clientEntity = clientRepository.findOne(clientTO.getId());
		if (!flatEntity.getStatus().equals("FREE")) {
			throw new FlatNotAvaibleExcepion();
		}
		int reservedFlats = 0;

		if (!clientEntity.getOwned().isEmpty()) {
			List<FlatEntity> ownedFlats = clientEntity.getOwned();
			for (FlatEntity flatEntityOwn : ownedFlats) {
				if (flatEntityOwn.getStatus().equals("RESERVED")) {
					reservedFlats++;
				}
			}
		}
		if (reservedFlats >= RESERVATION_LIMIT) {
			throw new OverReservationLimitException();
		}
		flatEntity.setStatus("RESERVED");
		flatEntity.setOwner(clientEntity);
		clientEntity.addOwned(flatEntity);

		if (coowners.size() != 0 || coowners != null) {
			for (ClientTO clientTO2 : coowners) {
				ClientEntity coownerEntity = clientRepository.findOne(clientTO2.getId());
				flatEntity.addCoowner(coownerEntity);
				coownerEntity.addCoowned(flatEntity);
			}			
		}
		flatTO = FlatMapper.map2TO(flatEntity);

		return flatTO;
	}

	@Override
	public FlatTO buyFlat(FlatTO flatTO, ClientTO clientTO) {
		flatTO = buyFlatWithCoowners(flatTO, clientTO, new ArrayList<>());
		return flatTO;
	}

	@Override
	public FlatTO buyFlatWithCoowners(FlatTO flatTO, ClientTO clientTO, List<ClientTO> coowners) {
		FlatEntity flatEntity = flatRepository.getOne(flatTO.getId());
		ClientEntity clientEntity = clientRepository.findOne(clientTO.getId());
		if (flatEntity.getStatus().equals("SOLD")) {
			throw new FlatNotAvaibleExcepion();
		} else if (flatEntity.getStatus().equals("RESERVED")
				&& !(flatEntity.getOwner().getId().equals(clientEntity.getId()))) {
			throw new FlatNotAvaibleExcepion();
		} else {
			flatEntity.setStatus("SOLD");
			flatEntity.setOwner(clientEntity);
			clientEntity.addOwned(flatEntity);
		}
		for (ClientTO clientTO2 : coowners) {
			ClientEntity coownerEntity = clientRepository.findOne(clientTO2.getId());
			flatEntity.addCoowner(coownerEntity);
			coownerEntity.addCoowned(flatEntity);
		}
		flatTO = FlatMapper.map2TO(flatEntity);

		return flatTO;
	}

	@Override
	public FlatTO cancelReservation(FlatTO flatTO) {
		FlatEntity flatEntity = flatRepository.findOne(flatTO.getId());
		ClientEntity ownerEntity = clientRepository.findOne(flatEntity.getOwner().getId());
		if (flatEntity.getCoowner().equals(0) || flatEntity.getCoowner() == null) {
			List<ClientEntity> coowners = flatEntity.getCoowner();
			for (ClientEntity clientEntity : coowners) {
				clientEntity.getCoowned().remove(flatEntity);
			}
			flatEntity.getCoowner().clear();
		}
		ownerEntity.getOwned().remove(flatEntity);
		flatEntity.setOwner(null);
		flatEntity.setStatus("FREE");
		return FlatMapper.map2TO(flatEntity);
	}

	@Override
	public String getFlatStatus(Long flatId) {
		String status = flatRepository.findOne(flatId).getStatus();
		return status;
	}

	@Override
	public ClientTO getFlatOwner(Long flatId) {
		ClientEntity clientEntity = flatRepository.findOne(flatId).getOwner();
		ClientTO clientTO = ClientMapper.map2TO(clientEntity);
		return clientTO;
	}

	@Override
	public List<ClientTO> getFlatCoowner(Long flatId) {
		List<ClientEntity> coownerEntities = flatRepository.findOne(flatId).getCoowner();
		List<ClientTO> coownerTOs = ClientMapper.map2TOs(coownerEntities);
		return coownerTOs;
	}

}
