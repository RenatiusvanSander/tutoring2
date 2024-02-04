package edu.remad.tutoring2.appconstants;

import java.time.format.DateTimeFormatter;

public final class TimeAppConstants {

	private TimeAppConstants() {
	}
	
	public static final DateTimeFormatter LOCAL_DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
}
