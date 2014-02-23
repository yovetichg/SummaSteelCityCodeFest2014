package org.trwib.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@MappedSuperclass
public class BaseEvent {
	@Id
	@Column(name="event_id")
	private int eventId;

	private String description;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="end_dt")
	private Date endDt;

	private String location;

	private String name;

	// Connect to EventCategory via the EventCategoryAssociation 
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="event_category_association", 
		joinColumns={@JoinColumn(name="event_id")},
		inverseJoinColumns={@JoinColumn(name="event_category_id")})
	private List<EventCategory> eventCategories;

	public int getEventId() {
		return this.eventId;
	}

	public void setEventId(int eventId) {
		this.eventId = eventId;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getEndDt() {
		return this.endDt;
	}

	public void setEndDt(Date endDt) {
		this.endDt = endDt;
	}

	public String getLocation() {
		return this.location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="start_dt")
	private Date startDt;

	public Date getStartDt() {
		return this.startDt;
	}

	public void setStartDt(Date startDt) {
		this.startDt = startDt;
	}

	public List<EventCategory> getEventCategories() {
		return this.eventCategories;
	}
}
