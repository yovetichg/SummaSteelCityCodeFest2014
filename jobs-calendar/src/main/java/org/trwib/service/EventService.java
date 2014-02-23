package org.trwib.service;

import java.util.List;

import org.trwib.model.EventCategory;
import org.trwib.model.FullEvent;
import org.trwib.model.SimpleEvent;

public interface EventService {

	/**
	 * Return a list of events by category ID
	 * 
	 * @param categoryId
	 * @param page
	 * @param size
	 * @return
	 */
	List<SimpleEvent> getEventsByCategory(Long categoryId, int page, int size);

	/**
	 * Return a list of all events
	 * 
	 * @param page
	 * @param size
	 * @return
	 */
	List<SimpleEvent> getAllEvents(int page, int size); 
	
	/**
	 * @return list of all categories
	 */
	List<EventCategory> getAllCategories();

	/**
     * Create a new event
     * @param event
     * @return ID of the newly created event
     */
    Integer create(FullEvent event);
}
