package edu.remad.tutoring2.json.customserializer;

import java.io.IOException;


import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;

import edu.remad.tutoring2.models.Role;

public class RoleSerializer extends AbstractGenericTutoring2Serializer<Role> {

	/**
	 * serial version UID
	 */
	private static final long serialVersionUID = 1L;

	public RoleSerializer() {
		super(Role.class);
	}

	public RoleSerializer(Class<Role> t) {
		super(t);
	}

	public RoleSerializer(Class<Role> t, ObjectMapper objectMapper) {
		super(t, objectMapper);
	}

	@Override
	public void serialize(Role value, JsonGenerator gen, SerializerProvider provider) throws IOException {
		gen.writeStartObject();
		gen.writeNumberField("id", value.getId());
		gen.writeStringField("name", value.getName());
		
		String users = objectMapper.writeValueAsString(value.getUsers());
		gen.writeObjectField("users", users);
		gen.writeEndObject();
		gen.flush();
	}
}
