package edu.remad.tutoring2.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import edu.remad.tutoring2.json.customdeserializers.ServiceContractEntityDeserializer;
import edu.remad.tutoring2.models.ServiceContractEntity;

@Configuration
public class JsonSecurityConfig {

	@Bean
	public ObjectMapper objectMapper() {
		ObjectMapper mapper = new ObjectMapper();
		SimpleModule module = new SimpleModule();
		module.addDeserializer(ServiceContractEntity.class, new ServiceContractEntityDeserializer());
		mapper.registerModule(module);
		mapper.registerModule(new JavaTimeModule());
		
		return mapper;
	}
}
