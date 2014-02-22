package org.trwib.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.trwib.model.QuestionResponse;

/**
 * Writes and reads question responses to and from the database
 * @author bgray
 */
public interface QuestionResponseRepository extends JpaRepository<QuestionResponse, Long> {

    /**
     * Gets the first question
     * @return
     */
    @Query("SELECT q FROM Question q WHERE q.isFirst = true")
    QuestionResponse getFirst();

//    @Query("SELECT q FROM Question q WHERE q. = LOWER(:lastName)")
//    QuestionResponse(@Param("questionId") Long questionId, @Param("yesResponse") boolean yesResponse);
}
