package edu.remad.tutoring2.services.impl;

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

import org.springframework.stereotype.Service;

import edu.remad.tutoring2.appconstants.SchedulerAppConstants;
import edu.remad.tutoring2.models.ReminderEntity;
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

	public ReminderServiceImpl(ReminderEntityRepository reminderEntityRepository, EmailService emailService) {
		this.reminderEntityRepository = reminderEntityRepository;
		this.emailService = emailService;
		scheduler = Executors.newScheduledThreadPool(SchedulerAppConstants.AMOUNT_OF_SCHEDULED_THREADS);
		scheduledTasks = new HashSet<>();

		initSchedulerPool();
	}

	private void initSchedulerPool() {
		ReminderServiceEmailSendTask emailSendTask = new ReminderServiceEmailSendTask(this, 0L, TimeUnit.DAYS, emailService);
		RunnableScheduledFuture<?> task = (RunnableScheduledFuture<?>) scheduler.scheduleAtFixedRate(emailSendTask, 0,
				1, TimeUnit.DAYS);
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
		// TODO Auto-generated method stub
		return null;
	}
}
