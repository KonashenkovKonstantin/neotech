package com.neotech.services.impl;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import com.neotech.beans.TimestampBean;
import com.neotech.dao.ITimestampDAO;
import com.neotech.dao.base.MySqlDAOFactory;
import com.neotech.services.IDataService;

public class DataServiceImpl implements IDataService {

	
	@Override
	public void saveTimestamps(List<Timestamp> timestampList) throws SQLException {
		ITimestampDAO dao = MySqlDAOFactory.getInstance().getTimestampDAO();
		dao.saveTimestamps(timestampList);
		return;
	}

	@Override
	public List<TimestampBean> getAllTimestamps() throws SQLException  {
		ITimestampDAO dao = MySqlDAOFactory.getInstance().getTimestampDAO();
		return dao.getAllTimestamp();
	}

}
