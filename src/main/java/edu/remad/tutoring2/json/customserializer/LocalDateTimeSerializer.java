package edu.remad.tutoring2.json.customserializer;

import java.io.IOException;
import java.time.LocalDateTime;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import edu.remad.tutoring2.appconstants.TimeAppConstants;

public class LocalDateTimeSerializer extends StdSerializer<LocalDateTime> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LocalDateTimeSerializer() {
		this(null);
	}
	
	public LocalDateTimeSerializer(Class<LocalDateTime> type) {
		super(type);
	}

	@Override
	public void serialize(LocalDateTime value, JsonGenerator gen, SerializerProvider provider)
			throws IOException {
		gen.writeStartObject();
		gen.writeEndObject();
	}
}
