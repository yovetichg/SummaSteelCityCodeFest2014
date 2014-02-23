package org.trwib.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the event database table.
 * 
 */
@Entity
@NamedQuery(name="Event.findAll", query="SELECT e FROM Event e")
public class Event implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="event_id")
	private int eventId;

	private String description;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="end_dt")
	private Date endDt;

	private String location;

	private String name;

	@Column(name="provider_id")
	private int providerId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="start_dt")
	private Date startDt;

	//bi-directional many-to-one association to EventCategoryAssociation
	//@OneToMany(mappedBy="event", fetch=FetchType.LAZY)
	//private List<EventCategoryAssociation> eventCategoryAssociations;

	public Event() {
	}

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

	public int getProviderId() {
		return this.providerId;
	}

	public void setProviderId(int providerId) {
		this.providerId = providerId;
	}

	public Date getStartDt() {
		return this.startDt;
	}

	public void setStartDt(Date startDt) {
		this.startDt = startDt;
	}
/*
	public List<EventCategoryAssociation> getEventCategoryAssociations() {
		return this.eventCategoryAssociations;
	}

	public void setEventCategoryAssociations(List<EventCategoryAssociation> eventCategoryAssociations) {
		this.eventCategoryAssociations = eventCategoryAssociations;
	}

	public EventCategoryAssociation addEventCategoryAssociation(EventCategoryAssociation eventCategoryAssociation) {
		getEventCategoryAssociations().add(eventCategoryAssociation);
		eventCategoryAssociation.setEvent(this);

		return eventCategoryAssociation;
	}

	public EventCategoryAssociation removeEventCategoryAssociation(EventCategoryAssociation eventCategoryAssociation) {
		getEventCategoryAssociations().remove(eventCategoryAssociation);
		eventCategoryAssociation.setEvent(null);

		return eventCategoryAssociation;
	}
*/
}