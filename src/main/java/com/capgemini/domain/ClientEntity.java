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
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
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
@Table(name = "CLIENT")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@EntityListeners({OnCreateListener.class, OnUpdateListener.class})
public class ClientEntity extends AbstractEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column
	private String firstName;
	@Column
	private String lastName;
	@Column
	private String email;
	@Column
	private String address;
	@Column
	private String phone;
	@OneToMany(mappedBy = "owner")
	private List<FlatEntity> owned = new LinkedList<>();
	@ManyToMany(mappedBy = "coowner")
	private List<FlatEntity> coowned = new LinkedList<>();
	@Version
	private Long version;

	public void addOwned(FlatEntity flatEntity){
		this.owned.add(flatEntity);
	}
	
	public void addCoowned(FlatEntity flatEntity){
		this.coowned.add(flatEntity);
	}
}
