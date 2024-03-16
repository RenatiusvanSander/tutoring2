package edu.remad.tutoring2.services;

import java.util.List;

import edu.remad.tutoring2.models.TutoringAppointmentEntity;
import edu.remad.tutoring2.models.UserEntity;

public interface TutoringAppointmentService {
	
	/**
	 * Validates appointment. Start and end time have to forfill opening hours and only one hour.
	 * 
	 * @param appointment pupil's appointment for a lecture session, {@link TutoringAppointmentEntity}
	 * @return {@code true} tutoring appointment is valid to be saved. {@code false} appointment does not meet dependencies.
	 */
	boolean isValid(TutoringAppointmentEntity appointment);

	/**
	 * Validates the day is full with appointments
	 * 
	 * @param appointment pupil's appointment for a lecture session, {@link TutoringAppointmentEntity}
	 * @return {@code true} day is full with appointments. {@code false} day is not full with appointments.
	 */
	boolean isDayFullWithAppointments(TutoringAppointmentEntity appointment);
	
	/**
	 * Validates week is full.
	 * 
	 * @param appointment pupil's appointment for a lecture session, {@link TutoringAppointmentEntity}
	 * @return {@code true} means week is full. {@code false} means week is not full.
	 */
	boolean isWeekFullWithAppointmens(TutoringAppointmentEntity appointment);
	
	TutoringAppointmentEntity save(TutoringAppointmentEntity tutoringAppointment);
	
	List<TutoringAppointmentEntity> saveAll(List<TutoringAppointmentEntity> tutoringAppointments);
	
	TutoringAppointmentEntity getById(long id);
	
	List<TutoringAppointmentEntity> getByIds(List<Long> ids);
	
	List<TutoringAppointmentEntity> getAll();
	
	List<TutoringAppointmentEntity> getAllOfCurrentDate();
	
	List<TutoringAppointmentEntity> getAllOfCurrentWeek();
	
	List<TutoringAppointmentEntity> getAllOfCurrentMonth();
	
	List<TutoringAppointmentEntity> getAllForUser(UserEntity user);
	
	List<TutoringAppointmentEntity> getAllOfCurrentDateForUser(UserEntity user);
	
	List<TutoringAppointmentEntity> getAllOfCurrentWeekForUser(UserEntity user);
	
	List<TutoringAppointmentEntity> getAllOfCurrentMonthForUser(UserEntity user);
	
	TutoringAppointmentEntity delete(TutoringAppointmentEntity appointment);
	
	List<TutoringAppointmentEntity> deleteMultiple(List<TutoringAppointmentEntity> appointments);
	
	TutoringAppointmentEntity deleteById(long id);
	
	TutoringAppointmentEntity deleteByIds(List<Long> ids);
	
	TutoringAppointmentEntity update(TutoringAppointmentEntity appointment);
	
	List<TutoringAppointmentEntity> updatemultiple(List<TutoringAppointmentEntity> appointment);
}
