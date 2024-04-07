package edu.remad.tutoring2.json.customdeserializers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import edu.remad.tutoring2.models.Role;
import edu.remad.tutoring2.models.TutoringAppointmentEntity;

public class RoleDeserializer extends StdDeserializer<Role> {

	/**
	 * serial version UID
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private ObjectMapper objectMapper;

	public RoleDeserializer() {
		this(Role.class);
	}

	public RoleDeserializer(Class<?> vc) {
		super(vc);
	}

	public RoleDeserializer(ObjectMapper objectMapper) {
		this(TutoringAppointmentEntity.class);
		this.objectMapper = objectMapper;
	}

	@Override
	public Role deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
		return new Role();
	}

	/**
	 * Sets Codec
	 * 
	 * @param c {@link ObjectCodec} shalls be {@link ObjectMapper} or
	 *          {@link ObjectReader}
	 */
	public void setCodec(ObjectCodec c) {
		objectMapper = (ObjectMapper) c;
	}
}
