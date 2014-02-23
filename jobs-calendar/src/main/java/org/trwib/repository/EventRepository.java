package org.trwib.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.trwib.model.FullEvent;

public interface EventRepository extends JpaRepository<FullEvent, Long> {
}
