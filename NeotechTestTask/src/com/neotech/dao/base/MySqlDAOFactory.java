package com.neotech.dao.base;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

import com.neotech.beans.DBConfigBean;
import com.neotech.dao.ITimestampDAO;
import com.neotech.dao.impl.TimestampDAO;
import com.neotech.helpers.ParseConfigHelper;
import com.neotech.services.ServiceLocator;

public class MySqlDAOFactory implements DAOFactory {
	private static final Logger logger = Logger.getLogger(MySqlDAOFactory.class);
	
	private static String dbUrl = "jdbc:mysql://localhost/%s";
	private static String dbUser = "";
	private static String dbPassword = "";
	
	private static final String SQL_CREATE_TABLE = 
			"CREATE TABLE IF NOT EXISTS TimeInfo ("
			+ "id INT AUTO_INCREMENT PRIMARY KEY, "
			+ "timeInfo TIMESTAMP)";
	
	private ITimestampDAO timestampDAO = new TimestampDAO();
	private static MySqlDAOFactory instance = null;
	
	public static MySqlDAOFactory getInstance() {
		if (instance == null) {
			synchronized (MySqlDAOFactory.class) {
				if (instance == null) {
					try {
						instance = new MySqlDAOFactory();
					} catch(Exception ee) {
						logger.error("Somethig went wrong during db initialization", ee);
					}
				}				
			}
		}
		return instance;
	}
	
	private MySqlDAOFactory() throws Exception {
		try {
			Class.forName ("com.mysql.jdbc.Driver");
			initDBConfiguration();
			initDB();
		} catch(ClassNotFoundException e) {
			ServiceLocator.getPrintService().printAboutError(e);
		}
	}
	
	private void initDBConfiguration() throws Exception {
		DBConfigBean dbConfigBean = ParseConfigHelper.parseDBConfig();		
		dbUrl = String.format(dbUrl, dbConfigBean.getDbName());
		dbUser = dbConfigBean.getDbUser();
		dbPassword = dbConfigBean.getDbPassword();		
	}
	
	private void initDB() throws Exception {
		logger.info("Started init MySQL db");
		try (Connection con = getConnection(); Statement stmt = con.createStatement(); ){
			stmt.executeUpdate(SQL_CREATE_TABLE);
			
		} catch(Exception ee) {
			logger.error("Error occurred during init db", ee);
		}
		
		logger.info("Finished init MySQL db"); 
	}
	

	@Override
	public ITimestampDAO getTimestampDAO() {
		return timestampDAO;
	}

	@Override
	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection (dbUrl, dbUser, dbPassword);
	}

}
