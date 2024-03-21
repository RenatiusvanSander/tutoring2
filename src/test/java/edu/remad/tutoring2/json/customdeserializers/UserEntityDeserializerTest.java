package edu.remad.tutoring2.json.customdeserializers;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.util.JsonParserDelegate;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.cfg.DeserializerFactoryConfig;
import com.fasterxml.jackson.databind.deser.BeanDeserializerFactory;
import com.fasterxml.jackson.databind.deser.DefaultDeserializationContext.Impl;
import com.fasterxml.jackson.databind.deser.DeserializerFactory;

import edu.remad.tutoring2.json.AbstractJsonJUnit5Test;

@SpringJUnitWebConfig(classes = { UserEntityDeserializerTest.Config.class })
public class UserEntityDeserializerTest extends AbstractJsonJUnit5Test {
	
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
		json = OBJECTMAPPER.writeValueAsString(createUser());
	}
	
	@Test
	public void test() throws JsonParseException, IOException {
		JsonParserDelegate parser = new JsonParserDelegate(createJsonPaser(json));
		parser.setCodec(OBJECTMAPPER);
		DeserializerFactory deserializerFactory = new BeanDeserializerFactory(new DeserializerFactoryConfig());
		DeserializationContext context = new Impl(deserializerFactory);
		
		UserEntityDeserializer deserializer = new UserEntityDeserializer();
		deserializer.setCodec(OBJECTMAPPER);
		System.out.println(json);
		
		deserializer.deserialize(parser, context);
	}
	
	private JsonParser createJsonPaser(String content) throws JsonParseException, IOException {
		JsonFactory factory = new JsonFactory();
		JsonParser jsonParser = factory.createParser(content);
		
		return jsonParser;
	}
}
