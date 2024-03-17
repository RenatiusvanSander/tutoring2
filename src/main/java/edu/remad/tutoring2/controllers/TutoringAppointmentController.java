package edu.remad.tutoring2.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.remad.tutoring2.models.TutoringAppointmentEntity;
import edu.remad.tutoring2.services.TutoringAppointmentService;

@RestController
@RequestMapping("/api/tutoring-appointments")
public class TutoringAppointmentController {
	
	private final TutoringAppointmentService tutoringAppointmentService;
	
	@Autowired
	public TutoringAppointmentController(TutoringAppointmentService tutoringAppointmentService) {
		this.tutoringAppointmentService = tutoringAppointmentService;
	}

	@PostMapping(value = "/create-tutoring-appointment", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<TutoringAppointmentEntity> createTutoringAppointment(
			@RequestBody TutoringAppointmentEntity tutoringAppointment) {

		TutoringAppointmentEntity savedAppointment = null;
		if (tutoringAppointmentService.isValid(tutoringAppointment)) {
			savedAppointment = tutoringAppointmentService.save(tutoringAppointment);
		}

		return savedAppointment != null ? ResponseEntity.ok(savedAppointment)
				: ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
}
