package com.capgemini.service;

import java.util.List;

import com.capgemini.types.ClientTO;

public interface ClientService {

	ClientTO addClient(ClientTO clientTO);

	ClientTO getClient(Long id);

	List<ClientTO> findClientsWithMoreThan1Flat();

	ClientTO updateClient(ClientTO clientTO);
}
