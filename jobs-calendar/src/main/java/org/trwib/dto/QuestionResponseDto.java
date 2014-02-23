package org.trwib.dto;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.trwib.model.EventCategory;
import org.trwib.model.Question;
import org.trwib.model.QuestionResponse;

import java.io.Serializable;

/**
 * DTO to make the JSON look as the API needs
 * @author bgray
 **/
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class QuestionResponseDto implements Serializable {

    public QuestionResponseDto(QuestionResponse response) {
        if (response instanceof Question) {
            this.question = (Question) response;
        } else if (response instanceof EventCategory) {
            this.category = (EventCategory) response;
        }
    }

    private Question question;

    private EventCategory category;

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public EventCategory getCategory() {
        return category;
    }

    public void setCategory(EventCategory category) {
        this.category = category;
    }
}
