package edu.remad.tutoring2.services;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import edu.remad.tutoring2.models.TutoringAppointmentEntity;
import edu.remad.tutoring2.models.UserEntity;

@Transactional
public class TutoringAppointmentServiceTest extends AbstractJunit5ServiceJpaTest {

	@Autowired
	private TutoringAppointmentService tutoringAppointmentService;

	@BeforeEach
	public void clean() {
		tutoringAppointmentService.deleteAll();
	}

	@Test
	public void tutoringAppointmentServiceNotNulltest() {
		assertNotNull(tutoringAppointmentService);
	}

	@Test
	public void isDayFullWithAppointmentsShouldBeTrueTest() {
		LocalDateTime currentDate = LocalDate.now().atStartOfDay();
		for (int i = 0; i < 3; i++) {
			UserEntity user = new UserEntity();
			long id = i + 3;
			if (id == 4) {
				id = 6l;
			}
			user.setId(id);

			TutoringAppointmentEntity appointmentEntity = createAppointment();
			appointmentEntity.setTutoringAppointmentDate(currentDate);
			appointmentEntity.setTutoringAppointmentStartDateTime(currentDate.plusHours(i + 1));
			appointmentEntity.setTutoringAppointmentEndDateTime(currentDate.plusHours(i + 2));
			appointmentEntity.setTutoringAppointmentUser(user);
			tutoringAppointmentService.saveWithoutValidation(appointmentEntity);
		}

		TutoringAppointmentEntity appointment = createAppointment();
		appointment.setTutoringAppointmentDate(currentDate);
		boolean actualIsDayFull = tutoringAppointmentService.isDayFullWithAppointments(appointment);

		assertTrue(actualIsDayFull);
	}

	@Test
	public void isDayFullWithAppointmentsShouldBeFalseTest() {
		LocalDateTime currentDate = LocalDate.now().atStartOfDay();
		for (int i = 0; i < 2; i++) {
			UserEntity user = new UserEntity();
			long id = 0;
			if (i == 0) {
				id = 3l;
			} else if (i == 1) {
				id = 5;
			}
			user.setId(id);

			TutoringAppointmentEntity appointmentEntity = createAppointment();
			appointmentEntity.setTutoringAppointmentDate(currentDate);
			appointmentEntity.setTutoringAppointmentStartDateTime(currentDate.plusHours(i + 1));
			appointmentEntity.setTutoringAppointmentEndDateTime(currentDate.plusHours(i + 2));
			appointmentEntity.setTutoringAppointmentUser(user);
			tutoringAppointmentService.saveWithoutValidation(appointmentEntity);
		}

		TutoringAppointmentEntity appointment = createAppointment();
		appointment.setTutoringAppointmentDate(currentDate);
		boolean actualIsDayFull = tutoringAppointmentService.isDayFullWithAppointments(appointment);

		assertFalse(actualIsDayFull);
	}

	@Test
	public void isWeekFullWithAppointmensShouldBeTrueTest() {
		LocalDateTime currentDate = LocalDate.now().atStartOfDay();
		DayOfWeek dayOfWeek = currentDate.getDayOfWeek();
		long timeUntilEndOfWeek = 7l - dayOfWeek.getValue();
		long daysBackWeekStart = timeUntilEndOfWeek == 0 ? 6l : 7l - timeUntilEndOfWeek;

		for (int i = 0; i < 10; i++) {
			UserEntity user = new UserEntity();
			long id = 0;
			if (i % 2 == 0) {
				id = 5l;
			} else {
				id = 3l;
			}
			user.setId(id);

			TutoringAppointmentEntity appointmentEntity = createAppointment();
			if (i % 3 == 0) {
				appointmentEntity.setTutoringAppointmentDate(currentDate.plusDays(daysBackWeekStart));
			} else if (i % 2 == 0) {
				long days = daysBackWeekStart / 2l;
				appointmentEntity.setTutoringAppointmentDate(currentDate.plusDays(days));
			} else {
				appointmentEntity.setTutoringAppointmentDate(currentDate);
			}
			appointmentEntity.setTutoringAppointmentStartDateTime(currentDate.plusHours(i + 1));
			appointmentEntity.setTutoringAppointmentEndDateTime(currentDate.plusHours(i + 2));
			appointmentEntity.setTutoringAppointmentUser(user);
			tutoringAppointmentService.saveWithoutValidation(appointmentEntity);
		}

		TutoringAppointmentEntity appointment = createAppointment();
		appointment.setTutoringAppointmentDate(currentDate);
		boolean actualIsDayFull = tutoringAppointmentService.isWeekFullWithAppointmens(appointment);

		assertTrue(actualIsDayFull);
	}

