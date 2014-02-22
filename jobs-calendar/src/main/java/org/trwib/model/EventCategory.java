package org.trwib.model;

import org.apache.commons.lang.builder.ToStringBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * Entity class representing a category an event belongs to
 * @author bgray
 */
@Entity
@Table(name = "event_category")
@PrimaryKeyJoinColumn(name="question_response_id")
public class EventCategory extends QuestionResponse {
    
    @Column(name = "description")
    private String description;

    @Override
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
