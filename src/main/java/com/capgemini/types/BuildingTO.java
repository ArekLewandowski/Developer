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
public class BuildingTO {

	private Long id;
	private String description;
	private String localization;
	private int floors;
	private boolean elevator;
	private int flatsSum;
	private List<Long> flatsId;
	private Long version;
	
	public void addFlatId(Long id){
		this.flatsId.add(id);
	}
}
