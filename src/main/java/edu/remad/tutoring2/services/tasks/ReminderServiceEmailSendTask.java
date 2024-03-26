package edu.remad.tutoring2.services.tasks;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Delayed;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.RunnableScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import javax.mail.MessagingException;
import javax.naming.OperationNotSupportedException;

import edu.remad.tutoring2.appconstants.TemplateAppConstants;
import edu.remad.tutoring2.appconstants.TimeAppConstants;
import edu.remad.tutoring2.models.ReminderEntity;
import edu.remad.tutoring2.services.EmailService;
import edu.remad.tutoring2.services.ReminderService;
import freemarker.template.TemplateException;

public class ReminderServiceEmailSendTask implements RunnableScheduledFuture<Boolean> {

	private long delay;

	private TimeUnit unit;

	private boolean isToCancel;

	private boolean isCancelled;

	private boolean isDone = false;

	private Boolean get = Boolean.FALSE;

	private final ReminderService reminderService;

	private final EmailService emailService;

	public ReminderServiceEmailSendTask(ReminderService reminderService, long delay, TimeUnit unit,
			EmailService emailService) {
		this.reminderService = reminderService;
		this.emailService = emailService;
		delay = unit.convert(delay, unit);
	}

	@Override
	public void run() {
		boolean hasToRun = true;
		
		while (!isToCancel && hasToRun) {
			List<ReminderEntity> reminders = reminderService.getAllRemindersOfCurrentDate(LocalDate.now().atStartOfDay());
			try {
				for (ReminderEntity reminder : reminders) {
					String subject = TemplateAppConstants.REMINDER_EMAIL_VALUE_SUBJECT_TEXT
							+ reminder.getReminderDate().format(TimeAppConstants.LOCAL_DATE_TIME_FORMATTER);
					Map<String, Object> templateModel = generateTemplateModel(reminder, subject);
					emailService.sendMessageUsingFreemarkerTemplate(reminder.getReminderUserEntity().getEmail(),
							TemplateAppConstants.TEMPLATE_REMINDER_SERVICE_EMAIL_SEND_TASK, subject, templateModel);
				}
			} catch (OperationNotSupportedException | IOException | TemplateException | MessagingException e) {
				throw new RuntimeException();
			}

			hasToRun = false;
		}

		if (isToCancel) {
			isCancelled = true;
		}

		isDone = true;

		if (isDone()) {
			get = Boolean.TRUE;
			isToCancel = false;
		}
	}

	private Map<String, Object> generateTemplateModel(ReminderEntity reminder, String subject) {
		Map<String, Object> templateModel = new HashMap<>();
		templateModel.put(TemplateAppConstants.REMINDER_EMAIL_VALUE_SUBJECT_TEXT, subject);
		templateModel.put(TemplateAppConstants.REMINDER_EMAIL_ATTRIBUTE_USERNAME,
				reminder.getReminderUserEntity().getUsername());
		templateModel.put(TemplateAppConstants.REMINDER_EMAIL_ATTRIBUTE_START_TIME,
				reminder.getReminderTutoringAppointment().getTutoringAppointmentStartDateTime()
						.format(TimeAppConstants.TIME_FORMATTER));
		templateModel.put(TemplateAppConstants.REMINDER_EMAIL_ATTRIBUTE_END_TIME,
				reminder.getReminderTutoringAppointment().getTutoringAppointmentEndDateTime()
						.format(TimeAppConstants.TIME_FORMATTER));
		
		return templateModel;
	}

	@Override
	public boolean cancel(boolean mayInterruptIfRunning) {
		if (mayInterruptIfRunning) {
			isToCancel = true;
			isCancelled = false;
		}

		return isToCancel;
	}

	@Override
	public boolean isCancelled() {
		return isCancelled;
	}

	@Override
	public boolean isDone() {
		return isDone;
	}

	@Override
	public Boolean get() throws InterruptedException, ExecutionException {
		return Boolean.valueOf(get.booleanValue());
	}

	@Override
	public Boolean get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
		return Boolean.valueOf(get.booleanValue());
	}

	@Override
	public long getDelay(TimeUnit unit) {
		return unit.convert(delay, unit);
	}

	public void setDelay(TimeUnit unit) {
		this.unit = unit;
		delay = unit.convert(delay, unit);
	}

	@Override
	public int compareTo(Delayed o) {
		if (o.getDelay(unit) == delay) {
			return 0;
		}

		if (delay < o.getDelay(unit)) {
			return -1;
		}

		return 1;
	}

	@Override
	public boolean isPeriodic() {
		return true;
	}
}
