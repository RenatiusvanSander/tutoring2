package edu.remad.tutoring2.json;

import org.junit.jupiter.api.BeforeAll;
import org.springframework.boot.jackson.JsonComponentModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import edu.remad.tutoring2.controllers.AbstractJunit5Test;
import edu.remad.tutoring2.json.customdeserializers.AddressEntityDeserializer;
import edu.remad.tutoring2.json.customdeserializers.UserEntityDeserializer;
import edu.remad.tutoring2.json.customdeserializers.ZipCodeEntityDeserializer;
import edu.remad.tutoring2.models.AddressEntity;
import edu.remad.tutoring2.models.UserEntity;
import edu.remad.tutoring2.models.ZipCodeEntity;

public abstract class AbstractJsonJUnit5Test extends AbstractJunit5Test {

	protected static ObjectMapper OBJECTMAPPER;

	@BeforeAll
	protected static void createStaticMembers() {
		OBJECTMAPPER = new ObjectMapper();
		
		JsonComponentModule jsonComponentModule = new JsonComponentModule();
		jsonComponentModule.addDeserializer(ZipCodeEntity.class, new ZipCodeEntityDeserializer());
		jsonComponentModule.addDeserializer(AddressEntity.class, new AddressEntityDeserializer(OBJECTMAPPER));
		jsonComponentModule.addDeserializer(UserEntity.class, new UserEntityDeserializer());
		
		OBJECTMAPPER.registerModule(jsonComponentModule);
		OBJECTMAPPER.registerModule(new JavaTimeModule());
	}
}
