package edu.remad.tutoring2.controllers;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import edu.remad.tutoring2.models.TutoringAppointmentEntity;

public class TutoringAppointmentControllerTest extends AbstractControllerTest {
	
	@Test
	public void validateTest() {
		TutoringAppointmentEntity appointment = createAppointment();
		
		TutoringAppointmentController controller = new TutoringAppointmentController(null);
		boolean booleanResult = controller.isValid(appointment);
		
		assertTrue(booleanResult);
	}
}
