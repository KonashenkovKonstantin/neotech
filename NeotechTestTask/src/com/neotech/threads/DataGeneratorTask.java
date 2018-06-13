package com.neotech.threads;

import java.sql.Timestamp;
import java.util.Date;

public class DataGeneratorTask implements Runnable {
	
	private DataSaverTask dataSaverTask;
	
	public DataGeneratorTask(DataSaverTask dataSaverTask) {
		this.dataSaverTask = dataSaverTask;
	}
	
	public void run() {		
		Timestamp timestamp = new Timestamp((new Date()).getTime());
		dataSaverTask.addTimestamp(timestamp);
	}

}
