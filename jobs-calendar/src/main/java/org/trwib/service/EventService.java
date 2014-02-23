package org.trwib.service;

import org.trwib.model.Event;

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
     * Create a new event
     * @param event
     * @return ID of the newly created event
     */
    Integer create(Event event);
}
