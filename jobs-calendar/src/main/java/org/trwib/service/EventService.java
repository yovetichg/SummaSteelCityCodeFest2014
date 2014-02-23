package org.trwib.service;

import java.util.List;

import org.trwib.model.Event;

public interface EventService {
	
	/**
	 * Return a list of events by category ID
	 * 
	 * @param categoryId
	 * @param page
	 * @param size
	 * @return
	 */
	List<Event> getEventsByCategory(Long categoryId, int page, int size);
}
