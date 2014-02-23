package org.trwib.dto;

import java.util.List;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import org.trwib.model.Event;

@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class EventsDto {
	private List<Event> events;
	private int page;
	private int size;

	public EventsDto(List<Event> events, int page, int size) {
		this.events = events;
		this.page = page;
		this.size = size;
	}
	
	public List<Event> getEvent() {
		return events;
	}

	public void setEvent(List<Event> events) {
		this.events = events;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}
}
