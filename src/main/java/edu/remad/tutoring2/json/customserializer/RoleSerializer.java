package edu.remad.tutoring2.json.customserializer;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import edu.remad.tutoring2.models.Role;

public class RoleSerializer extends StdSerializer<Role> {

	/**
	 * serial version UID
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private ObjectMapper objectMapper;

	public RoleSerializer() {
		super(Role.class);
	}
	
	public RoleSerializer(Class<Role> t) {
		super(t);
	}
	
	public RoleSerializer(Class<Role> t, ObjectMapper objectMapper) {
		super(t);
		this.objectMapper = objectMapper;
	}

	@Override
	public void serialize(Role value, JsonGenerator gen, SerializerProvider provider) throws IOException {		
	}
}
