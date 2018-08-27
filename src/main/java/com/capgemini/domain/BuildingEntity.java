package com.capgemini.domain;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.transaction.Transactional;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Transactional
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "BUILDING")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@EntityListeners({OnCreateListener.class, OnUpdateListener.class})
public class BuildingEntity extends AbstractEntity{

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
	@OneToMany(mappedBy = "building", cascade = CascadeType.REMOVE)
	private List<FlatEntity> flats = new LinkedList<>();
	@Version
	private Long version;
	
	public void addFlat(FlatEntity fEntity){
		this.flats.add(fEntity);
	}
}
