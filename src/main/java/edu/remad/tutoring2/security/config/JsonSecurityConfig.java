package edu.remad.tutoring2.security.config;

import org.springframework.boot.jackson.JsonComponentModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import edu.remad.tutoring2.json.customdeserializers.ServiceContractEntityDeserializer;
import edu.remad.tutoring2.json.customdeserializers.TutoringAppointmentEntityDeserializer;
import edu.remad.tutoring2.json.customserializer.ServiceContractEntitySerializer;
import edu.remad.tutoring2.json.customserializer.TutoringAppointmentEntitySerializer;
import edu.remad.tutoring2.models.ServiceContractEntity;
import edu.remad.tutoring2.models.TutoringAppointmentEntity;

/**
 * Use of JsonComponentModule allows annotatio {@code @JsonComponent} to register serializers and deserializers
 */
@Configuration
public class JsonSecurityConfig {

	@Bean
	public ObjectMapper objectMapper() {
		JsonComponentModule jsonComponentModule = new JsonComponentModule();
		jsonComponentModule.addDeserializer(ServiceContractEntity.class, new ServiceContractEntityDeserializer());
		jsonComponentModule.addDeserializer(TutoringAppointmentEntity.class, new TutoringAppointmentEntityDeserializer());
		jsonComponentModule.addSerializer(ServiceContractEntity.class, new ServiceContractEntitySerializer());
		jsonComponentModule.addSerializer(TutoringAppointmentEntity.class, new TutoringAppointmentEntitySerializer());		
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		mapper.registerModule(jsonComponentModule);
		mapper.registerModule(new JavaTimeModule());

		return mapper;
	}
}
