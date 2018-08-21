package com.capgemini.service;

import com.capgemini.types.ClientTO;

public interface ClientService {

	ClientTO addClient(ClientTO clientTO);

	ClientTO getClient(Long id);
}
