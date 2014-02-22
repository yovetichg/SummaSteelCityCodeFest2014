package com.summa.codefest;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the question database table.
 * 
 */
@Entity
@Table(name="question")
@NamedQuery(name="Question.findAll", query="SELECT q FROM Question q")
public class Question implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="question_response_id")
	private int questionResponseId;

	@Column(name="is_first")
	private byte isFirst;

	@Column(name="no_id")
	private int noId;

	private String question;

	@Column(name="yes_id")
	private int yesId;

	public Question() {
	}

	public int getQuestionResponseId() {
		return this.questionResponseId;
	}

	public void setQuestionResponseId(int questionResponseId) {
		this.questionResponseId = questionResponseId;
	}

	public byte getIsFirst() {
		return this.isFirst;
	}

	public void setIsFirst(byte isFirst) {
		this.isFirst = isFirst;
	}

	public int getNoId() {
		return this.noId;
	}

	public void setNoId(int noId) {
		this.noId = noId;
	}

	public String getQuestion() {
		return this.question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public int getYesId() {
		return this.yesId;
	}

	public void setYesId(int yesId) {
		this.yesId = yesId;
	}

}