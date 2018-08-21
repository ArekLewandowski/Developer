package com.capgemini.types;

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
}
