package edu.remad.tutoring2.json.customserializer;

import java.io.IOException;

import org.springframework.boot.jackson.JsonComponent;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;

import edu.remad.tutoring2.models.AddressEntity;

@JsonComponent
public class AddressEntitySerializer extends AbstractGenericTutoring2Serializer<AddressEntity> {

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

	public AddressEntitySerializer(Class<AddressEntity> t, ObjectMapper objectMapper) {
		super(t, objectMapper);
	}

	@Override
	public void serialize(AddressEntity value, JsonGenerator gen, SerializerProvider provider) throws IOException {
		gen.writeStartObject();
		gen.writeNumberField("id", value.getId());
		gen.writeStringField("addressStreet", value.getAddressStreet());
		gen.writeStringField("addressHouseNo", value.getAddressHouseNo());
		//gen.writeObjectField("user", value.getUser()); //TODO
		gen.writeObjectField("addressZipCode", value.getAddressZipCode());
		gen.writeEndObject();
	}
}
