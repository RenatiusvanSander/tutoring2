package edu.remad.tutoring2.services.impl;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.data.domain.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.remad.tutoring2.models.TutoringAppointmentEntity;
import edu.remad.tutoring2.models.UserEntity;
import edu.remad.tutoring2.repositories.TutoringAppointmentEntityRepository;
import edu.remad.tutoring2.services.TutoringAppointmentService;

@Service
public class TutoringAppointmentServiceImpl implements TutoringAppointmentService {

	private final TutoringAppointmentPersistentCache cache;

	private final TutoringAppointmentEntityRepository tutAppointmentEntityRepository;

	private static final int mondayToFridayStartTime = 19;
	private static final int mondayToFridayEndTime = 22;
	private static final int saturdayToSundayStartTime = 10;
	private static final int saturdayToSundayEndTime = 21;
	private static final int USERS_PER_WEEK = 2;
	private static final int MAX_APPOINTMENTS_PER_WEEK = 10;

	@Autowired
	public TutoringAppointmentServiceImpl(TutoringAppointmentPersistentCache cache,
			TutoringAppointmentEntityRepository tutoringAppointmentEntityRepository) {
		this.cache = cache;
		this.tutAppointmentEntityRepository = tutoringAppointmentEntityRepository;
	}

	@Override
	public boolean isValid(TutoringAppointmentEntity appointment) {
		LocalDateTime dateAndTime = LocalDateTime.now();
		long minutes = ChronoUnit.MINUTES.between(appointment.getTutoringAppointmentStartDateTime(),
				appointment.getTutoringAppointmentEndDateTime());

		DayOfWeek day = dateAndTime.getDayOfWeek();
		switch (day) {
		case MONDAY:
		case TUESDAY:
		case WEDNESDAY:
		case THURSDAY:
		case FRIDAY: {

			if (appointment.getTutoringAppointmentStartDateTime().getHour() >= mondayToFridayStartTime
					&& appointment.getTutoringAppointmentEndDateTime().getHour() <= mondayToFridayEndTime
					&& minutes == 60) {
				return true;
			} else {
				return false;
			}
		}
		case SATURDAY:
		case SUNDAY: {
			if (appointment.getTutoringAppointmentStartDateTime().getHour() >= saturdayToSundayStartTime
					&& appointment.getTutoringAppointmentEndDateTime().getHour() <= saturdayToSundayEndTime
					&& minutes == 60) {
				return true;
			} else {
				return false;
			}
		}
		}

		return false;
	}

	@Override
	public boolean isDayFullWithAppointments(TutoringAppointmentEntity appointment) {
		Set<TutoringAppointmentEntity> appointments = cache.findAppointmentsForDate(appointment);

		if (appointments.isEmpty()) {
			List<TutoringAppointmentEntity> laodedAppointments = tutAppointmentEntityRepository
					.findByTutoringAppointmentDate(appointment.getTutoringAppointmentDate());
			appointments = laodedAppointments.stream().collect(Collectors.toSet());
		}

		int workHours = receiveWorkHours(appointment.getTutoringAppointmentDate());
		int countedUsers = countAppointmentUsers(appointments, appointment.getTutoringAppointmentUser());

		if (countedUsers >= USERS_PER_WEEK) {
			return true;
		}

		if (appointments.size() < workHours) {
			return false;
		}

		return true;
	}

	private int countAppointmentUsers(Set<TutoringAppointmentEntity> appointments, final UserEntity requester) {
		boolean requesterFound = appointments.stream()
				.anyMatch(appointment -> appointment.getTutoringAppointmentUser().getId() == requester.getId());
		Set<UserEntity> users = appointments.stream().map(TutoringAppointmentEntity::getTutoringAppointmentUser)
				.collect(Collectors.toSet());

		return requesterFound ? users.size() - 1 : users.size();
	}

	private int receiveWorkHours(LocalDateTime date) {
		DayOfWeek dayOfWeek = date.getDayOfWeek();
		int workingHours = 0;

		switch (dayOfWeek) {
		case MONDAY:
		case TUESDAY:
		case WEDNESDAY:
		case THURSDAY:
		case FRIDAY:
			workingHours = mondayToFridayEndTime - mondayToFridayStartTime;
			break;
		case SATURDAY:
		case SUNDAY:
			workingHours = saturdayToSundayEndTime - saturdayToSundayStartTime;
			break;
		}

		return workingHours;
	}

