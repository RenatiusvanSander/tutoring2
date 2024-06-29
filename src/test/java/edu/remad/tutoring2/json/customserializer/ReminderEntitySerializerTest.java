package edu.remad.tutoring2.json.customserializer;

import java.io.IOException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;

import edu.remad.tutoring2.json.AbstractJsonJUnit5Test;
import edu.remad.tutoring2.models.ReminderEntity;

public class ReminderEntitySerializerTest extends AbstractJsonJUnit5Test {

	private ReminderEntity reminder;

	@BeforeEach
	@Override
	public void setUp() throws JsonProcessingException {
		super.setUp();
		reminder = createReminder();
	}

	@Test
	public void serializeTest() throws IOException {
		String expectedJson = "{\"reminderNo\":10,\"reminderTutoringAppointment\":{\"tutoringAppointmentNo\":1,\"tutoringAppointmentUser\":{\"id\":0,\"username\":null,\"email\":null,\"password\":null,\"enabled\":false,\"roles\":[{\"id\":1,\"name\":\"Admin\",\"users\":[{}]}],\"firstName\":null,\"lastName\":null,\"gender\":null,\"cellPhone\":null,\"addresses\":\"null\",\"creationDate\":null},\"tutoringAppointmentDate\":\"2024-03-14 20:00\",\"tutoringAppointmentStartDateTime\":\"2024-03-14 20:00\",\"tutoringAppointmentEndDateTime\":\"2024-03-14 21:00\",\"serviceContractEntity\":{\"serviceContractNo\":41,\"serviceContractName\":\"Elektrotechnik Nachhilfe\",\"serviceContractDescription\":\"Nachhilfe in den Grundlagen der Elektrotechnik\",\"serviceContractCreationDate\":\"2024-05-11 00:00\"},\"isAccomplished\":false,\"tutoringAppointmentCreationDate\":\"2024-03-14 10:00\"},\"reminderUserEntity\":{\"id\":0,\"username\":null,\"email\":null,\"password\":null,\"enabled\":false,\"roles\":[{\"id\":1,\"name\":\"Admin\",\"users\":[{}]}],\"firstName\":null,\"lastName\":null,\"gender\":null,\"cellPhone\":null,\"addresses\":\"null\",\"creationDate\":null},\"reminderDate\":\"2024-03-10\",\"reminderCreationDate\":\"2024-05-11\"}";
		
		ReminderEntitySerializer serializer = new ReminderEntitySerializer();
		serializer.serialize(reminder, createJsonGenerator(jsonWriter), OBJECTMAPPER.getSerializerProvider());
		
		Assertions.assertEquals(expectedJson, jsonWriter.toString());
	}
}
