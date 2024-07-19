package edu.remad.tutoring2.services;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation = Propagation.REQUIRES_NEW)
public abstract class AbstractTutoringService<T> {

	protected abstract void deProxy(T entity);
}
