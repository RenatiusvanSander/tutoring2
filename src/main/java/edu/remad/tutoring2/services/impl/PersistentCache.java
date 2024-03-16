package edu.remad.tutoring2.services.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class PersistentCache<T> {

	protected final Map<Long, T> items;
	protected final Class<T> clazz;
	
	protected PersistentCache(Class<T> clazz) {
		items = new HashMap<>();
		this.clazz = clazz;
	}
	
	public void add(Long id, T item) {
		items.put(id, item);
	}
	
	public void addAll(Map<Long, T> itemsToAdd) {
		items.putAll(itemsToAdd);
	}
	
	public void remove( Long id) {
		items.remove(id);
	}
	
	public void removeAll(List<Long> ids) {
		for(Long id : ids) {
			items.remove(id);
		}
	}
	
	abstract void clean();
}
