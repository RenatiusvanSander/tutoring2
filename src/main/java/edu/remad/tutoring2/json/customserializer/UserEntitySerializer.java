package edu.remad.tutoring2.json.customserializer;

import java.io.IOException;

import org.springframework.boot.jackson.JsonComponent;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import edu.remad.tutoring2.models.Role;
import edu.remad.tutoring2.models.UserEntity;

@JsonComponent
public class UserEntitySerializer extends StdSerializer<UserEntity> {

	/**
	 * serial version UID
	 */
	private static final long serialVersionUID = 1L;

	public UserEntitySerializer() {
		this(null);
	}
	
	public UserEntitySerializer(Class<UserEntity> t) {
		super(t);
	}

	@Override
	public void serialize(UserEntity value, JsonGenerator gen, SerializerProvider provider) throws IOException {
		gen.writeStartObject();
		gen.writeNumberField("id", value.getId());
		gen.writeStringField("username", value.getUsername());
		gen.writeStringField("email", value.getEmail());
		gen.writeStringField("password", value.getPassword());
		gen.writeBooleanField("enabled", value.getEnabled());
		gen.writeStartArray();
		for(Role role : value.getRoles()) {
			gen.writeStartObject();
			gen.writeNumberField("id", role.getId());
			gen.writeStringField("name", role.getName());
			gen.writeEndObject();
		}
		gen.writeEndArray();
		gen.writeEndObject();
	}
}
