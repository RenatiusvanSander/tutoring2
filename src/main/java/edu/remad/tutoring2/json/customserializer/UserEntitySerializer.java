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
		boolean isValueNull = isEntityNull(value);

		gen.writeStartObject();
		gen.writeNumberField("id", isValueNull ? 0L : value.getId());
		gen.writeStringField("username", isValueNull ? null : value.getUsername());
		gen.writeStringField("email", isValueNull ? null : value.getEmail());
		gen.writeStringField("password", isValueNull ? null : value.getPassword());
		gen.writeBooleanField("enabled", isValueNull ? false : value.getEnabled());

		gen.writeArrayFieldStart("roles");
		for (Role role : value.getRoles()) {
			gen.writeStartObject();
			gen.writeNumberField("id", role.getId());
			gen.writeStringField("name", role.getName());
			writeUserArray(gen, role);
			gen.writeEndObject();
		}
		gen.writeEndArray();

		gen.writeStringField("firstName", isValueNull ? null : value.getFirstName());
		gen.writeStringField("lastName", isValueNull ? null : value.getLastName());
		gen.writeStringField("gender", isValueNull ? null : value.getGender());
		gen.writeStringField("cellPhone", isValueNull ? null : value.getCellPhone());

		String addresses = objectMapper.writeValueAsString(isValueNull ? null : value.getAddresses());
		gen.writeStringField("addresses", addresses);
		gen.writeStringField("creationDate",
				isValueNull ? null : value.getCreationDate().format(TimeAppConstants.DATE_FORMATTER));
		gen.writeEndObject();
	}

	private void writeUserArray(JsonGenerator gen, Role role) throws IOException {
		gen.writeArrayFieldStart("users");
		if (!role.getUsers().isEmpty()) {
			for (UserEntity user : role.getUsers()) {
				gen.writeStartObject();
				gen.writeNumberField("id", role.getId());
				gen.writeEndObject();
			}
		}
		gen.writeEndArray();
	}
}
