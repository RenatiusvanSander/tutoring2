package edu.remad.tutoring2.json;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;

import edu.remad.tutoring2.appconstants.TimeAppConstants;
import edu.remad.tutoring2.models.Role;
import edu.remad.tutoring2.models.UserEntity;

public final class JsonBaseDeserializerHelper {
	
	public static LocalDateTime convertToLocalDateTime(String json) {
	    return LocalDateTime.parse(json, TimeAppConstants.LOCAL_DATE_TIME_FORMATTER);
	}
	
	public static LocalDateTime convertToLocalDateTimeAsDate(String json) {
		LocalDate localDate = LocalDate.parse(json, TimeAppConstants.DATE_FORMATTER);
		return LocalDateTime.of(localDate, LocalDateTime.now().toLocalTime());
	}
	
	public static ObjectReader createRolesReader(ObjectMapper objectMapper) {
		return objectMapper.readerFor(new TypeReference<List<Role>>() {});
	}
	
	public static ObjectReader createUserReader(ObjectMapper objectMapper) {
		return objectMapper.readerFor(new TypeReference<List<UserEntity>>() {});
	}
}
