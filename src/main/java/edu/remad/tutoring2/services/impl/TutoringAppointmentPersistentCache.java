package edu.remad.tutoring2.services.impl;

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
}
