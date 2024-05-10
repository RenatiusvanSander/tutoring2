package edu.remad.tutoring2.json.customserializer;

import java.io.IOException;

import org.springframework.boot.jackson.JsonComponent;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import edu.remad.tutoring2.models.AddressEntity;

@JsonComponent
public class AddressEntitySerializer extends StdSerializer<AddressEntity> {

	/**
	 * serial version UID
	 */
	private static final long serialVersionUID = 1L;

	public AddressEntitySerializer() {
		super(AddressEntity.class);
	}
	
	public AddressEntitySerializer(Class<AddressEntity> t) {
		super(t);
	}

	@Override
	public void serialize(AddressEntity value, JsonGenerator gen, SerializerProvider provider)
			throws IOException {
		gen.writeStartObject();
		gen.writeNumberField("id", value.getId());
		gen.writeStringField("addressStreet", value.getAddressStreet());
		gen.writeStringField("addressHouseNo", value.getAddressHouseNo());
		gen.writeObjectField("addressZipCode", value.getAddressZipCode());
		gen.writeEndObject();
	}
}
