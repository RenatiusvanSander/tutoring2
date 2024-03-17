package edu.remad.tutoring2.repositories;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.remad.tutoring2.models.TutoringAppointmentEntity;

@Repository
public interface TutoringAppointmentEntityRepository extends JpaRepository<TutoringAppointmentEntity, Long> {
	
	/**
	 * Finds tutoring appointment's date
	 * 
	 * @param date a date as instance of {@link LocalDateTime}
	 * @return all tutoring appointment dates
	 */
	default List<TutoringAppointmentEntity> findByTutoringAppointmentDate(LocalDateTime date) {
	    return findByTutoringAppointmentDateBetween(date.toLocalDate().atStartOfDay(), date.toLocalDate().plusDays(1).atStartOfDay());
	}

	/**
	 * Finds tutoring appointments of the date
	 * 
	 * @param atStartOfDay start of given day
	 * @param atEndOfDay end of given day
	 * @return all found tutoring appointments between start of day and end of day
	 */
	List<TutoringAppointmentEntity> findByTutoringAppointmentDateBetween(LocalDateTime atStartOfDay, LocalDateTime atEndOfDay);
}
