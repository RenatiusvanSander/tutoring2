package edu.remad.tutoring2.json;

import java.time.LocalDateTime;

import edu.remad.tutoring2.appconstants.TimeAppConstants;

public final class JsonBaseDeserializerHelper {
	
	public static LocalDateTime convertToLocalDateTime(String json) {
	    return LocalDateTime.parse(json, TimeAppConstants.LOCAL_DATE_TIME_FORMATTER);
	}
}
