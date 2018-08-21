package com.capgemini.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.dao.ClientDAO;
import com.capgemini.domain.ClientEntity;
import com.capgemini.mappers.ClientMapper;
import com.capgemini.service.ClientService;
import com.capgemini.types.ClientTO;

@Service
@Transactional
public class ClientServiceImpl implements ClientService {

	@Autowired
	ClientDAO clientDAO;

	@Override
	public ClientTO addClient(ClientTO clientTO) {
		ClientEntity clientEntity = ClientMapper.map2Entity(clientTO);
		clientEntity = clientDAO.save(clientEntity);
		return ClientMapper.map2TO(clientEntity);
	}

	@Override
	public ClientTO getClient(Long id) {
		ClientEntity clientEntity = clientDAO.getOne(id);
		return ClientMapper.map2TO(clientEntity);
	}

}
