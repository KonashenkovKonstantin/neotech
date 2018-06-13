package com.neotech.services;

import java.util.List;

import com.neotech.beans.TimestampBean;

public interface IPrintService {
	
	void printTimestamps(List<TimestampBean> timestampList);
	
	void printAboutErrorWithDB();
	
	void printAboutError(Exception ee);
	

}
