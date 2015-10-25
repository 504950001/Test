package com.stevex.demo;

import java.util.Date;
import java.util.Properties;

import org.exolab.castor.mapping.GeneralizedFieldHandler;
import org.exolab.castor.mapping.ValidityException;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class DateTimeFieldHandler extends GeneralizedFieldHandler {

	private static String dateFormatPattern;

	public void setConfiguration(Properties config) throws ValidityException {
		dateFormatPattern = config.getProperty("date-format");
	}

	public Object convertUponGet(Object value) {
		Date date = (Date)value;
		DateTime dateTime = new DateTime(date);
		return format(dateTime);
	}

	public Object convertUponSet(Object value) {
		String dateTimeString = (String) value;
		DateTime dataTime = parse(dateTimeString);
		return dataTime.toDate();
	}

	public Class<Date> getFieldType() {
		return Date.class;
	}

	protected static String format(final DateTime dateTime) {
		String dateTimeString = "";
		if (dateTime != null) {
			DateTimeFormatter dateTimeFormatter = DateTimeFormat
					.forPattern(dateFormatPattern);
			dateTimeString = dateTimeFormatter.print(dateTime);
		}
		return dateTimeString;
	}

	protected static DateTime parse(final String dateTimeString) {
		DateTime dateTime = new DateTime();
		if (dateTimeString != null) {
			DateTimeFormatter dateTimeFormatter = DateTimeFormat
					.forPattern(dateFormatPattern);
			dateTime = dateTimeFormatter.parseDateTime(dateTimeString);
		}
		return dateTime;
	}

}
