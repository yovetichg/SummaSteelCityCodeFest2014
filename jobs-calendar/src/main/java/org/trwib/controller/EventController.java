package org.trwib.controller;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.trwib.dto.EventsDto;
import org.trwib.model.Event;
import org.trwib.service.EventService;

import com.fasterxml.jackson.databind.util.JSONPObject;

@Controller
public class EventController {
    private static final Logger LOGGER = LoggerFactory.getLogger(EventController.class);

    @Resource
    private EventService eventService;

    @ResponseBody
    @RequestMapping(value = "/events", method = RequestMethod.GET, produces="application/json")
    public JSONPObject getEvent(@RequestParam("callback") final String callback) {
        LOGGER.debug("Getting all events");
        List<Event> list = eventService.getEventsByCategory(0L, 1, 20);
        return new JSONPObject(callback, new EventsDto(list, 1, 20));
    }

    @ResponseBody
    @RequestMapping(value = "/events", method = RequestMethod.POST, produces="application/json")
    public JSONPObject createEvent(@RequestParam("callback") final String callback,
                                   @RequestBody(required = true) Event event) {
        LOGGER.debug("Creating event [{}]", event);
        return new JSONPObject(callback, eventService.create(event));
    }
 }
