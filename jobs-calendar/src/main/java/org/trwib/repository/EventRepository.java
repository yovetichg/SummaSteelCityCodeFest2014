package org.trwib.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.trwib.model.Event;

public interface EventRepository extends JpaRepository<Event, Long> {

}
