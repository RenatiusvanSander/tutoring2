package edu.remad.tutoring2.json.customdeserializers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.util.JsonParserDelegate;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.cfg.DeserializerFactoryConfig;
import com.fasterxml.jackson.databind.deser.BeanDeserializerFactory;
import com.fasterxml.jackson.databind.deser.DeserializerFactory;
import com.fasterxml.jackson.databind.deser.DefaultDeserializationContext.Impl;

import edu.remad.tutoring2.json.AbstractJsonJUnit5Test;
import edu.remad.tutoring2.models.ServiceContractEntity;

public class ServiceContractEntityDeserializerTest extends AbstractJsonJUnit5Test {

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
		json = OBJECTMAPPER.writeValueAsString(createServiceContractEntity());
	}
	
	@Test
	public void test() throws JsonParseException, IOException {
		JsonParserDelegate parser = new JsonParserDelegate(createJsonPaser(json));
		parser.setCodec(OBJECTMAPPER);
		DeserializerFactory deserializerFactory = new BeanDeserializerFactory(new DeserializerFactoryConfig());
		DeserializationContext context = new Impl(deserializerFactory);
		
		ServiceContractEntityDeserializer deserializer = new ServiceContractEntityDeserializer();
		deserializer.setCodec(OBJECTMAPPER);
		
		ServiceContractEntity actualServiceContract = deserializer.deserialize(parser, context);
		assertEquals(41l, actualServiceContract.getServiceContractNo());
		assertEquals("Elektrotechnik Nachhilfe", actualServiceContract.getServiceContractName());
		assertEquals("Nachhilfe in den Grundlagen der Elektrotechnik", actualServiceContract.getServiceContractDescription());
		assertNotNull(actualServiceContract.getServiceContractCreationDate());
	}

}
