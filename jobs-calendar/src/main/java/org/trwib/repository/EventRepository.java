package org.trwib.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.trwib.model.Event;
import org.trwib.model.QuestionResponse;

public interface EventRepository extends JpaRepository<Event, Long> {
}
