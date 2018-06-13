package com.neotech.dao.base;

import java.sql.Connection;
import java.sql.SQLException;

import com.neotech.dao.ITimestampDAO;

public interface DAOFactory {
	
	ITimestampDAO getTimestampDAO();
	
	Connection getConnection() throws SQLException;

}
