package com.pccw.springframework.utility;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
	public static String formatDateTime(String format,Date date){
		if(date == null){
			return "";
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}
}
