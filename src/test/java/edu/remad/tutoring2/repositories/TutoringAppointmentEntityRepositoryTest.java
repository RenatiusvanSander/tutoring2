package edu.remad.tutoring2.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import edu.remad.tutoring2.models.TutoringAppointmentEntity;
import edu.remad.tutoring2.models.UserEntity;

public class TutoringAppointmentEntityRepositoryTest extends AbstractJunit5JpaTest {

	@Autowired
	private TutoringAppointmentEntityRepository tutoringAppointmentEntityRepository;
	
	@BeforeEach
	public void setUp() {
		for(int i = 0; i < 3; i++) {
			UserEntity user = new UserEntity();
			long id = i + 3;
			if(id == 4) {
				id = 5l;
			}
			user.setId(id);
			
			TutoringAppointmentEntity appointmentEntity = createAppointment();
			appointmentEntity.setTutoringAppointmentUser(user);
			tutoringAppointmentEntityRepository.saveAndFlush(appointmentEntity);
		}
	}
	
	@Test
	public void findByTutoringAppointmentDateTest() {
		LocalDateTime currentDate = LocalDate.now().atStartOfDay();
		for(int i = 0; i < 3; i++) {
			UserEntity user = new UserEntity();
			long id = i + 3;
			if(id == 4) {
				id = 5l;
			}
			user.setId(id);
			
			TutoringAppointmentEntity appointmentEntity = createAppointment();
			appointmentEntity.setTutoringAppointmentDate(currentDate);
			appointmentEntity.setTutoringAppointmentStartDateTime(currentDate.plusHours(i + 1));
			appointmentEntity.setTutoringAppointmentEndDateTime(currentDate.plusHours(i + 2));
			appointmentEntity.setTutoringAppointmentUser(user);
			tutoringAppointmentEntityRepository.saveAndFlush(appointmentEntity);
		}
		
		List<TutoringAppointmentEntity> loadedAppointments = tutoringAppointmentEntityRepository.findByTutoringAppointmentDate(currentDate);
		
		assertEquals(3, loadedAppointments.size());
	}

	@Test
	public void saveTest() {
		TutoringAppointmentEntity appointment = createAppointment();
		
		TutoringAppointmentEntity savedAppointment = tutoringAppointmentEntityRepository.saveAndFlush(appointment);
		
		System.out.println(savedAppointment);
	}
}
