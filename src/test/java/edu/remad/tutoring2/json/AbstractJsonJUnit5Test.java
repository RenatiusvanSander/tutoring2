package edu.remad.tutoring2.json;

import java.io.IOException;

import org.junit.jupiter.api.BeforeAll;
import org.springframework.boot.jackson.JsonComponentModule;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import edu.remad.tutoring2.controllers.AbstractJunit5Test;
import edu.remad.tutoring2.json.customdeserializers.AddressEntityDeserializer;
import edu.remad.tutoring2.json.customdeserializers.RoleDeserializer;
import edu.remad.tutoring2.json.customdeserializers.TokenEntityDeserializer;
import edu.remad.tutoring2.json.customdeserializers.TutoringAppointmentEntityDeserializer;
import edu.remad.tutoring2.json.customdeserializers.UserEntityDeserializer;
import edu.remad.tutoring2.json.customdeserializers.ZipCodeEntityDeserializer;
import edu.remad.tutoring2.models.AddressEntity;
import edu.remad.tutoring2.models.Role;
import edu.remad.tutoring2.models.TokenEntity;
import edu.remad.tutoring2.models.TutoringAppointmentEntity;
import edu.remad.tutoring2.models.UserEntity;
import edu.remad.tutoring2.models.ZipCodeEntity;

public abstract class AbstractJsonJUnit5Test extends AbstractJunit5Test {

	protected static ObjectMapper OBJECTMAPPER;

	@BeforeAll
	protected static void createStaticMembers() {
		OBJECTMAPPER = new ObjectMapper();
		
		JsonComponentModule jsonComponentModule = new JsonComponentModule();
		jsonComponentModule.addDeserializer(ZipCodeEntity.class, new ZipCodeEntityDeserializer(OBJECTMAPPER));
		jsonComponentModule.addDeserializer(AddressEntity.class, new AddressEntityDeserializer(OBJECTMAPPER));
		jsonComponentModule.addDeserializer(UserEntity.class, new UserEntityDeserializer(OBJECTMAPPER));
		jsonComponentModule.addDeserializer(Role.class, new RoleDeserializer(OBJECTMAPPER));
		jsonComponentModule.addDeserializer(TutoringAppointmentEntity.class, new TutoringAppointmentEntityDeserializer(OBJECTMAPPER));
		jsonComponentModule.addDeserializer(TokenEntity.class, new TokenEntityDeserializer(OBJECTMAPPER));
		
		OBJECTMAPPER.registerModule(jsonComponentModule);
		OBJECTMAPPER.registerModule(new JavaTimeModule());
	}
	
	protected JsonParser createJsonPaser(String content) throws JsonParseException, IOException {
		JsonFactory factory = new JsonFactory();
		JsonParser jsonParser = factory.createParser(content);
		
		return jsonParser;
	}
}
