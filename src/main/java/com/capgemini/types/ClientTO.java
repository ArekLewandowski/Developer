package com.capgemini.types;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClientTO {

	private Long id;
	private String firstName;
	private String lastName;
	private String email;
	private String address;
	private String phone;
	private List<Long> ownedId;
	private List<Long> coownedId;	
	private Long version;
	
	public void addOwnedFlatId(Long id){
		this.ownedId.add(id);
	}		
		
	public void addCoownedFlatId(Long id){
		this.coownedId.add(id);
	}
}
