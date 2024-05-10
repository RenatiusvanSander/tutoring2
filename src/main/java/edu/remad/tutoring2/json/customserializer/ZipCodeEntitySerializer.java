package edu.remad.tutoring2.json.customserializer;

import java.io.IOException;

import org.springframework.boot.jackson.JsonComponent;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import edu.remad.tutoring2.appconstants.TimeAppConstants;
import edu.remad.tutoring2.models.ZipCodeEntity;

@JsonComponent
public class ZipCodeEntitySerializer extends StdSerializer<ZipCodeEntity> {

	/**
	 * serial version UID
	 */
	private static final long serialVersionUID = 1L;

	public ZipCodeEntitySerializer() {
		super(ZipCodeEntity.class);
	}
	
	public ZipCodeEntitySerializer(Class<ZipCodeEntity> t) {
		super(t);
	}

	@Override
	public void serialize(ZipCodeEntity value, JsonGenerator gen, SerializerProvider provider) throws IOException {
		gen.writeStartObject();
		gen.writeNumberField("id", value.getId());
		gen.writeStringField("zipCode", value.getZipCode());
		gen.writeStringField("zipCodeLocation", value.getZipCodeLocation());
		gen.writeStringField("zipCodeCreationDate", value.getZipCodeCreationDate().format(TimeAppConstants.DATE_FORMATTER));
		gen.writeEndObject();
	}
}
