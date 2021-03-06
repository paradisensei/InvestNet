package org.singularnost.repository;

import org.singularnost.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author aleksandrpliskin on 22.04.17.
 */
@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    List<Event> findByCloseDateGreaterThan(long closeDate);

    List<Event> findByCloseDateGreaterThanAndCloseDateLessThan(long begin, long closeDate);
}
