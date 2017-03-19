package com.lastation.exercise.bookSrore.tool;

import java.sql.Date;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;

public class DateUitl {
	private DateUitl(){
	}
	
	public static String long2String(Long l) {
		Date d= new Date(l);
		return new SimpleDateFormat("yyyy年mm月dd日  HH:mm:ss").format(d);
	}
	
	public static Long string2Long(String str) throws ParseException {
		return new SimpleDateFormat("yyyy,mm,dd,HH,mm,ss").parse(str).getTime();
	}
}
