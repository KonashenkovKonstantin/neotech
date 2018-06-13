package com.neotech.beans;

import java.sql.Timestamp;

public class TimestampBean {
	
	private long id;
	private Timestamp timestamp;
	
	public TimestampBean(long id, Timestamp timestamp) {
		this.id = id;
		this.timestamp = timestamp;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
}
