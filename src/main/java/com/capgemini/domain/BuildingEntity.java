package com.capgemini.domain;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "BUILDING")
public class BuildingEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2600680955519902461L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column
	private String description;
	@Column
	private String localization;
	@Column
	private int floors;
	@Column
	private boolean elevator;
	@Column
	private int flatsSum;	
	@OneToMany(mappedBy = "buildingEntities")
	private List<FlatEntity> flats = new LinkedList<>();
	@Version
	private Long version;
}
