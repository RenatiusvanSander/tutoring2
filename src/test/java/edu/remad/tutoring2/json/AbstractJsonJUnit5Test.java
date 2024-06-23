package edu.remad.tutoring2.json;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.jackson.JsonComponentModule;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import edu.remad.tutoring2.controllers.AbstractJunit5Test;
import edu.remad.tutoring2.json.customdeserializers.AddressEntityDeserializer;
import edu.remad.tutoring2.json.customdeserializers.ReminderEntityDeserializer;
import edu.remad.tutoring2.json.customdeserializers.RoleDeserializer;
import edu.remad.tutoring2.json.customdeserializers.ServiceContractEntityDeserializer;
import edu.remad.tutoring2.json.customdeserializers.TokenEntityDeserializer;
import edu.remad.tutoring2.json.customdeserializers.TutoringAppointmentEntityDeserializer;
import edu.remad.tutoring2.json.customdeserializers.UserEntityDeserializer;
import edu.remad.tutoring2.json.customdeserializers.ZipCodeEntityDeserializer;
import edu.remad.tutoring2.json.customserializer.AddressEntitySerializer;
import edu.remad.tutoring2.json.customserializer.ReminderEntitySerializer;
import edu.remad.tutoring2.json.customserializer.ServiceContractEntitySerializer;
import edu.remad.tutoring2.json.customserializer.TutoringAppointmentEntitySerializer;
import edu.remad.tutoring2.json.customserializer.UserEntitySerializer;
import edu.remad.tutoring2.json.customserializer.ZipCodeEntitySerializer;
import edu.remad.tutoring2.models.AddressEntity;
import edu.remad.tutoring2.models.ReminderEntity;
import edu.remad.tutoring2.models.Role;
import edu.remad.tutoring2.models.ServiceContractEntity;
import edu.remad.tutoring2.models.TokenEntity;
import edu.remad.tutoring2.models.TutoringAppointmentEntity;
import edu.remad.tutoring2.models.UserEntity;
import edu.remad.tutoring2.models.ZipCodeEntity;

public abstract class AbstractJsonJUnit5Test extends AbstractJunit5Test {

	protected static ObjectMapper OBJECTMAPPER;
	
	protected StringWriter jsonWriter;
	
	@BeforeEach
	protected void setUp() throws JsonProcessingException {
		jsonWriter = new StringWriter();
	}

	@BeforeAll
	protected static void createStaticMembers() {
		OBJECTMAPPER = new ObjectMapper();
		
		JsonComponentModule jsonComponentModule = new JsonComponentModule();
		jsonComponentModule.addDeserializer(ZipCodeEntity.class, new ZipCodeEntityDeserializer(OBJECTMAPPER));
		jsonComponentModule.addDeserializer(AddressEntity.class, new AddressEntityDeserializer(OBJECTMAPPER));
		jsonComponentModule.addDeserializer(UserEntity.class, new UserEntityDeserializer(OBJECTMAPPER));
		jsonComponentModule.addDeserializer(Role.class, new RoleDeserializer(OBJECTMAPPER));
		jsonComponentModule.addDeserializer(ServiceContractEntity.class, new ServiceContractEntityDeserializer(OBJECTMAPPER));
		jsonComponentModule.addDeserializer(TutoringAppointmentEntity.class, new TutoringAppointmentEntityDeserializer(OBJECTMAPPER));
		jsonComponentModule.addDeserializer(TokenEntity.class, new TokenEntityDeserializer(OBJECTMAPPER));
		jsonComponentModule.addDeserializer(ReminderEntity.class, new ReminderEntityDeserializer(OBJECTMAPPER));
		
		jsonComponentModule.addSerializer(new ZipCodeEntitySerializer(ZipCodeEntity.class, OBJECTMAPPER));
		jsonComponentModule.addSerializer(new AddressEntitySerializer(AddressEntity.class, OBJECTMAPPER));
		jsonComponentModule.addSerializer(new ReminderEntitySerializer());
		jsonComponentModule.addSerializer(new ServiceContractEntitySerializer());
		jsonComponentModule.addSerializer(new TutoringAppointmentEntitySerializer());
		jsonComponentModule.addSerializer(new UserEntitySerializer(UserEntity.class, OBJECTMAPPER));
		
		OBJECTMAPPER.registerModule(jsonComponentModule);
		OBJECTMAPPER.registerModule(new JavaTimeModule());
	}
	
	protected JsonParser createJsonPaser(String content) throws JsonParseException, IOException {
		JsonFactory factory = new JsonFactory();
		JsonParser jsonParser = factory.createParser(content);
		
		return jsonParser;
	}
	
	protected JsonGenerator createJsonGenerator(Writer jsonWriter) throws IOException {
		JsonGenerator jsonGenerator = new JsonFactory().createGenerator(jsonWriter);
	    jsonGenerator.setCodec(OBJECTMAPPER);
		
		return jsonGenerator;
	}
	
	protected JsonGenerator createJsonGenerator() throws IOException {
		JsonGenerator jsonGenerator = new JsonFactory().createGenerator(jsonWriter);
	    jsonGenerator.setCodec(OBJECTMAPPER);
		
		return jsonGenerator;
	}
}
