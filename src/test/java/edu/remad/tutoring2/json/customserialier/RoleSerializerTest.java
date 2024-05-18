package edu.remad.tutoring2.json.customserialier;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.remad.tutoring2.json.AbstractJsonJUnit5Test;
import edu.remad.tutoring2.json.customserializer.RoleSerializer;
import edu.remad.tutoring2.models.Role;

public class RoleSerializerTest extends AbstractJsonJUnit5Test {

	private Role role;

	@BeforeEach
	public void setUp() {
		role = createRole();
		System.out.println(role);
	}
	
	@Test
	public void serializeTest() throws IOException {
		Writer actualJsonWriter = new StringWriter();
		String expectedJson = "";
		RoleSerializer serializer = new RoleSerializer(Role.class, OBJECTMAPPER);
		
		serializer.serialize(role, createJsonGenerator(actualJsonWriter), OBJECTMAPPER.getSerializerProvider());
		System.out.println(actualJsonWriter.toString());
		
		Assertions.assertEquals(expectedJson, actualJsonWriter.toString());
	}
}
