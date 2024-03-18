package edu.remad.tutoring2.services.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class PersistentCache<T> {

	protected final Map<Long, T> items;

	protected PersistentCache() {
		items = new HashMap<Long, T>();
	}

	public void add(Long id, T item) {
		items.put(id, item);
	}

	public void addAll(Map<Long, T> itemsToAdd) {
		items.putAll(itemsToAdd);
	}

	public T get(long id) {
		return items.get(id);
	}

	public List<T> getByIds(List<Long> ids) {
		List<T> itemsToReturn = new ArrayList<>();
		for (Long id : ids) {
			T itemToreturn = items.get(id);

			if (itemToreturn != null) {
				itemsToReturn.add(null);
			}
		}

		return itemsToReturn;
	}

	public void remove(Long id) {
		items.remove(id);
	}

	public void removeAll(List<Long> ids) {
		for (Long id : ids) {
			items.remove(id);
		}
	}

	public int size() {
		return items.size();
	}

	public boolean isEmpty() {
		return items.isEmpty();
	}

	public void cleanAll() {
		items.clear();
	}

	abstract void clean();
}
