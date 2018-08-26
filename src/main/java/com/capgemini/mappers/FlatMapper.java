package com.capgemini.mappers;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.capgemini.domain.ClientEntity;
import com.capgemini.domain.FlatEntity;
import com.capgemini.types.FlatTO;

public class FlatMapper {

	public static FlatTO map2TO(FlatEntity flatEntity){
		FlatTO flatTO = FlatTO.builder()
		.id(flatEntity.getId())
		.address(flatEntity.getAddress())
		.balcoons(flatEntity.getBalcoons())
		.price(flatEntity.getPrice())
		.rooms(flatEntity.getRooms())
		.status(flatEntity.getStatus())
		.floor(flatEntity.getFloor())
		.size(flatEntity.getSize())
		.version(flatEntity.getVersion())
		.coownedClientId(new ArrayList<>())
		.build();
		if (flatEntity.getOwner() != null) {
			flatTO.setOwnedClientId(flatEntity.getOwner().getId());			
		}
		List<ClientEntity> coowners = flatEntity.getCoowner();
		if (!coowners.isEmpty()) {
			for (ClientEntity clientEntity : coowners) {
				flatTO.addCoownedClientId(clientEntity.getId());			
			}
		}
		return flatTO;
	}
	
	public static FlatEntity map2Entity(FlatTO flatTO, FlatEntity flatEntity) {
		flatEntity.setAddress(flatTO.getAddress());
		flatEntity.setBalcoons(flatTO.getBalcoons());
		flatEntity.setPrice(flatTO.getPrice());
		flatEntity.setRooms(flatTO.getRooms());
		flatEntity.setStatus(flatTO.getStatus());
		flatEntity.setFloor(flatTO.getFloor());
		flatEntity.setSize(flatTO.getSize());
		flatEntity.setVersion(flatTO.getVersion());
		return flatEntity;
	}
	
	public static FlatEntity map2Entity(FlatEntity flatEntity, FlatTO flatTO, List<ClientEntity> coowners) {
		flatEntity = map2Entity(flatTO, flatEntity);
		for (ClientEntity clientEntity : coowners) {
			flatEntity.addCoowner(clientEntity);
		}
		return flatEntity;
	}
	
	public static FlatEntity map2Entity(FlatTO flatTO) {
		FlatEntity flatEntity = new FlatEntity();
		return map2Entity(flatTO, flatEntity);
	}
	
	public static List<FlatTO> map2TOs(List<FlatEntity> flatEntities) {
		List<FlatTO> flatTOs = new LinkedList<>();
		for (FlatEntity flatEntity : flatEntities) {
			flatTOs.add(map2TO(flatEntity));
		}
		return flatTOs;
	}
	
	public static List<FlatEntity> map2Entities(List<FlatTO> flatTOs) {
		List<FlatEntity> flatEntities = new LinkedList<>();
		for (FlatTO flatTO : flatTOs) {
			flatEntities.add(map2Entity(flatTO));
		}
		return flatEntities;
	}
}
