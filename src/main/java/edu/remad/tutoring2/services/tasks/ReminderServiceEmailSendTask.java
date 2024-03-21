package edu.remad.tutoring2.services.tasks;

import java.util.concurrent.Delayed;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.RunnableScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import edu.remad.tutoring2.services.ReminderService;

public class ReminderServiceEmailSendTask implements RunnableScheduledFuture<Boolean> {

	private long delay;

	private TimeUnit unit;

	private boolean isToCancel;

	private boolean isCancelled;

	private boolean isDone = false;

	private Boolean get = Boolean.FALSE;

	private final ReminderService reminderService;

	public ReminderServiceEmailSendTask(ReminderService reminderService, long delay, TimeUnit unit) {
		this.reminderService = reminderService;
		delay = unit.convert(delay, unit);
	}

	@Override
	public void run() {
		boolean hasToRun = true;
		while (!isToCancel && hasToRun) {
			System.out.println("ReminderTask has run!");
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
