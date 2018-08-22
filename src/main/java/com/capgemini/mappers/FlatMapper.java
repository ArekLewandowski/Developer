package com.capgemini.mappers;

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
		.build();
		flatTO.setOwnedClientId(flatEntity.getOwner().getId());
		List<ClientEntity> coowners = flatEntity.getCoowner();
		for (ClientEntity clientEntity : coowners) {
			flatTO.addCoownedClientId(clientEntity.getId());
		}
		return flatTO;
	}
	
	@SuppressWarnings("static-access")
	public static FlatEntity map2Entity(FlatTO flatTO, FlatEntity flatEntity) {
		flatEntity.builder()
		.address(flatTO.getAddress())
		.balcoons(flatTO.getBalcoons())
		.price(flatTO.getPrice())
		.rooms(flatTO.getRooms())
		.status(flatTO.getStatus())
		.floor(flatTO.getFloor())
		.size(flatTO.getSize())
		.build();
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
}
