package com.situ.crm.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SwitchingTime {

	public static Date toSwitchingTime(String string) {
		SimpleDateFormat simpleDateFormat =new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date date = null;
        try {
			date = simpleDateFormat.parse(string);
		} catch (ParseException e) {
			e.printStackTrace();
		}
        return date;
	}
}
