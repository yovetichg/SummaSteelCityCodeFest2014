package org.trwib.service;

import org.trwib.model.QuestionResponse;

/**
 * Methods used to obtain information relating to knock-out questions
 * @bgray
 */
public interface QuestionResponseService {

    /**
     * Gets the first questions
     * @return
     */
    QuestionResponse getFirst();

    /**
     * Gets the response based on the response to the last
     * @param questionId id of the question answered
     * @param yesResponse true if the last question was answered 'yes', false if 'no'
     * @return the response
     */
    QuestionResponse getNext(Long questionId, boolean yesResponse);

}
