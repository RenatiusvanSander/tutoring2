package edu.remad.tutoring2.tasks;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.RunnableScheduledFuture;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;

import edu.remad.tutoring2.services.tasks.ReminderServiceEmailSendTask;

public class RunnableScheduledFuturePeriodicExampleTest {

	@Test
	public void test() throws ExecutionException, InterruptedException {
		RunnableScheduledFuture<?> task = new ReminderServiceEmailSendTask(null, 0L, TimeUnit.DAYS);

	      ScheduledExecutorService es = Executors.newSingleThreadScheduledExecutor();
	      RunnableScheduledFuture<?> scheduleFuture =
	              (RunnableScheduledFuture<?>) es.scheduleAtFixedRate(task, 0, 1, TimeUnit.SECONDS);
	      System.out.println("remaining delay: " + scheduleFuture.getDelay(TimeUnit.MILLISECONDS));
	      System.out.println("periodic: " + scheduleFuture.isPeriodic());
	      TimeUnit.SECONDS.sleep(5);
	      scheduleFuture.cancel(true);
	      es.shutdown();
	  }
}
