package com.capgemini.types;

import java.util.LinkedList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FlatTO {

	private Long id;
	private int size;
	private int rooms;
	private int floor;
	private int balcoons;
	private String address;
	private String status;
	private int price;
	private Long buildingId;
	private Long ownedClientId;
	private List<Long> coownedClientId = new LinkedList<>();
	private Long version;

	public void addCoownedClientId(Long id) {
		this.coownedClientId.add(id);
	}
}
