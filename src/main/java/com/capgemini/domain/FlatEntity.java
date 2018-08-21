package com.capgemini.domain;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;

import com.capgemini.enums.FLAT_STATUS;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "FLAT")
public class FlatEntity  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6738130609171027027L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column
	private int size;
	@Column
	private int rooms;
	@Column
	private int floor;
	@Column
	private int balcoons;
	@Column
	private String address;
	@Column
	private FLAT_STATUS status;
	@Column
	private int price;
	
	@ManyToOne
	private List<BuildingEntity> buildingEntities = new LinkedList<>();
	
	@ManyToOne
	private List<ClientEntity> owner = new LinkedList<>();

	@ManyToMany
	private List<ClientEntity> coowner = new LinkedList<>();
	
	@Version
	private Long version;
}