	@Test
	public void saveShouldReturnNullTest() {
		LocalDateTime currentDate = LocalDate.now().atStartOfDay();
		DayOfWeek dayOfWeek = currentDate.getDayOfWeek();
		long timeUntilEndOfWeek = 7l - dayOfWeek.getValue();
		long daysBackWeekStart = timeUntilEndOfWeek == 0 ? 6l : 7l - timeUntilEndOfWeek;

		for (int i = 0; i < 10; i++) {
			UserEntity user = new UserEntity();
			long id = 0;
			if (i % 2 == 0) {
				id = 5l;
			} else {
				id = 3l;
			}
			user.setId(id);

			TutoringAppointmentEntity appointmentEntity = createAppointment();
			if (i % 3 == 0) {
				appointmentEntity.setTutoringAppointmentDate(currentDate.plusDays(daysBackWeekStart));
			} else if (i % 2 == 0) {
				long days = daysBackWeekStart / 2l;
				appointmentEntity.setTutoringAppointmentDate(currentDate.plusDays(days));
			} else {
				appointmentEntity.setTutoringAppointmentDate(currentDate);
			}
			appointmentEntity.setTutoringAppointmentStartDateTime(currentDate.plusHours(i + 1));
			appointmentEntity.setTutoringAppointmentEndDateTime(currentDate.plusHours(i + 2));
			appointmentEntity.setTutoringAppointmentUser(user);
			tutoringAppointmentService.saveWithoutValidation(appointmentEntity);
		}

		TutoringAppointmentEntity appointment = createAppointment();
		appointment.setTutoringAppointmentDate(currentDate);

		TutoringAppointmentEntity savedAppointment = tutoringAppointmentService.save(appointment);
		
		assertNull(savedAppointment);
	}
	
	@Test
	public void saveShouldReturnTutoringAppointmentTest() {
		LocalDateTime currentDate = LocalDate.now().atStartOfDay();
		DayOfWeek dayOfWeek = currentDate.getDayOfWeek();
		long timeUntilEndOfWeek = 7l - dayOfWeek.getValue();

		TutoringAppointmentEntity appointment1 = createAppointment();
		appointment1.setTutoringAppointmentDate(currentDate);
		appointment1.setTutoringAppointmentStartDateTime(currentDate.plusHours(2));
		appointment1.setTutoringAppointmentEndDateTime(currentDate.plusHours(3));
		appointment1.setTutoringAppointmentUser(createUser(3));
		tutoringAppointmentService.saveWithoutValidation(appointment1);
		
		TutoringAppointmentEntity appointment2 = createAppointment();
		appointment2.setTutoringAppointmentStartDateTime(currentDate.plusHours(5));
		appointment2.setTutoringAppointmentEndDateTime(currentDate.plusHours(6));
		appointment2.setTutoringAppointmentUser(createUser(5l));
		tutoringAppointmentService.saveWithoutValidation(appointment2);
		
		TutoringAppointmentEntity appointment3 = createAppointment();
		appointment3.setTutoringAppointmentCreationDate(currentDate.toLocalDate().plusDays(timeUntilEndOfWeek).atStartOfDay());
		appointment3.setTutoringAppointmentStartDateTime(currentDate.toLocalDate().plusDays(timeUntilEndOfWeek).atStartOfDay().plusDays(19));;
		appointment3.setTutoringAppointmentEndDateTime(currentDate.toLocalDate().plusDays(timeUntilEndOfWeek).atStartOfDay().plusDays(20));
		appointment3.setTutoringAppointmentUser(createUser(3l));
		tutoringAppointmentService.saveWithoutValidation(appointment3);
		
		TutoringAppointmentEntity appointment4 = createAppointment();
		appointment4.setTutoringAppointmentCreationDate(currentDate.toLocalDate().plusDays(timeUntilEndOfWeek).atStartOfDay());
		appointment4.setTutoringAppointmentStartDateTime(currentDate.toLocalDate().plusDays(timeUntilEndOfWeek).atStartOfDay().plusDays(21));;
		appointment4.setTutoringAppointmentEndDateTime(currentDate.toLocalDate().plusDays(timeUntilEndOfWeek).atStartOfDay().plusDays(22));
		appointment4.setTutoringAppointmentUser(createUser(5l));
		tutoringAppointmentService.saveWithoutValidation(appointment4);

		LocalDateTime dateToSet1 = LocalDate.now().atTime(20, 0);
		LocalDateTime dateToSet2 = LocalDate.now().atTime(21, 0);
		TutoringAppointmentEntity appointmentToSave = createAppointment();
		appointmentToSave.setTutoringAppointmentDate(currentDate);
		appointmentToSave.setTutoringAppointmentStartDateTime(dateToSet1);
		appointmentToSave.setTutoringAppointmentEndDateTime(dateToSet2);
		TutoringAppointmentEntity savedAppointment = tutoringAppointmentService.save(appointmentToSave);
		
		assertNotNull(savedAppointment);
	}
}
