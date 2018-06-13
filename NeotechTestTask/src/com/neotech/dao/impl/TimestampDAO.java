package com.neotech.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.neotech.beans.TimestampBean;
import com.neotech.dao.ITimestampDAO;
import com.neotech.dao.base.MySqlDAOFactory;

public class TimestampDAO implements ITimestampDAO {
	
	private static final String SQL_INSERT_TIMESTAMP = "insert into timeInfo (timeInfo) values (?)";
	private static final String SQL_GET_ALL_TIMESTAMPS = "select * from timeInfo";

	@Override
	public void saveTimestamps(List<Timestamp> timestampList) throws SQLException {
		PreparedStatement prep = null;
		ResultSet resultSet = null;
		Connection con = null;
		
		try {
			con = getConnection();
			prep = con.prepareStatement(SQL_INSERT_TIMESTAMP);			
			for (Timestamp timestamp : timestampList) {
				prep.setTimestamp(1, timestamp);
				prep.addBatch();
			}			
			prep.executeBatch();
			
		} finally {
			if (con != null) {con.close();}
			if (prep != null) {prep.close();}
			if (resultSet != null) {resultSet.close();}
		}
	}

	@Override
	public List<TimestampBean> getAllTimestamp() throws SQLException {
		PreparedStatement prep = null;
		ResultSet resultSet = null;
		Connection con = null;
		
		try {
			con = getConnection();
			prep = con.prepareStatement(SQL_GET_ALL_TIMESTAMPS);
			
			List<TimestampBean> result = new ArrayList<>();			
			ResultSet rs = prep.executeQuery();			
			while (rs.next()) {
				result.add(new TimestampBean(rs.getLong("id"), rs.getTimestamp("timeInfo")));				
			}
			return result;
			
		} finally {
			if (con != null) {con.close();}
			if (prep != null) {prep.close();}
			if (resultSet != null) {resultSet.close();}
		}
	}
	
	private Connection getConnection() throws SQLException {
		return MySqlDAOFactory.getInstance().getConnection();
	}

}
