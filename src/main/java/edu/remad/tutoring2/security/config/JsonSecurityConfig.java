package edu.remad.tutoring2.security.config;

import org.springframework.boot.jackson.JsonComponentModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import edu.remad.tutoring2.json.customdeserializers.AddressEntityDeserializer;
import edu.remad.tutoring2.json.customdeserializers.RoleDeserializer;
import edu.remad.tutoring2.json.customdeserializers.ServiceContractEntityDeserializer;
import edu.remad.tutoring2.json.customdeserializers.TokenEntityDeserializer;
import edu.remad.tutoring2.json.customdeserializers.TutoringAppointmentEntityDeserializer;
import edu.remad.tutoring2.json.customdeserializers.UserEntityDeserializer;
import edu.remad.tutoring2.json.customdeserializers.ZipCodeEntityDeserializer;
import edu.remad.tutoring2.json.customserializer.ServiceContractEntitySerializer;
import edu.remad.tutoring2.json.customserializer.TutoringAppointmentEntitySerializer;
import edu.remad.tutoring2.models.AddressEntity;
import edu.remad.tutoring2.models.Role;
import edu.remad.tutoring2.models.ServiceContractEntity;
import edu.remad.tutoring2.models.TokenEntity;
import edu.remad.tutoring2.models.TutoringAppointmentEntity;
import edu.remad.tutoring2.models.UserEntity;
import edu.remad.tutoring2.models.ZipCodeEntity;

/**
 * Use of JsonComponentModule allows annotatio {@code @JsonComponent} to register serializers and deserializers
 */
@Configuration
public class JsonSecurityConfig {

	@Bean
	public ObjectMapper objectMapper() {
		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		mapper.registerModule(new JavaTimeModule());
		JsonComponentModule jsonComponentModule = new JsonComponentModule();
		jsonComponentModule.addDeserializer(ServiceContractEntity.class, new ServiceContractEntityDeserializer());
		jsonComponentModule.addDeserializer(TutoringAppointmentEntity.class, new TutoringAppointmentEntityDeserializer());
		jsonComponentModule.addDeserializer(UserEntity.class, new UserEntityDeserializer(mapper));
		jsonComponentModule.addDeserializer(AddressEntity.class, new AddressEntityDeserializer());
		jsonComponentModule.addDeserializer(ZipCodeEntity.class, new ZipCodeEntityDeserializer());
		jsonComponentModule.addDeserializer(TokenEntity.class, new TokenEntityDeserializer());
		jsonComponentModule.addDeserializer(Role.class, new RoleDeserializer());
		jsonComponentModule.addSerializer(ServiceContractEntity.class, new ServiceContractEntitySerializer());
		jsonComponentModule.addSerializer(TutoringAppointmentEntity.class, new TutoringAppointmentEntitySerializer());		
		mapper.registerModule(jsonComponentModule);

		return mapper;
	}
}
