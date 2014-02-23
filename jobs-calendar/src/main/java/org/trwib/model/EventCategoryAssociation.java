package org.trwib.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the event_category_association database table.
 * 
 */
@Entity
@Table(name="event_category_association")
@NamedQuery(name="EventCategoryAssociation.findAll", query="SELECT e FROM EventCategoryAssociation e")
public class EventCategoryAssociation implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="event_category_association_id")
	private int eventCategoryAssociationId;

	@Column(name="event_category_id")
	private int eventCategoryId;

	//bi-directional many-to-one association to Event
	@ManyToOne
	@JoinColumn(name="event_id")
	private Event event;

	public EventCategoryAssociation() {
	}

	public int getEventCategoryAssociationId() {
		return this.eventCategoryAssociationId;
	}

	public void setEventCategoryAssociationId(int eventCategoryAssociationId) {
		this.eventCategoryAssociationId = eventCategoryAssociationId;
	}

	public int getEventCategoryId() {
		return this.eventCategoryId;
	}

	public void setEventCategoryId(int eventCategoryId) {
		this.eventCategoryId = eventCategoryId;
	}

	public Event getEvent() {
		return this.event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

}