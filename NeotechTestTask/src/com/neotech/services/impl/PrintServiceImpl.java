package com.neotech.services.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.neotech.beans.TimestampBean;
import com.neotech.services.IPrintService;

public class PrintServiceImpl implements IPrintService {
	
	private static final DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
	private static final String TIMESTAMP_COMMOM_INFO = "There are %d records in database\n";
	private static final String TIMESTAMP_EACH_RECORD = "id: %d, timestamp: %s\n";

	@Override
	public void printTimestamps(List<TimestampBean> timestampList ) {
		if (timestampList == null) {
			return;
		}
		
		System.out.printf(TIMESTAMP_COMMOM_INFO, timestampList.size());
		for (TimestampBean baseRecord : timestampList) {
			System.out.printf(TIMESTAMP_EACH_RECORD, baseRecord.getId(), baseRecord.getTimestamp());
		}
	}

	@Override
	public void printAboutErrorWithDB() {
		System.out.println(dateFormat.format(new Date()) + ". There is some problem with db. We will try to connect in 5 seconds");
		
	}

	@Override
	public void printAboutError(Exception ee) {
		System.out.println("Error: ");
		ee.printStackTrace();
		
	}
	
	
	
}
