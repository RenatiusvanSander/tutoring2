package edu.remad.tutoring2.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.remad.tutoring2.models.TutoringAppointmentEntity;
import edu.remad.tutoring2.services.ReminderService;
import edu.remad.tutoring2.services.TutoringAppointmentService;

@RestController
@RequestMapping("/api/tutoring-appointments")
public class TutoringAppointmentController {

	private final TutoringAppointmentService tutoringAppointmentService;
	
	private final ReminderService reminderService;

	@Autowired
	public TutoringAppointmentController(TutoringAppointmentService tutoringAppointmentService, ReminderService reminderService) {
		this.tutoringAppointmentService = tutoringAppointmentService;
		this.reminderService = reminderService;
	}

	@PostMapping(value = "/create-tutoring-appointment", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<TutoringAppointmentEntity> createTutoringAppointment(
			@RequestBody TutoringAppointmentEntity tutoringAppointment) {
		TutoringAppointmentEntity savedAppointment = tutoringAppointmentService.save(tutoringAppointment);
		
		if(savedAppointment != null) {
			reminderService.saveReminder(savedAppointment);
		}

		return savedAppointment != null ? ResponseEntity.ok(savedAppointment)
				: ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

	@DeleteMapping(value = "/delete-tutoring-appointment/delete-tutoring-appointment", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<TutoringAppointmentEntity> deleteTutoringAppointment(
			@RequestBody TutoringAppointmentEntity tutoringAppointment) {
		TutoringAppointmentEntity deletedAppointment = tutoringAppointmentService.delete(tutoringAppointment);
		
		if(deletedAppointment != null) {
			reminderService.deleteReminderByTutoringAppointment(deletedAppointment);
		}

		return deletedAppointment != null ? ResponseEntity.ok(deletedAppointment)
				: ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

	@DeleteMapping(value = "/delete-tutoring-appointment/delete-tutoring-appointments", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<TutoringAppointmentEntity>> deleteTutoringAppointments(
			@RequestBody List<TutoringAppointmentEntity> tutoringAppointments) {
		List<TutoringAppointmentEntity> deletedAppointments = tutoringAppointmentService
				.deleteMultiple(tutoringAppointments);
		
		if(!deletedAppointments.isEmpty()) {
			reminderService.deleteRemindersByTutoringAppointments(deletedAppointments);
		}

		return deletedAppointments != null ? ResponseEntity.ok(deletedAppointments)
				: ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

	@DeleteMapping(value = "/delete-tutoring-appointment/delete-by-id/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<TutoringAppointmentEntity> deleteTutoringAppointmentById(@PathVariable("id") Long id) {
		TutoringAppointmentEntity deletedAppointment = tutoringAppointmentService.deleteById(id);
		
		if(deletedAppointment != null) {
			reminderService.deleteReminderByTutoringAppointment(deletedAppointment);
		}

		return deletedAppointment != null ? ResponseEntity.ok(deletedAppointment)
				: ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

	@DeleteMapping(value = "/delete-tutoring-appointment/delete-by-ids", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<TutoringAppointmentEntity>> deleteTutoringAppointmentByIds(@RequestBody List<Long> ids) {
		List<TutoringAppointmentEntity> deletedAppointments = tutoringAppointmentService.deleteByIds(ids);
		
		if(!deletedAppointments.isEmpty()) {
			reminderService.deleteRemindersByTutoringAppointments(deletedAppointments);
		}

		return deletedAppointments != null ? ResponseEntity.ok(deletedAppointments)
				: ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

	@PutMapping(value = "/update-tutoring-appointment", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<TutoringAppointmentEntity> updateTutoringAppointment(
			@RequestBody TutoringAppointmentEntity appointment) {
		TutoringAppointmentEntity updatedAppointment = tutoringAppointmentService.update(appointment);
		
		if(updatedAppointment != null) {
			reminderService.updateReminder(updatedAppointment);
		}
		
		return updatedAppointment != null ? ResponseEntity.ok(updatedAppointment)
				: ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
}
