package edu.remad.tutoring2.services.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.RunnableScheduledFuture;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import edu.remad.tutoring2.appconstants.SchedulerAppConstants;
import edu.remad.tutoring2.appconstants.TimeAppConstants;
import edu.remad.tutoring2.models.ReminderEntity;
import edu.remad.tutoring2.models.TutoringAppointmentEntity;
import edu.remad.tutoring2.repositories.ReminderEntityRepository;
import edu.remad.tutoring2.services.EmailService;
import edu.remad.tutoring2.services.ReminderService;
import edu.remad.tutoring2.services.tasks.ReminderServiceEmailSendTask;

@Service
public class ReminderServiceImpl implements ReminderService {

	private ReminderEntityRepository reminderEntityRepository;

	private EmailService emailService;

	private ScheduledExecutorService scheduler;

	private Set<RunnableScheduledFuture<?>> scheduledTasks;

	@Autowired
	public ReminderServiceImpl(ReminderEntityRepository reminderEntityRepository, EmailService emailService) {
		this.reminderEntityRepository = reminderEntityRepository;
		this.emailService = emailService;
		scheduler = Executors.newScheduledThreadPool(SchedulerAppConstants.AMOUNT_OF_SCHEDULED_THREADS);
		scheduledTasks = new HashSet<>();

		initSchedulerPool();
	}

	private void initSchedulerPool() {
		ReminderServiceEmailSendTask emailSendTask = new ReminderServiceEmailSendTask(this, 30L, TimeUnit.SECONDS,
				emailService);
		 RunnableScheduledFuture<?> task = (RunnableScheduledFuture<?>) scheduler.scheduleAtFixedRate(emailSendTask,
				calculateDelayTo5Am(), TimeAppConstants.TIME_PERIOD_DAY_IN_HOURS, TimeUnit.HOURS);
		scheduledTasks.add(task);
	}

	@Override
	public ReminderEntity saveReminder(ReminderEntity reminder) {
		ReminderEntity savedReminder = reminderEntityRepository.saveAndFlush(reminder);

		return savedReminder;
	}

	@Override
	public ReminderEntity updateReminder(ReminderEntity reminder) {
		Optional<ReminderEntity> reminderOptional = reminderEntityRepository.findById(reminder.getReminderNo());
		ReminderEntity reminderToUpdate = reminderOptional.isPresent() ? reminderOptional.get() : null;

		return reminderToUpdate;
	}

	@Override
	public ReminderEntity deleteReminderById(long id) {
		ReminderEntity loadedReminder = reminderEntityRepository.getReferenceById(id);

		if (loadedReminder != null) {
			reminderEntityRepository.delete(loadedReminder);
		}

		return loadedReminder;
	}

	@Override
	public List<ReminderEntity> deleteMultipleReminderByIds(List<Long> ids) {
		List<ReminderEntity> deletedReminders = reminderEntityRepository.findAllById(ids);
		reminderEntityRepository.deleteAll(deletedReminders);

		return deletedReminders;
	}

	@Override
	public ReminderEntity deleteReminder(ReminderEntity reminder) {
		ReminderEntity deletedReminder = reminderEntityRepository.getReferenceById(reminder.getReminderNo());

		if (deletedReminder != null) {
			reminderEntityRepository.delete(reminder);
		}

		return deletedReminder;
	}

	@Override
	public ReminderEntity gteReminderById(long id) {
		ReminderEntity loadedReminder = reminderEntityRepository.getReferenceById(id);

		return loadedReminder != null ? loadedReminder : null;
	}

	@Override
	public List<ReminderEntity> getAllRemindersOfCurrentDate(LocalDateTime currentDate) {
		List<ReminderEntity> remindersOfCurrentDate = Collections.emptyList();

		return remindersOfCurrentDate;
	}

	@Override
	public boolean clean(LocalDateTime date) {
		List<ReminderEntity> remindersToClean = reminderEntityRepository.findByReminderDate(date);
		boolean isCleaned = false;

		if (!remindersToClean.isEmpty()) {
			reminderEntityRepository.deleteAll(remindersToClean);
			isCleaned = true;
		}

		return isCleaned;
	}

