package org.trwib.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.trwib.model.QuestionResponse;

/**
 * Writes and reads question responses to and from the database
 * @author bgray
 */
public interface QuestionResponseRepository extends JpaRepository<QuestionResponse, Long> {

    @Query("SELECT q FROM Question q WHERE q.isFirst = true")
    QuestionResponse getFirst();

    @Query("SELECT q.yesResponse FROM Question q WHERE q.id = :questionId")
    QuestionResponse getNextYesResponse(@Param("questionId") Long questionId);

    @Query("SELECT q.noResponse FROM Question q WHERE q.id = :questionId")
    QuestionResponse getNextNoResponse(@Param("questionId") long questionId);
}