	@Override
	public boolean isWeekFullWithAppointmens(TutoringAppointmentEntity appointment) {
		Set<TutoringAppointmentEntity> appointments = cache
				.findAppointmentsForWeek(appointment.getTutoringAppointmentDate());

		if (appointments.isEmpty()) {
			LocalDateTime startOfWeekDate = cache.createStartOfWeekdate(appointment);
			LocalDateTime endOfWeekDate = cache.createEndOfWeekDate(appointment);
			appointments = tutAppointmentEntityRepository
					.findByTutoringAppointmentDateBetween(startOfWeekDate, endOfWeekDate).stream()
					.collect(Collectors.toSet());

			if (!appointments.isEmpty()) {
				Map<Long, TutoringAppointmentEntity> mappedAppointment = appointments.stream().collect(
						Collectors.toMap(TutoringAppointmentEntity::getTutoringAppointmentNo, Function.identity()));
				cache.addAll(mappedAppointment);
			}
		}

		long countedUsers = countAppointmentUsers(appointments, appointment.getTutoringAppointmentUser());
		if (appointments.size() >= MAX_APPOINTMENTS_PER_WEEK || countedUsers >= USERS_PER_WEEK) {
			return true;
		}

		return false;
	}

	@Override
	public TutoringAppointmentEntity save(TutoringAppointmentEntity tutoringAppointment) {
		TutoringAppointmentEntity savedAppointment = null;

		if (validated(tutoringAppointment)) {
			savedAppointment = tutAppointmentEntityRepository.saveAndFlush(tutoringAppointment);
			cache.add(savedAppointment.getTutoringAppointmentNo(), savedAppointment);
		}

		return savedAppointment;
	}

	@Override // TODO rework
	public List<TutoringAppointmentEntity> saveAll(List<TutoringAppointmentEntity> tutoringAppointments) {
		List<TutoringAppointmentEntity> savedAppointments = tutAppointmentEntityRepository
				.saveAllAndFlush(tutoringAppointments);
		Map<Long, TutoringAppointmentEntity> mapOfAppointments = savedAppointments.stream()
				.collect(Collectors.toMap(TutoringAppointmentEntity::getTutoringAppointmentNo, Function.identity()));
		cache.addAll(mapOfAppointments);

		return savedAppointments;
	}

	@Override
	public TutoringAppointmentEntity getById(long id) {
		TutoringAppointmentEntity appointment = cache.get(id);

		if (appointment == null) {
			appointment = tutAppointmentEntityRepository.getReferenceById(id);

			if (appointment != null) {
				cache.add(appointment.getTutoringAppointmentNo(), appointment);
			}
		}

		return appointment;
	}

	@Override
	public List<TutoringAppointmentEntity> getByIds(List<Long> ids) {
		List<TutoringAppointmentEntity> appointments = cache.getByIds(ids);

		if (ids.size() != appointments.size()) {
			appointments = tutAppointmentEntityRepository.findAllById(ids);

			if (!appointments.isEmpty() && ids.size() == appointments.size()) {
				Map<Long, TutoringAppointmentEntity> mappedAppointments = appointments.stream().collect(
						Collectors.toMap(TutoringAppointmentEntity::getTutoringAppointmentNo, Function.identity()));
				cache.addAll(mappedAppointments);
			}
		}

		return appointments;
	}

	@Override
	public List<TutoringAppointmentEntity> getAll() {
		List<TutoringAppointmentEntity> storedAppointments = tutAppointmentEntityRepository.findAll();

		if (!storedAppointments.isEmpty() && storedAppointments.size() != cache.size()) {
			Map<Long, TutoringAppointmentEntity> mappedStoredAppointments = storedAppointments.stream().collect(
					Collectors.toMap(TutoringAppointmentEntity::getTutoringAppointmentNo, Function.identity()));
			cache.addAll(mappedStoredAppointments);
		}

		return storedAppointments;
	}

	@Override
	public List<TutoringAppointmentEntity> getAllOfCurrentDate() {
		LocalDateTime currentDate = LocalDate.now().atStartOfDay();
		Set<TutoringAppointmentEntity> currentDateAppointments = cache.findAppointmentsForDate(currentDate);

		return currentDateAppointments.stream().collect(Collectors.toList());
	}

	@Override
	public List<TutoringAppointmentEntity> getAllOfCurrentWeek() {
		LocalDateTime date = LocalDate.now().atStartOfDay();
		Set<TutoringAppointmentEntity> currentWeekAppointments = cache.findAppointmentsForWeek(date);

		return currentWeekAppointments.stream().collect(Collectors.toList());
	}

	@Override
	public List<TutoringAppointmentEntity> getAllOfCurrentMonth() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TutoringAppointmentEntity> getAllForUser(UserEntity user) {
		List<TutoringAppointmentEntity> usersAppointments = cache.getAllAppointmentsForUSer(user.getId());

		if (usersAppointments.isEmpty()) {
			TutoringAppointmentEntity appointment = new TutoringAppointmentEntity();
			appointment.setTutoringAppointmentUser(user);
			Example<TutoringAppointmentEntity> example = Example.of(appointment);
			usersAppointments = tutAppointmentEntityRepository.findAll(example);

			if (!usersAppointments.isEmpty()) {
				Map<Long, TutoringAppointmentEntity> mappedAppointments = usersAppointments.stream().collect(
						Collectors.toMap(TutoringAppointmentEntity::getTutoringAppointmentNo, Function.identity()));
				cache.addAll(mappedAppointments);
			}
		}

		return usersAppointments;
	}

