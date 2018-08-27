package com.capgemini.mappers;

import java.util.LinkedList;
import java.util.List;

import com.capgemini.domain.ClientEntity;
import com.capgemini.domain.FlatEntity;
import com.capgemini.types.ClientTO;

public class ClientMapper {

	public static ClientTO map2TO(ClientEntity clientEntity) {
		ClientTO clientTO = ClientTO.builder().id(clientEntity.getId()).firstName(clientEntity.getFirstName())
				.lastName(clientEntity.getLastName()).address(clientEntity.getAddress()).phone(clientEntity.getPhone())
				.email(clientEntity.getEmail()).version(clientEntity.getVersion()).ownedId(new LinkedList<>())
				.coownedId(new LinkedList<>()).build();
		List<FlatEntity> ownedFlats = clientEntity.getOwned();
		if (ownedFlats != null) {
			for (FlatEntity flatEntity : ownedFlats) {
				clientTO.addOwnedFlatId(flatEntity.getId());
			}
		}
		return clientTO;
	}

	public static ClientEntity map2Entity(ClientTO clientTO) {
		return ClientEntity.builder().firstName(clientTO.getFirstName()).lastName(clientTO.getLastName())
				.address(clientTO.getAddress()).phone(clientTO.getPhone()).email(clientTO.getEmail()).build();
	}

	@SuppressWarnings("static-access")
	public static ClientEntity map2Entity(ClientTO clientTO, ClientEntity clientEntity) {
		clientEntity.builder().address(clientTO.getAddress()).email(clientTO.getAddress())
				.firstName(clientTO.getFirstName()).lastName(clientTO.getLastName()).phone(clientTO.getPhone())
				.version(clientTO.getVersion()).build();
		return clientEntity;
	}

	public static ClientEntity map2Entity(ClientTO clientTO, ClientEntity clientEntity,
			List<FlatEntity> flatOwnedEntities, List<FlatEntity> flatCoownedEntities) {
		ClientEntity clientMappedEntity = map2Entity(clientTO, clientEntity);
		if (flatOwnedEntities != null) {
			for (FlatEntity flatEntity : flatOwnedEntities) {
				clientMappedEntity.addOwned(flatEntity);
			}
		}
		if (flatCoownedEntities != null) {
			for (FlatEntity flatEntity : flatCoownedEntities) {
				clientMappedEntity.addCoowned(flatEntity);
			}
		}
		return clientMappedEntity;
	}

	public static ClientEntity map2Entity(ClientTO clientTO, List<FlatEntity> flatOwnedEntities,
			List<FlatEntity> flatCoownedEntities) {
		ClientEntity clientEntity = map2Entity(clientTO);
		if (flatOwnedEntities != null) {
			for (FlatEntity flatEntity : flatOwnedEntities) {
				clientEntity.addOwned(flatEntity);
			}
		}
		if (flatCoownedEntities != null) {
			for (FlatEntity flatEntity : flatCoownedEntities) {
				clientEntity.addCoowned(flatEntity);
			}
		}
		return clientEntity;
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
