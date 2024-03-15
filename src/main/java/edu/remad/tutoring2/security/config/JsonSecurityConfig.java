package edu.remad.tutoring2.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import edu.remad.tutoring2.json.customdeserializers.ServiceContractEntityDeserializer;
import edu.remad.tutoring2.json.customdeserializers.TutoringAppointmentEntityDeserializer;
import edu.remad.tutoring2.json.customserializer.ServiceContractEntitySerializer;
import edu.remad.tutoring2.json.customserializer.TutoringAppointmentEntitySerializer;
import edu.remad.tutoring2.models.ServiceContractEntity;
import edu.remad.tutoring2.models.TutoringAppointmentEntity;

@Configuration
public class JsonSecurityConfig {

	@Bean
	public ObjectMapper objectMapper() {
		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		SimpleModule module = new SimpleModule();
		module.addDeserializer(ServiceContractEntity.class, new ServiceContractEntityDeserializer());
		module.addDeserializer(TutoringAppointmentEntity.class, new TutoringAppointmentEntityDeserializer());
		module.addSerializer(ServiceContractEntity.class, new ServiceContractEntitySerializer());
		module.addSerializer(TutoringAppointmentEntity.class, new TutoringAppointmentEntitySerializer());
		mapper.registerModule(module);

		return mapper;
	}
}
