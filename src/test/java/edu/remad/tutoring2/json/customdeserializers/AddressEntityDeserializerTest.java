package edu.remad.tutoring2.json.customdeserializers;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.util.JsonParserDelegate;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.cfg.DeserializerFactoryConfig;
import com.fasterxml.jackson.databind.deser.BeanDeserializerFactory;
import com.fasterxml.jackson.databind.deser.DeserializerFactory;
import com.fasterxml.jackson.databind.deser.DefaultDeserializationContext.Impl;

import edu.remad.tutoring2.json.AbstractJsonJUnit5Test;
import edu.remad.tutoring2.models.AddressEntity;

public class AddressEntityDeserializerTest extends AbstractJsonJUnit5Test {

	private String json;

	@Configuration
	static class Config {
		@Bean
		public ObjectMapper objectMapper() {
			return OBJECTMAPPER;
		}
	}

	@BeforeEach
	public void setUp() throws JsonProcessingException {
		json = OBJECTMAPPER.writeValueAsString(createAddress());
	}

	@Test
	public void testDeserializer() throws JacksonException, IOException {
		JsonParserDelegate parser = new JsonParserDelegate(createJsonPaser(json));
		parser.setCodec(OBJECTMAPPER);
		DeserializerFactory deserializerFactory = new BeanDeserializerFactory(new DeserializerFactoryConfig());
		DeserializationContext context = new Impl(deserializerFactory);

		AddressEntityDeserializer deserializer = new AddressEntityDeserializer(OBJECTMAPPER);
		deserializer.setCodec(OBJECTMAPPER);
		AddressEntity actualAddress = deserializer.deserialize(parser, context);

		System.out.println(actualAddress);
	}
}
