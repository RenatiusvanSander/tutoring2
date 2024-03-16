package edu.remad.tutoring2.services.impl;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import edu.remad.tutoring2.models.TutoringAppointmentEntity;
import edu.remad.tutoring2.models.UserEntity;
import edu.remad.tutoring2.services.TutoringAppointmentService;

public class TutoringAppointmentServiceImpl implements TutoringAppointmentService {
	
	private final PersistentCache cache;
	
	private final TutoringAppointmentService tutoringAppointmentService;
	
	@Autowired
	public TutoringAppointmentServiceImpl(PersistentCache cache, TutoringAppointmentService tutoringAppointmentService) {
		this.cache = cache;
		this.tutoringAppointmentService = tutoringAppointmentService;
	}
	
	@Override
	public boolean isValid(TutoringAppointmentEntity appointment) {
		LocalDateTime dateAndTime = LocalDateTime.now();
		int mondayToFridayStartTime = 19;
		int mondayToFridayEndTime = 22;
		int saturdayToSundayStartTime = 10;
		int saturdayToSundayEndTime = 21;
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
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isWeekFullWithAppointmens(TutoringAppointmentEntity appointment) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public TutoringAppointmentEntity save(TutoringAppointmentEntity tutoringAppointment) {
		
		return null;
	}

	@Override
	public List<TutoringAppointmentEntity> saveAll(List<TutoringAppointmentEntity> tutoringAppointments) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TutoringAppointmentEntity getById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TutoringAppointmentEntity> getByIds(List<Long> ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TutoringAppointmentEntity> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TutoringAppointmentEntity> getAllOfCurrentDate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TutoringAppointmentEntity> getAllOfCurrentWeek() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TutoringAppointmentEntity> getAllOfCurrentMonth() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TutoringAppointmentEntity> getAllForUser(UserEntity user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TutoringAppointmentEntity> getAllOfCurrentDateForUser(UserEntity user) {
		// TODO Auto-generated method stub
		return null;
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TutoringAppointmentEntity> deleteMultiple(List<TutoringAppointmentEntity> appointments) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TutoringAppointmentEntity deleteById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TutoringAppointmentEntity deleteByIds(List<Long> ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TutoringAppointmentEntity update(TutoringAppointmentEntity appointment) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TutoringAppointmentEntity> updatemultiple(List<TutoringAppointmentEntity> appointment) {
		// TODO Auto-generated method stub
		return null;
	}
}
