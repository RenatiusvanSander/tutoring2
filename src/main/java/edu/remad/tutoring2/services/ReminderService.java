package edu.remad.tutoring2.services;

import java.time.LocalDateTime;
import java.util.List;

import edu.remad.tutoring2.models.ReminderEntity;
import edu.remad.tutoring2.models.TutoringAppointmentEntity;

public interface ReminderService {

	/**
	 * 
	 * @param reminder
	 * @return
	 */
	ReminderEntity saveReminder(ReminderEntity reminder);
	
	ReminderEntity saveReminder(TutoringAppointmentEntity appointment);
	
	ReminderEntity updateReminder(ReminderEntity reminder);
	
	ReminderEntity deleteReminderById(long id);
	
	List<ReminderEntity> deleteMultipleReminderByIds(List<Long> ids);
	
	ReminderEntity deleteReminder(ReminderEntity reminder);
	
	ReminderEntity gteReminderById(long id);
	
	List<ReminderEntity> getAllRemindersOfCurrentDate(LocalDateTime currentDate);
	
	boolean clean(LocalDateTime date);
	
	void cancelSpecificTaskType(Class<?> clazz);
	
	void cancelAllTasks();
	
	List<ReminderEntity> getAllReminderOfCurrentDate();
	
	ReminderEntity getReminderByTutoringAppointmentEntity(TutoringAppointmentEntity appointmentEntity);

	List<ReminderEntity> getRemindersByTutoringAppointments(List<TutoringAppointmentEntity> appointments);

	void deleteReminderByTutoringAppointment(TutoringAppointmentEntity deletedAppointment);

	void deleteRemindersByTutoringAppointments(List<TutoringAppointmentEntity> tutoringAppointments);

	ReminderEntity updateReminder(TutoringAppointmentEntity updatedAppointment);
	
	List<ReminderEntity> getAllReminders();
}
