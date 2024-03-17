package edu.remad.tutoring2.services;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class TutoringAppointmentServiceTest extends AbstractJunit5ServiceJpaTest{
	
	@Autowired
	private TutoringAppointmentService tutoringAppointmentService;
	
	@Test
	public void tutoringAppointmentServiceNotNulltest() {
		assertNotNull(tutoringAppointmentService);
	}
}
