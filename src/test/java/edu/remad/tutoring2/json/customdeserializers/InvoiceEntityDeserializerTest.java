package edu.remad.tutoring2.json.customdeserializers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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
import edu.remad.tutoring2.models.InvoiceEntity;

public class InvoiceEntityDeserializerTest extends AbstractJsonJUnit5Test {

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
		json = OBJECTMAPPER.writeValueAsString(createInvoice());
	}

	@Test
	public void testDeserializer() throws JacksonException, IOException {
		JsonParserDelegate parser = new JsonParserDelegate(createJsonPaser(json));
		parser.setCodec(OBJECTMAPPER);
		DeserializerFactory deserializerFactory = new BeanDeserializerFactory(new DeserializerFactoryConfig());
		DeserializationContext context = new Impl(deserializerFactory);

		InvoiceEntityDeserializer deserializer = new InvoiceEntityDeserializer();
		deserializer.setCodec(OBJECTMAPPER);
		InvoiceEntity actualInvoice = deserializer.deserialize(parser, context);
		
		assertEquals(125l, actualInvoice.getInvoiceNo());
		assertNotNull(actualInvoice.getInvoiceCreationDate());
		assertNotNull(actualInvoice.getInvoiceDate());
		assertNotNull(actualInvoice.getInvoiceServiceContract());
		assertNotNull(actualInvoice.getInvoiceTutoringDate());
		assertNotNull(actualInvoice.getInvoiceUser());
		assertNotNull(actualInvoice.getPrice());
		assertEquals(1.0f, actualInvoice.getInvoiceTutoringHours());
	}
}
