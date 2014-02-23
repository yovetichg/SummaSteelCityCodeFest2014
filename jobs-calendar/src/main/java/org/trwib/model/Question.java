package org.trwib.model;

import org.codehaus.jackson.annotate.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * Entity class representing a Question itself
 * @author bgray
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "question")
@PrimaryKeyJoinColumn(name="question_response_id")
public class Question extends QuestionResponse {
    
    @Column(name = "question")
    private String question;

    @Column(name = "is_first")
    private Boolean isFirst;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "yes_id")
    @JsonIgnore
    private QuestionResponse yesResponse;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "no_id")
    @JsonIgnore
    private QuestionResponse noResponse;

    @Override
    @JsonIgnore
    public String getDescription() {
        return question;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    @JsonIgnore
    public Boolean getFirst() {
        return isFirst;
    }

    protected void setFirst(Boolean first) {
        isFirst = first;
    }

    public QuestionResponse getYesResponse() {
        return yesResponse;
    }

    protected void setYesResponse(QuestionResponse yesResponse) {
        this.yesResponse = yesResponse;
    }

    public QuestionResponse getNoResponse() {
        return noResponse;
    }

    protected void setNoResponse(QuestionResponse noResponse) {
        this.noResponse = noResponse;
    }

}
