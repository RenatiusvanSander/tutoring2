package edu.remad.tutoring2.json.customdeserializers;

import java.io.IOException;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import edu.remad.tutoring2.models.ReminderEntity;

public class ReminderEntityDeserializer  extends StdDeserializer<ReminderEntity>  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ReminderEntityDeserializer() {
		this(null);
	}
	
	public ReminderEntityDeserializer(Class<?> vc) {
		super(vc);
	}

	@Override
	public ReminderEntity deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
		return null;
	}
}
