package edu.remad.tutoring2.json.customserializer;

import java.io.IOException;

import org.springframework.boot.jackson.JsonComponent;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;

import edu.remad.tutoring2.appconstants.TimeAppConstants;
import edu.remad.tutoring2.models.Role;
import edu.remad.tutoring2.models.UserEntity;

@JsonComponent
public class UserEntitySerializer extends AbstractGenericTutoring2Serializer<UserEntity> {

	/**
	 * serial version UID
	 */
	private static final long serialVersionUID = 1L;

	public UserEntitySerializer() {
		super(UserEntity.class);
	}

	public UserEntitySerializer(Class<UserEntity> t) {
		super(t);
	}

	public UserEntitySerializer(Class<UserEntity> t, ObjectMapper objectMapper) {
		super(t, objectMapper);
	}

	@Override
	public void serialize(UserEntity value, JsonGenerator gen, SerializerProvider provider) throws IOException {
		gen.writeStartObject();
		gen.writeNumberField("id", value.getId());
		gen.writeStringField("username", value.getUsername());
		gen.writeStringField("email", value.getEmail());
		gen.writeStringField("password", value.getPassword());
		gen.writeBooleanField("enabled", value.getEnabled());

		gen.writeArrayFieldStart("roles");
		for (Role role : value.getRoles()) {
			gen.writeStartObject();
			gen.writeNumberField("id", role.getId());
			gen.writeStringField("name", role.getName());
			writeUserArray(gen, role);
			gen.writeEndObject();
		}
		gen.writeEndArray();

		gen.writeStringField("firstName", value.getFirstName());
		gen.writeStringField("lastName", value.getLastName());
		gen.writeStringField("gender", value.getGender());
		gen.writeStringField("cellPhone", value.getCellPhone());

		String addresses = objectMapper.writeValueAsString(value.getAddresses());
		gen.writeStringField("addresses", addresses);
		gen.writeStringField("creationDate", value.getCreationDate().format(TimeAppConstants.DATE_FORMATTER));
		gen.writeEndObject();
	}

	private void writeUserArray(JsonGenerator gen, Role role) throws IOException {
		gen.writeArrayFieldStart("users");
		gen.writeStartObject(role.getUsers(), role.getUsers().size());
		gen.writeEndObject();
		gen.writeEndArray();
	}
}
