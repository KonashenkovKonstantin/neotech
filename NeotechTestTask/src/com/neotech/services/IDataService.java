package com.neotech.services;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import com.neotech.beans.TimestampBean;

public interface IDataService {
	
	void saveTimestamps(List<Timestamp> timestampList) throws SQLException;
	
	List<TimestampBean> getAllTimestamps() throws SQLException;
	

}
