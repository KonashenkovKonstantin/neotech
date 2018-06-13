package com.neotech.helpers;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.neotech.beans.DBConfigBean;

public class ParseConfigHelper {
	
	private static final Logger logger = Logger.getLogger(ParseConfigHelper.class);
	
	private static final String CONFIG_VALUE_DELIMITER = "=";
	
	private static final String DB_CONFIG_NAME = "dbName";
	private static final String DB_CONFIG_USER = "dbUser";
	private static final String DB_CONFIG_PASSWORD = "dbPassword";
	
	private static final String PATH_TO_DB_CONFIG = "./db.properties";
	
	public static DBConfigBean parseDBConfig() throws Exception {
		DBConfigBean dbConfigBean = new DBConfigBean();
		Map<String, String> map = parseConfig(PATH_TO_DB_CONFIG);
		dbConfigBean.setDbName(map.get(DB_CONFIG_NAME));
		dbConfigBean.setDbUser(map.get(DB_CONFIG_USER));
		dbConfigBean.setDbPassword(map.get(DB_CONFIG_PASSWORD));
		return dbConfigBean; 
	}
	
	private static Map<String,String> parseConfig(String pathToConfig) throws Exception {
		
		try (Scanner scan = new Scanner(new File(pathToConfig))){			
			Map<String, String> map = new HashMap<>();
			
			while(scan.hasNextLine()) {
				String line = scan.nextLine();
				addNewConfig(map, line);
			}
			return map;			
		}
	}
	
	public static void addNewConfig(Map<String,String> map, String line) {
		//1. empty line
		if (line == null || line.length() == 0) {
			return;
		}
		
		//2. no value or / and config
		String[] configs = line.split(CONFIG_VALUE_DELIMITER);
		if (configs.length != 2) {
			return;
		}
		
		String config = configs[0].trim();
		String value = configs[1].trim();
		
		//3. empty value or/and config
		if (config.length() == 0 || value.length() == 0) {
			return;
		}
		
		map.put(config, value);		
	}

}
