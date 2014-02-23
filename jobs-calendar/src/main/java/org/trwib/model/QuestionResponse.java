package org.trwib.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import java.io.Serializable;

/**
 * Entity class representing a response to a question
 * @author bgray
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "question_response")
@Inheritance(strategy= InheritanceType.JOINED)
public abstract class QuestionResponse implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public abstract String getDescription();

    public Long getId() {
        return id;
    }

    /**
     * This setter method should only be used by unit tests.
     * @param id
     */
    protected void setId(Long id) {
        this.id = id;
    }
}
