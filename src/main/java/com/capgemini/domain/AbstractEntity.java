package com.capgemini.domain;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.*;

@MappedSuperclass
public abstract class AbstractEntity {

	@Temporal(TemporalType.TIMESTAMP)
	@Column(updatable = false)
	private Date created;

	@Temporal(TemporalType.TIMESTAMP)
	@Column
	private Date updated;

	public Date getCreated() {
		return created;
	}

	public void setCreated(final Timestamp created) {
		this.created = created;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(final Timestamp updated) {
		this.updated = updated;
	}
}