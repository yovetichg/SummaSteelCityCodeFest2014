package org.trwib.model;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.IOException;

@Entity
@Table(name = "event")
@JsonSerialize(using = SimpleEvent.UserSerializer.class)
public class SimpleEvent extends BaseEvent {
	protected static class UserSerializer extends JsonSerializer<SimpleEvent>
	{
		@Override
		public void serialize(SimpleEvent event, JsonGenerator generator,
				SerializerProvider provider) throws IOException,
				JsonProcessingException {
	        generator.writeStartObject();
	        generator.writeNumberField("eventId", event.getEventId());
	        generator.writeStringField("name", event.getName());
	        generator.writeStringField("description", event.getDescription());
	        generator.writeStringField("location", event.getLocation());
	        generator.writeNumberField("startDt", event.getStartDt().getTime());
	        generator.writeNumberField("endDt", event.getEndDt().getTime());
	        generator.writeEndObject();
        }
		
	}
}
