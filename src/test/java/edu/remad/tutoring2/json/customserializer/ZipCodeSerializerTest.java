package edu.remad.tutoring2.json.customserializer;

import java.io.IOException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;

import edu.remad.tutoring2.json.AbstractJsonJUnit5Test;
import edu.remad.tutoring2.models.ZipCodeEntity;

public class ZipCodeSerializerTest extends AbstractJsonJUnit5Test {

	private ZipCodeEntity zipCode;
	
	@BeforeEach
	@Override
	public void setUp() throws JsonProcessingException {
		super.setUp();
		zipCode = createZipCode();
	}
	
	@Test
	public void serializeTest() throws IOException {
		String expectedJson = "{\"id\":1,\"zipCode\":\"76246\",\"zipCodeLocation\":\"Hohleburg\",\"zipCodeCreationDate\":\"2024-03-10\"}";
		ZipCodeEntitySerializer serializer = new ZipCodeEntitySerializer();
		serializer.serialize(zipCode, createJsonGenerator(), OBJECTMAPPER.getSerializerProvider());
		
		Assertions.assertEquals(expectedJson, jsonWriter.toString());
	}
}
