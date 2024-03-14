package edu.remad.tutoring2.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import edu.remad.tutoring2.json.customdeserializers.ServiceContractEntityDeserializer;
import edu.remad.tutoring2.json.customserializer.ServiceContractEntitySerializer;
import edu.remad.tutoring2.models.ServiceContractEntity;

@Configuration
public class JsonSecurityConfig {

	@Bean
	public ObjectMapper objectMapper() {
		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		SimpleModule module = new SimpleModule();
		module.addDeserializer(ServiceContractEntity.class, new ServiceContractEntityDeserializer());
		module.addSerializer(ServiceContractEntity.class, new ServiceContractEntitySerializer());
		mapper.registerModule(module);

		return mapper;
	}
}
