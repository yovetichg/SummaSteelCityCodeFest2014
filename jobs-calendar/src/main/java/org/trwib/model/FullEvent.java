package org.trwib.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;


/**
 * The persistent class for the event database table.
 * 
 */
@Entity
@Table(name = "event")
public class FullEvent extends BaseEvent {
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="provider_id")
	private Provider provider;

	public FullEvent() {
	}

	public Provider getProvider() {
		return this.provider;
	}

	public void setProvider(Provider provider) {
		this.provider = provider;
	}
}