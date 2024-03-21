package edu.remad.tutoring2.appconstants;

import edu.remad.tutoring2.services.ReminderService;

public final class SchedulerAppConstants {

	private SchedulerAppConstants() {
	}
	
	/**
	 * Amount of scheduled threads, which are allowed for {@link ReminderService}
	 */
	public static final int AMOUNT_OF_SCHEDULED_THREADS = 100;
}
