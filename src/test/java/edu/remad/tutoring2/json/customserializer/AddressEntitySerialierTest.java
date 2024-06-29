package edu.remad.tutoring2.json.customserializer;

import java.io.IOException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;

import edu.remad.tutoring2.json.AbstractJsonJUnit5Test;
import edu.remad.tutoring2.models.AddressEntity;

public class AddressEntitySerialierTest extends AbstractJsonJUnit5Test {

	private AddressEntity address;

	@BeforeEach
	@Override
	public void setUp() throws JsonProcessingException {
		super.setUp();
		address = createAddress();
	}
	
	@Test
	public void serializeTest() throws IOException {
		String expectedJson = "{\"id\":0,\"addressStreet\":\"Hohlenbarg\",\"addressHouseNo\":\"24\",\"user\":{\"id\":0,\"username\":null,\"email\":null,\"password\":null,\"enabled\":false,\"roles\":[],\"firstName\":null,\"lastName\":null,\"gender\":null,\"cellPhone\":null,\"addresses\":\"null\",\"creationDate\":null},\"addressZipCode\":{\"id\":1,\"zipCode\":\"76246\",\"zipCodeLocation\":\"Hohleburg\",\"zipCodeCreationDate\":\"2024-03-10\"}";
		
		AddressEntitySerializer serializer = new AddressEntitySerializer();
		serializer.serialize(address, createJsonGenerator(jsonWriter), OBJECTMAPPER.getSerializerProvider());
		
		System.out.println(jsonWriter.toString());
		Assertions.assertEquals(expectedJson, jsonWriter.toString());
	}
}
