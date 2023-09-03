package com.daniel.upload.api.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class DateUtils {
	
	private DateUtils() {}
	
	public static Date toDate(String value) {
		
		try {
			return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX").parse(value);
		} catch (ParseException e) {
			return null;
		}
	}

}
