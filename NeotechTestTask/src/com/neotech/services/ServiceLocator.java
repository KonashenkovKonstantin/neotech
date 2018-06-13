package com.neotech.services;

import org.apache.log4j.Logger;

import com.neotech.services.impl.DataServiceImpl;
import com.neotech.services.impl.PrintServiceImpl;
 
public class ServiceLocator {
	private final static Logger logger = Logger.getLogger(ServiceLocator.class);	
	private static IDataService dataService;
	private static IPrintService printService;
	
	static {
		synchronized (ServiceLocator.class) {
			try {
				dataService = new DataServiceImpl();
				printService = new PrintServiceImpl();
				
				logger.info("Location service. Successfully initialized services");
				
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}		
	}
	
	public static IDataService getDataService() {
		return dataService;
	}
	
	public static IPrintService getPrintService() {
		return printService;
	}
}
