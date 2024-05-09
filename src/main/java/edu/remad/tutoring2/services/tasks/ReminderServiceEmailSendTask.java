package edu.remad.tutoring2.services.tasks;

import java.io.IOException;
import java.time.LocalDate;
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

import edu.remad.ical4jbuilder.utilities.InterchangeCalendarUtilities;
import edu.remad.tutoring2.appconstants.InterchangeCalendarAppConstants;
import edu.remad.tutoring2.appconstants.TemplateAppConstants;
import edu.remad.tutoring2.appconstants.TimeAppConstants;
import edu.remad.tutoring2.models.ReminderEntity;
import edu.remad.tutoring2.services.EmailService;
import edu.remad.tutoring2.services.ReminderService;
import freemarker.template.TemplateException;

public class ReminderServiceEmailSendTask implements RunnableScheduledFuture<Boolean> {

	private final Map<ReminderEntity, byte[]> interchangeCalendars;

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
		this.interchangeCalendars = new HashMap<>();
		this.reminderService = reminderService;
		this.emailService = emailService;
		delay = unit.convert(delay, unit);
	}

	@Override
	public void run() {
		boolean hasToRun = true;

		while (!isToCancel && hasToRun) {
			List<ReminderEntity> reminders = reminderService.getAllRemindersOfCurrentDate(LocalDate.now().atStartOfDay());
			List<byte[]> calendarFiles = InterchangeCalendarUtilities.createInterchangeCalendarFile(reminders);

			if (reminders.size() == calendarFiles.size()) {
				int i = 0;
				for (ReminderEntity reminder : reminders) {
					interchangeCalendars.put(reminder, calendarFiles.get(i));
					i++;
				}
			}

			try {
				if (!interchangeCalendars.isEmpty()) {
					for (Map.Entry<ReminderEntity, byte[]> entry : interchangeCalendars.entrySet()) {
						ReminderEntity reminder = entry.getKey();
						String subject = TemplateAppConstants.REMINDER_EMAIL_VALUE_SUBJECT_TEXT
								+ reminder.getReminderDate().format(TimeAppConstants.LOCAL_DATE_TIME_FORMATTER);
						Map<String, Object> templateModel = generateTemplateModel(reminder, subject);
						emailService.sendMessageWithAttachmentUsingFreemarkerTemplate(reminder.getReminderUserEntity().getEmail(),
								subject, TemplateAppConstants.TEMPLATE_REMINDER_SERVICE_EMAIL_SEND_TASK, templateModel, entry.getValue(), InterchangeCalendarAppConstants.INTERCHANGE_CALENDAR_FILENAME_WITH_EXTENSION);
					}
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
		templateModel.put(TemplateAppConstants.REMINDER_EMAIL_ATTRIBUTE_SUBJECT, subject);
		templateModel.put(TemplateAppConstants.REMINDER_EMAIL_ATTRIBUTE_USERNAME,
				reminder.getReminderUserEntity().getUsername());
		templateModel.put(TemplateAppConstants.REMINDER_EMAIL_ATTRIBUTE_START_TIME,
				reminder.getReminderTutoringAppointment().getTutoringAppointmentStartDateTime()
						.format(TimeAppConstants.TIME_FORMATTER));
		templateModel.put(TemplateAppConstants.REMINDER_EMAIL_ATTRIBUTE_END_TIME,
				reminder.getReminderTutoringAppointment().getTutoringAppointmentEndDateTime()
						.format(TimeAppConstants.TIME_FORMATTER));
		templateModel.put(TemplateAppConstants.REMINDER_EMAIL_ATTRIBUTE_TUTORING_DATE, reminder
				.getReminderTutoringAppointment().getTutoringAppointmentDate().format(TimeAppConstants.DATE_FORMATTER));

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
		return get;
	}

	@Override
	public Boolean get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
		return get;
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
