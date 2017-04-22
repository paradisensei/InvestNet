package org.singularnost.repository;

import org.singularnost.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author aleksandrpliskin on 22.04.17.
 */
@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
}
