package edu.remad.tutoring2.json.customserializer;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonComponent;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import edu.remad.tutoring2.appconstants.TimeAppConstants;
import edu.remad.tutoring2.models.Role;
import edu.remad.tutoring2.models.UserEntity;

@JsonComponent
public class UserEntitySerializer extends StdSerializer<UserEntity> {

	/**
	 * serial version UID
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private ObjectMapper objectMapper;

	public UserEntitySerializer() {
		super(UserEntity.class);
	}
	
	public UserEntitySerializer(Class<UserEntity> t) {
		super(t);
	}
	
	public UserEntitySerializer(Class<UserEntity> t, ObjectMapper objectMapper) {
		super(t);
		this.objectMapper = objectMapper;
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
			gen.writeStartObject(role.getUsers(), role.getUsers().size());
			gen.writeEndObject();
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
}
