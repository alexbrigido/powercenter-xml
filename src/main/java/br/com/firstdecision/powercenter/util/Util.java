package br.com.firstdecision.powercenter.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {

	public static String newDateToString(String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(new Date());
	}
	
	
}
