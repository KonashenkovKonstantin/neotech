package com.neotech.dao;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import com.neotech.beans.TimestampBean;

public interface ITimestampDAO {
	void saveTimestamps(List<Timestamp> timestampList) throws SQLException;
	List<TimestampBean> getAllTimestamp() throws SQLException;

}
