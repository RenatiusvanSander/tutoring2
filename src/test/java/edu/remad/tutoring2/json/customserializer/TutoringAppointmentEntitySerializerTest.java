package edu.remad.tutoring2.json.customserializer;

import java.io.IOException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;

import edu.remad.tutoring2.json.AbstractJsonJUnit5Test;
import edu.remad.tutoring2.models.TutoringAppointmentEntity;

public class TutoringAppointmentEntitySerializerTest extends AbstractJsonJUnit5Test {

	private TutoringAppointmentEntity tutoringAppointment;

	@BeforeEach
	@Override
	public void setUp() throws JsonProcessingException {
		super.setUp();
		tutoringAppointment = createAppointment();
	}
	
	@Test
	public void serializeTest() throws IOException {
		String expectedJson = "{\"tutoringAppointmentNo\":1,\"tutoringAppointmentUser\":{\"id\":3,\"username\":\"mustermann\",\"email\":\"maxmustermann@nirgendwo.de\",\"password\":\"MusterCity\",\"enabled\":true,\"roles\":[{\"id\":1,\"name\":\"Admin\",\"users\":[{\"id\":null,\"username\":null,\"email\":null,\"password\":null,\"enabled\":null,\"roles\":[],\"tokens\":null,\"firstName\":null,\"lastName\":null,\"gender\":null,\"cellPhone\":null,\"addresses\":null,\"creationDate\":null}]}],\"tokens\":null,\"firstName\":\"Max\",\"lastName\":\"Mustermann\",\"gender\":\"male\",\"cellPhone\":\"+4953535343435747\",\"addresses\":[{\"id\":0,\"addressStreet\":\"Hohlenbarg\",\"addressHouseNo\":\"24\",\"addressZipCode\":{\"id\":1,\"zipCode\":\"76246\",\"zipCodeLocation\":\"Hohleburg\",\"zipCodeCreationDate\":[2024,3,10,21,0]}}],\"creationDate\":[2022,3,14,21,0]}";
		TutoringAppointmentEntitySerializer serializer = new TutoringAppointmentEntitySerializer();
		
		serializer.serialize(tutoringAppointment, createJsonGenerator(jsonWriter), OBJECTMAPPER.getSerializerProvider());
		
		Assertions.assertEquals(expectedJson, jsonWriter.toString());
	}
}
