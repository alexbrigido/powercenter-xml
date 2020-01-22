package br.com.firstdecision.powercenter.xml;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {

	public static String newDateToString(String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(new Date());
	}
	
	
}
