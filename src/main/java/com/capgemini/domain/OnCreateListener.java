package com.capgemini.domain;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.PrePersist;

public class OnCreateListener {

	@PrePersist
	protected void onCreate(final AbstractEntity abstractEntity) {
		Date creationDate = new Date();
		abstractEntity.setCreated(new Timestamp(creationDate.getTime()));
	}
}
