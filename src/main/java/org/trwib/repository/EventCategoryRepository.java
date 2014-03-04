package org.trwib.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.trwib.model.EventCategory;

public interface EventCategoryRepository extends JpaRepository<EventCategory, Long> {

}
