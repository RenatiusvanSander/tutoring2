package edu.remad.tutoring2.json.customdeserializers;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.util.JsonParserDelegate;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.cfg.DeserializerFactoryConfig;
import com.fasterxml.jackson.databind.deser.BeanDeserializerFactory;
import com.fasterxml.jackson.databind.deser.DeserializerFactory;
import com.fasterxml.jackson.databind.deser.DefaultDeserializationContext.Impl;

import edu.remad.tutoring2.json.AbstractJsonJUnit5Test;
import edu.remad.tutoring2.models.TutoringAppointmentEntity;

public class TutoringAppointmentEntityDeserializerTest extends AbstractJsonJUnit5Test {

	private String json;

	@BeforeEach
	public void setUp() throws JsonProcessingException {
		TutoringAppointmentEntity tutoringAppointment = createAppointment();
		json = OBJECTMAPPER.writeValueAsString(tutoringAppointment);
		System.out.println(json);
	}

	@Test
	public void test() throws JsonParseException, IOException {
		JsonParserDelegate parser = new JsonParserDelegate(createJsonPaser(json));
		parser.setCodec(OBJECTMAPPER);
		DeserializerFactory deserializerFactory = new BeanDeserializerFactory(new DeserializerFactoryConfig());
		DeserializationContext context = new Impl(deserializerFactory);
		
		TutoringAppointmentEntityDeserializer deserializer = new TutoringAppointmentEntityDeserializer();
		deserializer.setCodec(OBJECTMAPPER);
		
		TutoringAppointmentEntity actualAppointment = deserializer.deserialize(parser, context);
		
		System.out.println(actualAppointment);
	}
}
