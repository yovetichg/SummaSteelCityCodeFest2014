package org.trwib.service;

import java.util.List;

import org.trwib.model.Event;
import org.trwib.model.EventCategory;

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

	/**
	 * Return a list of all events
	 * 
	 * @param page
	 * @param size
	 * @return
	 */
	List<Event> getAllEvents(int page, int size); 
	/**
	 * @return list of all categories
	 */
	List<EventCategory> getAllCategories();
}
