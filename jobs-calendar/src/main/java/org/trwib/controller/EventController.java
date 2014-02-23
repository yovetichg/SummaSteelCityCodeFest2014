package org.trwib.controller;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.trwib.dto.EventsDto;
import org.trwib.model.Event;
import org.trwib.model.EventCategory;
import org.trwib.service.EventService;

import com.fasterxml.jackson.databind.util.JSONPObject;

@Controller
public class EventController {
    private static final Logger LOGGER = LoggerFactory.getLogger(EventController.class);

    @Resource
    private EventService eventService;

    @ResponseBody
    @RequestMapping(value = "/allevents", method = RequestMethod.GET, produces="application/json")
    public JSONPObject getAllEvents( 
						@RequestParam(value="page", defaultValue="0") final Integer page,
						@RequestParam(value="size", defaultValue="10") final Integer size,
    					@RequestParam(value="callback") final String callback) {
        LOGGER.debug("Getting all events");
        List<Event> list = eventService.getAllEvents(page, size);
        return new JSONPObject(callback, new EventsDto(list, page, size));
    }

    @ResponseBody
    @RequestMapping(value = "/events/{categoryId}", method = RequestMethod.GET, produces="application/json")
    public JSONPObject getEvents(@PathVariable("categoryId") final Long categoryId,
			@RequestParam(value="page", defaultValue="0") final Integer page,
			@RequestParam(value="size", defaultValue="10") final Integer size,
    					@RequestParam(value="callback") final String callback) {
        LOGGER.debug("Getting all events for category {}", categoryId);
        List<Event> list = eventService.getEventsByCategory(categoryId, page, size);
        return new JSONPObject(callback, new EventsDto(list, page, size));
    }
    
    @ResponseBody
    @RequestMapping(value = "/categories", method = RequestMethod.GET, produces="application/json")
    public JSONPObject getCategories(@RequestParam("callback") final String callback) {
        LOGGER.debug("Getting all categories");
        List<EventCategory> list = eventService.getAllCategories();
        return new JSONPObject(callback, list);
    }
 }
