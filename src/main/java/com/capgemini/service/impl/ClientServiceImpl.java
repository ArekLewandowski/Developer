package com.capgemini.service.impl;

import java.util.List;

import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.domain.ClientEntity;
import com.capgemini.mappers.ClientMapper;
import com.capgemini.repository.ClientRepository;
import com.capgemini.service.ClientService;
import com.capgemini.types.ClientTO;

@Service
@Transactional
public class ClientServiceImpl implements ClientService {

	@Autowired
	private ClientRepository clientRepository;

	@Override
	public ClientTO addClient(ClientTO clientTO) {
		ClientEntity clientEntity = ClientMapper.map2Entity(clientTO);
		clientEntity = clientRepository.save(clientEntity);
		return ClientMapper.map2TO(clientEntity);
	}

	@Override
	public ClientTO updateClient(ClientTO clientTO) {
		ClientEntity currentEntity = clientRepository.findOne(clientTO.getId());
		if (clientTO.getVersion() != currentEntity.getVersion()) {
			throw new OptimisticLockException();
		}
		ClientEntity clientEntity = ClientMapper.map2Entity(clientTO, currentEntity);
		clientEntity = clientRepository.save(clientEntity);
		return ClientMapper.map2TO(clientEntity);
	}

	@Override
	public ClientTO getClient(Long id) {
		ClientEntity clientEntity = clientRepository.findOne(id);
		return ClientMapper.map2TO(clientEntity);
	}

	@Override
	public List<ClientTO> findClientsWithMoreThan1Flat() {
		List<ClientEntity> clientEntities = clientRepository.findClientsWithMoreThan1Flat();
		List<ClientTO> clientTOs = ClientMapper.map2TOs(clientEntities);
		return clientTOs;
	}

}
