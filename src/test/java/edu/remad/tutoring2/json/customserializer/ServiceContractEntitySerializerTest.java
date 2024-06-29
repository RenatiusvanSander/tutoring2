package edu.remad.tutoring2.json.customserializer;

import java.io.IOException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;

import edu.remad.tutoring2.json.AbstractJsonJUnit5Test;
import edu.remad.tutoring2.models.ServiceContractEntity;

public class ServiceContractEntitySerializerTest extends AbstractJsonJUnit5Test {

	private ServiceContractEntity serviceContract;

	@BeforeEach
	@Override
	public void setUp() throws JsonProcessingException {
		super.setUp();
		serviceContract = createServiceContractEntity();
	}

	@Test
	public void serializeTest() throws IOException {
		String expectedJson = "{\"serviceContractNo\":41,\"serviceContractName\":\"Elektrotechnik Nachhilfe\",\"serviceContractDescription\":\"Nachhilfe in den Grundlagen der Elektrotechnik\",\"serviceContractCreationDate\":\"2024-05-11 00:00\"}";
		
		ServiceContractEntitySerializer serializer = new ServiceContractEntitySerializer();
		serializer.serialize(serviceContract, createJsonGenerator(), OBJECTMAPPER.getSerializerProvider());

		System.out.println(jsonWriter.toString());
		Assertions.assertEquals(expectedJson, jsonWriter.toString());
	}
}
