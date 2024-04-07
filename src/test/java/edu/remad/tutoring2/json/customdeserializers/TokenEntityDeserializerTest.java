package edu.remad.tutoring2.json.customdeserializers;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.jackson.JsonComponent;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.util.JsonParserDelegate;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.cfg.DeserializerFactoryConfig;
import com.fasterxml.jackson.databind.deser.BeanDeserializerFactory;
import com.fasterxml.jackson.databind.deser.DeserializerFactory;
import com.fasterxml.jackson.databind.deser.DefaultDeserializationContext.Impl;

import edu.remad.tutoring2.json.AbstractJsonJUnit5Test;
import edu.remad.tutoring2.models.TokenEntity;

@JsonComponent
public class TokenEntityDeserializerTest extends AbstractJsonJUnit5Test {

	private String json;

	@BeforeEach
	public void setUp() throws JsonProcessingException {
		TokenEntity token = createToken();
		json = OBJECTMAPPER.writeValueAsString(token);
		System.out.println(json);
	}

	@Test
	public void test() throws JsonParseException, IOException {
		JsonParserDelegate parser = new JsonParserDelegate(createJsonPaser(json));
		parser.setCodec(OBJECTMAPPER);
		DeserializerFactory deserializerFactory = new BeanDeserializerFactory(new DeserializerFactoryConfig());
		DeserializationContext context = new Impl(deserializerFactory);
		
		TokenEntityDeserializer deserializer = new TokenEntityDeserializer(OBJECTMAPPER);
		deserializer.setCodec(OBJECTMAPPER);
		
		TokenEntity actualToken = deserializer.deserialize(parser, context);
		
		System.out.println(actualToken);
	}
}
