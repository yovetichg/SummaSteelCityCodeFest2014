package org.trwib.model;

import org.apache.commons.lang.builder.ToStringBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * Entity class representing a Question itself
 * @author bgray
 */
@Entity
@Table(name = "question")
@PrimaryKeyJoinColumn(name="question_response_id")
public class Question extends QuestionResponse {
    
    @Column(name = "question")
    private String question;

    @Column(name = "is_first")
    private Boolean isFirst;

    @Override
    public String getDescription() {
        return question;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Boolean getFirst() {
        return isFirst;
    }

    public void setFirst(Boolean first) {
        isFirst = first;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
