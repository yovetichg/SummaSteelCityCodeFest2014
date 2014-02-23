package org.trwib.dto;

import java.io.Serializable;
import java.util.List;

import org.trwib.model.SimpleEvent;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@SuppressWarnings("serial")
@JsonSerialize()
public class EventsDto  implements Serializable {
	private List<SimpleEvent> events;
	private int page;
	private int size;

	public EventsDto(List<SimpleEvent> events, int page, int size) {
		this.events = events;
		this.page = page;
		this.size = size;
	}
	
	public List<SimpleEvent> getEvent() {
		return events;
	}

	public void setEvent(List<SimpleEvent> events) {
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