	@Override
	public void cancelSpecificTaskType(final Class<?> clazz) {
		scheduledTasks.stream().filter(task -> task.getClass() == clazz).forEach(task -> task.cancel(true));
	}

	@Override
	public void cancelAllTasks() {
		for (RunnableScheduledFuture<?> task : scheduledTasks) {
			task.cancel(true);
		}
	}

	@Override
	public List<ReminderEntity> getAllReminderOfCurrentDate() {
		return null;
	}

	private long calculateDelayTo5Am() {
		LocalDateTime timeNow = LocalDateTime.now();
		int currentHour = timeNow.getHour();

		long delayInHours = 0;
		if (currentHour < TimeAppConstants.REMINDER_EMAIL_TIME_ON_5_O_CLOCK) {
			delayInHours = TimeAppConstants.REMINDER_EMAIL_TIME_ON_5_O_CLOCK - currentHour;
		} else {
			delayInHours = TimeAppConstants.REMINDER_EMAIL_TIME_ON_5_O_CLOCK + TimeAppConstants.TIME_PERIOD_DAY_IN_HOURS
					- currentHour;
		}

		return (long) delayInHours;
	}

	@Override
	public ReminderEntity saveReminder(TutoringAppointmentEntity appointment) {

		ReminderEntity reminder = new ReminderEntity();
		reminder.setReminderDate(appointment.getTutoringAppointmentDate().minusDays(1l));
		reminder.setReminderTutoringAppointment(appointment);
		reminder.setReminderCreationDate(LocalDate.now().atStartOfDay());
		reminder.setReminderUserEntity(appointment.getTutoringAppointmentUser());

		ReminderEntity savedReminder = reminderEntityRepository.saveAndFlush(reminder);

		return savedReminder != null ? savedReminder : null;
	}

	@Override
	public ReminderEntity getReminderByTutoringAppointmentEntity(TutoringAppointmentEntity appointmentEntity) {
		ReminderEntity reminderExample = new ReminderEntity();
		reminderExample.setReminderTutoringAppointment(appointmentEntity);
		Optional<ReminderEntity> optional = reminderEntityRepository.findOne(Example.of(reminderExample));
		ReminderEntity loadedReminder = optional.isPresent() ? optional.get() : null;

		return loadedReminder;
	}

	@Override
	public List<ReminderEntity> getRemindersByTutoringAppointments(List<TutoringAppointmentEntity> appointments) {
		List<Long> ids = appointments.stream().map(appointment -> appointment.getTutoringAppointmentNo())
				.collect(Collectors.toList());
		List<ReminderEntity> loadedReminders = reminderEntityRepository
				.findByReminderTutoringAppointment_TutoringAppointmentNoIn(ids);

		return loadedReminders;
	}

	@Override
	public void deleteReminderByTutoringAppointment(TutoringAppointmentEntity deleteAppointment) {
		ReminderEntity loadedReminder = getReminderByTutoringAppointmentEntity(deleteAppointment);
		deleteReminder(loadedReminder);
	}

	@Override
	public void deleteRemindersByTutoringAppointments(List<TutoringAppointmentEntity> tutoringAppointments) {
		List<ReminderEntity> lodedReminders = getRemindersByTutoringAppointments(tutoringAppointments);

		if (!lodedReminders.isEmpty()) {
			List<Long> ids = lodedReminders.stream().map(ReminderEntity::getReminderNo).collect(Collectors.toList());
			deleteMultipleReminderByIds(ids);
		}
	}

	@Override
	public ReminderEntity updateReminder(TutoringAppointmentEntity updatedAppointment) {
		ReminderEntity loadedReminder = getReminderByTutoringAppointmentEntity(updatedAppointment);
		loadedReminder.setReminderDate(updatedAppointment.getTutoringAppointmentDate());

		ReminderEntity updatedReminder = reminderEntityRepository.saveAndFlush(loadedReminder);

		return updatedReminder;
	}

	@Override
	public List<ReminderEntity> getAllReminders() {
		return reminderEntityRepository.findAll();
	}
}
