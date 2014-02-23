package org.trwib.service;

import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.trwib.model.EventCategory;
import org.trwib.model.FullEvent;
import org.trwib.model.SimpleEvent;
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
	public List<SimpleEvent> getEventsByCategory(Long categoryId, int page, int size) {
    	TypedQuery<SimpleEvent> query = 
        		em.createQuery("SELECT e FROM SimpleEvent e join e.eventCategories cats WHERE cats.id = :categoryId", 
        				SimpleEvent.class);
        query.setParameter("categoryId", categoryId);
        query.setFirstResult(page * size);
        query.setMaxResults(size);
        return query.getResultList();
    }
    
	@Override
	public List<SimpleEvent> getAllEvents(int page, int size) {
    	TypedQuery<SimpleEvent> query = 
        		em.createQuery("SELECT e FROM SimpleEvent e", SimpleEvent.class);
        query.setFirstResult(page * size);
        query.setMaxResults(size);
        return query.getResultList();
	}

	@Override
	public List<EventCategory> getAllCategories() {
		return eventCategoryRepository.findAll();
	}

    @Override
    public Integer create(FullEvent event) {
        FullEvent returnedEvent = eventRepository.saveAndFlush(event);
        return returnedEvent.getEventId();
    }

	@Override
	public FullEvent getEventDetail(int eventId) {
		return eventRepository.findOne(eventId);
	}

}
