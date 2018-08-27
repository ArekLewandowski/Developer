package com.capgemini.domain;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "FLAT")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@EntityListeners({ OnCreateListener.class, OnUpdateListener.class })
public class FlatEntity extends AbstractEntity {

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
	private String status;
	@Column
	private int price;

	@ManyToOne
	@JoinColumn
	private BuildingEntity building;

	@ManyToOne
	private ClientEntity owner;

	@ManyToMany
	private List<ClientEntity> coowner = new LinkedList<>();

	@Version
	private Long version;

	public void addCoowner(ClientEntity cEntity) {
		this.coowner.add(cEntity);
	}
}
