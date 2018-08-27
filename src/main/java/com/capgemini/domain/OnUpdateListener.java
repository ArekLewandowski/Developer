package com.capgemini.domain;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.PreUpdate;

public class OnUpdateListener {

	@PreUpdate
	protected void onUpdate(final AbstractEntity abstractEntity) {
		Date updateDate = new Date();
		abstractEntity.setUpdated(new Timestamp(updateDate.getTime()));
	}
}