	@Override
	public List<TutoringAppointmentEntity> getAllOfCurrentDateForUser(UserEntity user) {
		LocalDateTime date = LocalDate.now().atStartOfDay();
		List<TutoringAppointmentEntity> usersAppointments = cache.getAllOfCurrentDateForUser(user.getId(), date);

		if (usersAppointments.isEmpty()) {
			TutoringAppointmentEntity appointment = new TutoringAppointmentEntity();
			appointment.setTutoringAppointmentUser(user);
			Example<TutoringAppointmentEntity> example = Example.of(appointment);
			usersAppointments = tutAppointmentEntityRepository.findAll(example);

			if (!usersAppointments.isEmpty()) {
				Map<Long, TutoringAppointmentEntity> mappedAppointments = usersAppointments.stream().collect(
						Collectors.toMap(TutoringAppointmentEntity::getTutoringAppointmentNo, Function.identity()));
				cache.addAll(mappedAppointments);
			}
		}

		return usersAppointments;
	}

	@Override
	public List<TutoringAppointmentEntity> getAllOfCurrentWeekForUser(UserEntity user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TutoringAppointmentEntity> getAllOfCurrentMonthForUser(UserEntity user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TutoringAppointmentEntity delete(TutoringAppointmentEntity appointment) {
		return deleteById(appointment.getTutoringAppointmentNo());
	}

	@Override
	public List<TutoringAppointmentEntity> deleteMultiple(List<TutoringAppointmentEntity> appointments) {
		List<Long> ids = appointments.stream().map(TutoringAppointmentEntity::getTutoringAppointmentNo)
				.collect(Collectors.toList());
		tutAppointmentEntityRepository.deleteAllInBatch(appointments);
		cache.removeAll(ids);

		return appointments;
	}

	@Override
	public TutoringAppointmentEntity deleteById(long id) {
		Optional<TutoringAppointmentEntity> optional = tutAppointmentEntityRepository.findById(id);

		if (optional.isPresent()) {
			cache.remove(optional.get().getTutoringAppointmentNo());
			return optional.get();
		}

		return null;
	}

	@Override
	public List<TutoringAppointmentEntity> deleteByIds(List<Long> ids) {
		List<TutoringAppointmentEntity> deletedAppointments = new ArrayList<>();
		for (Long id : ids) {
			TutoringAppointmentEntity appointment = deleteById(id);

			if (appointment != null) {
				deletedAppointments.add(appointment);
			}
		}

		return deletedAppointments;
	}

	@Override
	public TutoringAppointmentEntity update(TutoringAppointmentEntity appointment) {
		TutoringAppointmentEntity updatedAppointment = null;

		if (validated(appointment)
				&& tutAppointmentEntityRepository.existsById(appointment.getTutoringAppointmentNo())) {
			updatedAppointment = tutAppointmentEntityRepository.saveAndFlush(appointment);

			if (updatedAppointment != null) {
				cache.add(updatedAppointment.getTutoringAppointmentNo(), updatedAppointment);
			}
		}

		return updatedAppointment;
	}

	@Override
	public List<TutoringAppointmentEntity> updateMultiple(List<TutoringAppointmentEntity> appointments) {
		List<TutoringAppointmentEntity> updatedAppointments = new ArrayList<>();

		for (TutoringAppointmentEntity appointment : appointments) {
			TutoringAppointmentEntity currentUpdatedAppointment = update(appointment);

			if (currentUpdatedAppointment != null) {
				updatedAppointments.add(currentUpdatedAppointment);
			}
		}

		if (!updatedAppointments.isEmpty()) {
			Map<Long, TutoringAppointmentEntity> mappedAppointments = updatedAppointments.stream().collect(
					Collectors.toMap(TutoringAppointmentEntity::getTutoringAppointmentNo, Function.identity()));
			cache.addAll(mappedAppointments);
		}

		return updatedAppointments;
	}

	@Override
	public void deleteAll() {
		tutAppointmentEntityRepository.deleteAll();
		cache.cleanAll();
	}

	@Override
	public boolean validated(TutoringAppointmentEntity appointment) {
		boolean isValid = isValid(appointment);
		boolean dayIsNotFull = !isDayFullWithAppointments(appointment);
		boolean weekIsNotFull = !isWeekFullWithAppointmens(appointment);

		if (!isValid) {
			return false;
		}

		if (!weekIsNotFull) {
			return false;
		}

		if (!dayIsNotFull) {
			return false;
		}

		return true;
	}

	@Override
	public TutoringAppointmentEntity saveWithoutValidation(TutoringAppointmentEntity tutoringAppointment) {
		TutoringAppointmentEntity savedAppointment = tutAppointmentEntityRepository.saveAndFlush(tutoringAppointment);
		cache.add(savedAppointment.getTutoringAppointmentNo(), savedAppointment);

		return savedAppointment;
	}
}
