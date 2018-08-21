package com.capgemini.types;

import com.capgemini.enums.FLAT_STATUS;

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
	private FLAT_STATUS status;
	private int price;
}
