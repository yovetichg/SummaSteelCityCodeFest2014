package org.trwib.service;

import org.trwib.model.Event;
import org.trwib.model.EventCategory;
import java.util.List;

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

	/**
     * Create a new event
     * @param event
     * @return ID of the newly created event
     */
    Integer create(Event event);
}
