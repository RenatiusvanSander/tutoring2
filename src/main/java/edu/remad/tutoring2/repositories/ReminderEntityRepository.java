package edu.remad.tutoring2.repositories;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.remad.tutoring2.models.ReminderEntity;

@Repository
public interface ReminderEntityRepository extends JpaRepository<ReminderEntity, Long> {

	/**
	 * Finds reminders's date
	 * 
	 * @param date a date as instance of {@link LocalDateTime}
	 * @return all tutoring appointment date reminders
	 */
	default List<ReminderEntity> findByReminderDate(LocalDateTime date) {
	    return findByReminderDateBetween(date.toLocalDate().atStartOfDay(), date.toLocalDate().plusDays(1).atStartOfDay());
	}

	/**
	 * Finds reminders by the date
	 * 
	 * @param atStartOfDay start of given day
	 * @param atEndOfDay end of given day
	 * @return all found tutoring appointment reminders between start of day and end of day
	 */
	List<ReminderEntity> findByReminderDateBetween(LocalDateTime atStartOfDay, LocalDateTime atEndOfDay);

	/**
	 * Finds Reminders by tutoring appointment number / id.
	 * 
	 * @param tutoringAppointmentNo tutoring appoint's number
	 * @return found Reminders
	 */
	List<ReminderEntity> findByReminderTutoringAppointment_TutoringAppointmentNoIn(List<Long> tutoringAppointmentNo);
}
