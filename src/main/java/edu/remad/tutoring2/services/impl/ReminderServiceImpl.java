package edu.remad.tutoring2.services.impl;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import org.springframework.stereotype.Service;

import edu.remad.tutoring2.appconstants.SchedulerAppConstants;
import edu.remad.tutoring2.models.ReminderEntity;
import edu.remad.tutoring2.repositories.ReminderEntityRepository;
import edu.remad.tutoring2.services.ReminderService;

@Service
public class ReminderServiceImpl implements ReminderService {

	private ReminderEntityRepository reminderEntityRepository;

	private ScheduledExecutorService scheduler;

	public ReminderServiceImpl(ReminderEntityRepository reminderEntityRepository) {
		this.reminderEntityRepository = reminderEntityRepository;
		scheduler = Executors.newScheduledThreadPool(SchedulerAppConstants.AMOUNT_OF_SCHEDULED_THREADS);
		
		initSchedulerPool();
	}

	private void initSchedulerPool() {
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
		
		if(!remindersToClean.isEmpty()) {
			reminderEntityRepository.deleteAll(remindersToClean);
			isCleaned = true;
		}
		
		return isCleaned;
	}
}
