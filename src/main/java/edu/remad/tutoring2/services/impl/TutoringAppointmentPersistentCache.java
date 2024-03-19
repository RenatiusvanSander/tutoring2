package edu.remad.tutoring2.services.impl;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import edu.remad.tutoring2.models.TutoringAppointmentEntity;

@Component
public class TutoringAppointmentPersistentCache extends PersistentCache<TutoringAppointmentEntity> {

	public TutoringAppointmentPersistentCache() {
		super();
	}

	@Override
	void clean() {
	}

	public Set<TutoringAppointmentEntity> findAppointmentsForDate(final TutoringAppointmentEntity appointment) {
		return findAppointmentsForDate(appointment.getTutoringAppointmentDate());
	}

	public Set<TutoringAppointmentEntity> findAppointmentsForDate(final LocalDateTime appointmentDate) {
		final int day = appointmentDate.getDayOfYear();
		final Month month = appointmentDate.getMonth();
		final int year = appointmentDate.getYear();

		Set<TutoringAppointmentEntity> foundAppointments = this.items.values().stream()
				.filter(date -> date.getTutoringAppointmentDate().getDayOfYear() == day
						&& date.getTutoringAppointmentDate().getMonth().equals(month)
						&& date.getTutoringAppointmentDate().getYear() == year)
				.collect(Collectors.toSet());

		return foundAppointments;
	}

	public Set<TutoringAppointmentEntity> findAppointmentsForWeek(LocalDateTime appointmentDate) {
		final LocalDateTime startOfWeekDate = createStartOfWeekdate(appointmentDate);
		final LocalDateTime endOfWeekDate = createEndOfWeekDate(appointmentDate);
		Set<TutoringAppointmentEntity> matchingAppointments = items.values().stream()
				.filter(item -> (item.getTutoringAppointmentDate().isAfter(startOfWeekDate)
						|| item.getTutoringAppointmentDate().isEqual(startOfWeekDate))
						&& (item.getTutoringAppointmentDate().isBefore(endOfWeekDate)
								|| item.getTutoringAppointmentDate().isEqual(endOfWeekDate)))
				.collect(Collectors.toSet());

		return matchingAppointments;
	}

	public LocalDateTime createStartOfWeekdate(TutoringAppointmentEntity appointment) {
		return createStartOfWeekdate(appointment.getTutoringAppointmentDate());
	}

	public LocalDateTime createStartOfWeekdate(LocalDateTime appointment) {
		DayOfWeek currentDay = appointment.getDayOfWeek();
		LocalDateTime startOfWeekDate = appointment.toLocalDate()
				.minusDays(currentDay.equals(DayOfWeek.MONDAY) ? 0 : currentDay.getValue()).atStartOfDay();

		return startOfWeekDate;
	}

	public LocalDateTime createEndOfWeekDate(TutoringAppointmentEntity appointment) {
		return createEndOfWeekDate(appointment.getTutoringAppointmentDate());
	}

	public LocalDateTime createEndOfWeekDate(LocalDateTime appointment) {
		long intervalToEndOfWeek = 7l - appointment.getDayOfWeek().getValue();
		LocalDateTime endOfWeekDate = appointment.toLocalDate().plusDays(intervalToEndOfWeek).atStartOfDay()
				.plusHours(23l);

		return endOfWeekDate;
	}

	public List<TutoringAppointmentEntity> getAllAppointmentsForUSer(final long userId) {
		return items.values().stream().filter(item -> item.getTutoringAppointmentUser().getId().longValue() == userId)
				.collect(Collectors.toList());
	}

	public List<TutoringAppointmentEntity> getAllOfCurrentDateForUser(final long id, final LocalDateTime date) {
		LocalDateTime endOfDay = date.plus(23, ChronoUnit.HOURS).plus(59, ChronoUnit.MINUTES);
		Set<TutoringAppointmentEntity> matchingAppointments = items.values().stream()
				.filter(item -> (item.getTutoringAppointmentDate().isAfter(date)
						|| item.getTutoringAppointmentDate().isEqual(date))
						&& (item.getTutoringAppointmentDate().isBefore(endOfDay)
								|| item.getTutoringAppointmentDate().isEqual(endOfDay)))
				.filter(item -> item.getTutoringAppointmentUser().getId().longValue() == id)
				.collect(Collectors.toSet());

		return matchingAppointments.stream().collect(Collectors.toList());
	}
}
