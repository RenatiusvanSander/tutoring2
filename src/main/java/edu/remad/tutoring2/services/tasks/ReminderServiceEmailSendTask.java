package edu.remad.tutoring2.services.tasks;

import java.util.concurrent.Delayed;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.RunnableScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class ReminderServiceEmailSendTask implements RunnableScheduledFuture<Boolean> {

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean cancel(boolean mayInterruptIfRunning) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isCancelled() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isDone() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Boolean get() throws InterruptedException, ExecutionException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getDelay(TimeUnit unit) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int compareTo(Delayed o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isPeriodic() {
		// TODO Auto-generated method stub
		return false;
	}
}
