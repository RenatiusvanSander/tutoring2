package edu.remad.tutoring2.appconstants;

import java.time.format.DateTimeFormatter;

public final class TimeAppConstants {

	private TimeAppConstants() {
	}

	public static final DateTimeFormatter LOCAL_DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
	
	public static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");

	public static final int REMINDER_EMAIL_TIME_ON_5_O_CLOCK = 5;

	public static final int TIME_PERIOD_DAY_IN_HOURS = 24;
}
