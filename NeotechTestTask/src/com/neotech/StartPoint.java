package com.neotech;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

import com.neotech.services.IDataService;
import com.neotech.services.IPrintService;
import com.neotech.services.ServiceLocator;
import com.neotech.threads.DataGeneratorTask;
import com.neotech.threads.DataSaverTask;

public class StartPoint {
	
	private static final String COMMAND_LINE_ARGUMENT_P = "-p";
	private static final Logger logger = Logger.getLogger(StartPoint.class);
	
	public static void main(String args[]) {
		
		logger.info("Programm started");
		if (isPrintTimestampMode(args)) {
			logger.info("'Print all timestamps' mode");	
			printAllTimestamps();
		} else {
			logger.info("'Send timestamps to DB' mode");
			startSendTimestampsInDB();
		}
		logger.info("Programm finished");
	}
	
	private static void startSendTimestampsInDB() {
		DataSaverTask dataSaverTask =  new DataSaverTask();
		Thread dataSaverThread = new Thread(dataSaverTask);
		
		try {
			dataSaverThread.start();		
			DataGeneratorTask dataGenerator = new DataGeneratorTask(dataSaverTask);
			ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
			executor.scheduleAtFixedRate(dataGenerator, 0, 1, TimeUnit.SECONDS);
			
			dataSaverThread.join();
		} catch(Exception ee) {
			logger.error("Error occurred during 'sending data to db'", ee);
			ServiceLocator.getPrintService().printAboutError(ee);
		}
	}
	
	private static void printAllTimestamps() {
		IDataService dataService = ServiceLocator.getDataService();
		IPrintService printService = ServiceLocator.getPrintService();
		
		try {
			printService.printTimestamps(dataService.getAllTimestamps());
		} catch(Exception ee) {
			logger.error("Error occurred during 'printing data from db'", ee);
			printService.printAboutError(ee);
		}
		
	}
	
	public static boolean isPrintTimestampMode(String args[]) {
		if (args == null || args.length == 0) {
			return false;
		} 
		
		for (String arg : args) {
			if (COMMAND_LINE_ARGUMENT_P.equals(arg)) {
				return true;
			}
		}
		return false;
	}
}
