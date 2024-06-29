package edu.remad.tutoring2.json.customserializer;

import java.io.IOException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;

import edu.remad.tutoring2.json.AbstractJsonJUnit5Test;
import edu.remad.tutoring2.models.Role;

public class RoleSerializerTest extends AbstractJsonJUnit5Test {

	private Role role;

	@BeforeEach
	public void setUp() throws JsonProcessingException {
		super.setUp();
		role = createRole();
	}
	
	@Test
	public void serializeTest() throws IOException {
		String expectedJson = "{\"id\":1,\"name\":\"Admin\",\"users\":[{\"id\":0,\"username\":null,\"email\":null,\"password\":null,\"enabled\":false,\"roles\":[],\"firstName\":null,\"lastName\":null,\"gender\":null,\"cellPhone\":null,\"addresses\":\"null\",\"creationDate\":null}]}"
				+ "";
		RoleSerializer serializer = new RoleSerializer(Role.class, OBJECTMAPPER);
		
		serializer.serialize(role, createJsonGenerator(), OBJECTMAPPER.getSerializerProvider());
		System.out.println(jsonWriter.toString());
		
		Assertions.assertEquals(expectedJson, jsonWriter.toString());
	}
}
