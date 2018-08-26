package com.capgemini.types;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FlatSearchCriteriaTO {

	private Integer minSize;
	private Integer maxSize;
	private Integer minRooms;
	private Integer maxRooms;
	private Integer minBalcons;
	private Integer maxBalcons;
}
