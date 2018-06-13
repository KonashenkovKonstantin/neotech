package com.neotech.threads;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.neotech.services.IDataService;
import com.neotech.services.ServiceLocator;

public class DataSaverTask implements Runnable {
	
	private static final Logger logger = Logger.getLogger(DataSaverTask.class);
	
	private static final int BATCH_SIZE = 20;	
	private static final int CHECK_FOR_BUFFER_DELAY = 1_000;
	private static final int TRY_AGAIN_AFTER_ERROR_DELAY = 5_000;
	
	private Object timestampBlock = new Object();
	private ArrayDeque<Timestamp> buffer = new ArrayDeque<>();
	
	
	public void addTimestamp(Timestamp timestamp) {
		synchronized (timestampBlock) {
			buffer.add(timestamp);
		}
		
	}
	
	public List<Timestamp> getListOfTimestamps() {
		synchronized (timestampBlock) {
			List<Timestamp> list = new ArrayList<>();
			int counter = 0;
			while(counter < BATCH_SIZE && !buffer.isEmpty()) {
				list.add(buffer.poll());
				counter++;
			}
			return list;
		}
	}
	
	public boolean isEmptyBuffer() {
		synchronized (timestampBlock) {
			return buffer.isEmpty();
		}
	}
	

	@Override
	public void run() {
		try {
			while(true) {
				if (isEmptyBuffer()) {					
					Thread.currentThread().sleep(CHECK_FOR_BUFFER_DELAY);
				} else {					
					sendDataToDB();
				}			
			}
		} catch(Exception ee) {
			logger.error("Something went wrong", ee );
		}
		
	}
	
	private void sendDataToDB() throws InterruptedException {
		List<Timestamp> listOfTimestamps = getListOfTimestamps();
		IDataService dataService = ServiceLocator.getDataService();
		boolean successResult = false;
		while(!successResult) {
			try {
				dataService.saveTimestamps(listOfTimestamps);
				successResult = true;
			} catch(SQLException sdle) {
				ServiceLocator.getPrintService().printAboutErrorWithDB();
				Thread.currentThread().sleep(TRY_AGAIN_AFTER_ERROR_DELAY);				
			} 			
		}		
	}
}
