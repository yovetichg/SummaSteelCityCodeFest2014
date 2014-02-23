package org.trwib.service;

import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.trwib.model.Event;
import org.trwib.model.EventCategory;
import org.trwib.repository.EventCategoryRepository;
import org.trwib.repository.EventRepository;

@Transactional
@Service
public class EventServiceImpl implements EventService {

	@PersistenceContext
	private EntityManager em;
	
	@Resource
    private EventRepository eventRepository;

    @Resource
    private EventCategoryRepository eventCategoryRepository;

    @Override
	public List<Event> getEventsByCategory(Long categoryId, int page, int size) {
    	TypedQuery<Event> query = 
        		em.createQuery("SELECT e FROM Event e join e.eventCategories cats WHERE cats.id = :categoryId", 
        				Event.class);
        query.setParameter("categoryId", categoryId);
        query.setFirstResult(page * size);
        query.setMaxResults(size);
        return query.getResultList();
    }
    
	@Override
	public List<Event> getAllEvents(int page, int size) {
    	TypedQuery<Event> query = 
        		em.createQuery("SELECT e FROM Event e", Event.class);
        query.setFirstResult(page * size);
        query.setMaxResults(size);
        return query.getResultList();
	}

	@Override
	public List<EventCategory> getAllCategories() {
		return eventCategoryRepository.findAll();
	}

    @Override
    public Integer create(Event event) {
        Event returnedEvent = eventRepository.saveAndFlush(event);
        return returnedEvent.getEventId();
    }

}
