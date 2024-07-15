package edu.remad.tutoring2.json.customserializer;

import java.io.IOException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;

import edu.remad.tutoring2.json.AbstractJsonJUnit5Test;
import edu.remad.tutoring2.models.PriceEntity;

public class PriceEntitySerializerTest extends AbstractJsonJUnit5Test {
	
	private PriceEntity price;

	@BeforeEach
	@Override
	public void setUp() throws JsonProcessingException {
		super.setUp();
		price = createPrice();
	}
	
	@Test
	public void testSerialize() throws IOException {
		String expectedJson = "{\"id\":3,\"user\":{\"id\":3,\"username\":\"mustermann\",\"email\":\"maxmustermann@nirgendwo.de\",\"password\":\"MusterCity\",\"enabled\":true,\"roles\":[{\"id\":1,\"name\":\"Admin\",\"users\":[{\"id\":1}]}],\"firstName\":\"Max\",\"lastName\":\"Mustermann\",\"gender\":\"male\",\"cellPhone\":\"+4953535343435747\",\"addresses\":\"[{\\\"id\\\":0,\\\"addressStreet\\\":\\\"Hohlenbarg\\\",\\\"addressHouseNo\\\":\\\"24\\\",\\\"addressZipCode\\\":{\\\"id\\\":1,\\\"zipCode\\\":\\\"76246\\\",\\\"zipCodeLocation\\\":\\\"Hohleburg\\\",\\\"zipCodeCreationDate\\\":\\\"2024-03-10\\\"}}]\",\"creationDate\":\"2022-03-14\"},\"price\":12.44999980926513671875,\"serviceContract\":{\"serviceContractNo\":41,\"serviceContractName\":\"Elektrotechnik Nachhilfe\",\"serviceContractDescription\":\"Nachhilfe in den Grundlagen der Elektrotechnik\",\"serviceContractCreationDate\":\"2024-05-11 00:00\"}}";
		
		PriceEntitySerializer serializer = new PriceEntitySerializer(PriceEntity.class, OBJECTMAPPER);
		serializer.serialize(price, createJsonGenerator(), OBJECTMAPPER.getSerializerProvider());
		
		System.out.println(jsonWriter.toString());
		Assertions.assertEquals(expectedJson, jsonWriter.toString());
	}
}
