package edu.remad.tutoring2.controllers;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.remad.tutoring2.models.TutoringAppointmentEntity;

@RestController
@RequestMapping("/api/tutoring-appointments")
public class TutoringAppointmentController {

	@PostMapping(value = "/create-tutoring-appointment", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<TutoringAppointmentEntity> createTutoringAppointment(
			@RequestBody TutoringAppointmentEntity tutoringAppointment) {
		validate(tutoringAppointment);

		return ResponseEntity.ok(null);
	}

	public boolean validate(TutoringAppointmentEntity tutoringAppointment) {
		LocalDateTime dateAndTime = LocalDateTime.now();
		int mondayToFridayStartTime = 19;
		int mondayToFridayEndTime = 22;
		int saturdayToSundayStartTime = 10;
		int saturdayToSundayEndTime = 21;
		long minutes = ChronoUnit.MINUTES.between(tutoringAppointment.getTutoringAppointmentStartDateTime(),
				tutoringAppointment.getTutoringAppointmentEndDateTime());

		DayOfWeek day = dateAndTime.getDayOfWeek();
		switch (day) {
		case MONDAY:
		case TUESDAY:
		case WEDNESDAY:
		case THURSDAY:
		case FRIDAY: {

			if (tutoringAppointment.getTutoringAppointmentStartDateTime().getHour() >= mondayToFridayStartTime
					&& tutoringAppointment.getTutoringAppointmentEndDateTime().getHour() <= mondayToFridayEndTime
					&& minutes == 60) {
				return false;
			} else {
				return true;
			}
		}

		case SATURDAY:
		case SUNDAY: {
			if (tutoringAppointment.getTutoringAppointmentStartDateTime().getHour() >= saturdayToSundayStartTime
					&& tutoringAppointment.getTutoringAppointmentEndDateTime().getHour() <= saturdayToSundayEndTime
					&& minutes == 60) {
				return false;
			} else {
				return true;
			}
		}
		}

		return false;
	}
}
