package org.trwib.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.trwib.model.Event;
import org.trwib.repository.EventRepository;

@Transactional
@Service
public class EventServiceImpl implements EventService {

    @Resource
    private EventRepository eventRepository;

    @Override
	public List<Event> getEventsByCategory(Long categoryId, int page, int size) {
		return eventRepository.findAll();
	}

    @Override
    public Integer create(Event event) {
        Event returnedEvent = eventRepository.saveAndFlush(event);
        return returnedEvent.getEventId();
    }

}
