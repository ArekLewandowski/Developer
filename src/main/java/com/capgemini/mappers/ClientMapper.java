package com.capgemini.mappers;

import java.util.LinkedList;
import java.util.List;

import com.capgemini.domain.ClientEntity;
import com.capgemini.types.ClientTO;

public class ClientMapper {

	public static ClientTO map2TO(ClientEntity clientEntity) {
		return ClientTO.builder().id(clientEntity.getId()).firstName(clientEntity.getFirstName())
				.lastName(clientEntity.getLastName())
				.addres(clientEntity.getAddres()).phone(clientEntity.getPhone()).email(clientEntity.getEmail())
				.build();
	}

	public static ClientEntity map2Entity(ClientTO clientTO) {
		return ClientEntity.builder().id(clientTO.getId()).firstName(clientTO.getFirstName())
				.lastName(clientTO.getLastName()).addres(clientTO.getAddres())
				.phone(clientTO.getPhone()).email(clientTO.getEmail()).build();
	}

	public static List<ClientTO> map2TOs(List<ClientEntity> clientEntities) {
		List<ClientTO> clientTOs = new LinkedList<>();
		for (ClientEntity clientEntity : clientEntities) {
			ClientTO clientTO = map2TO(clientEntity);
			clientTOs.add(clientTO);
		}
		return clientTOs;
	}

	public static List<ClientEntity> map2Entities(List<ClientTO> clientTOs) {
		List<ClientEntity> clientEntities = new LinkedList<>();
		for (ClientTO clientTO : clientTOs) {
			ClientEntity clientEntity = map2Entity(clientTO);
			clientEntities.add(clientEntity);
		}
		return clientEntities;
	}
}
