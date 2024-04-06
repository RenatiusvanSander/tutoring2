package edu.remad.tutoring2.json.customdeserializers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import edu.remad.tutoring2.json.AbstractJsonJUnit5Test;

@SpringJUnitWebConfig(classes = { TutoringAppointmentEntityDeserialierTest.Config.class })
public class TutoringAppointmentEntityDeserialierTest extends AbstractJsonJUnit5Test {
	private String json;
	
	@Configuration
	static class Config {
		@Bean
		public ObjectMapper objectMapper() {
			return OBJECTMAPPER;
		}
	}
	
	@BeforeEach
	public void setUp() throws JsonProcessingException {
		json = OBJECTMAPPER.writeValueAsString(createAppointment());
	}
	
	@Test
	public void emptyTest() {
		System.out.println(json);
	}
}
