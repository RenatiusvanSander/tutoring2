package edu.remad.tutoring2.controllers;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import edu.remad.tutoring2.models.TutoringAppointmentEntity;

public class TutoringAppointmentControllerTest extends AbstractJunit5Test {
	
	@Test
	public void validateTest() {
		TutoringAppointmentEntity appointment = createAppointment();
		
		TutoringAppointmentController controller = new TutoringAppointmentController(null, null);
		
		assertNotNull(controller);
	}
}
