package org.singularnost.repository;

import org.singularnost.model.Event;
import org.singularnost.model.Prediction;
import org.singularnost.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author aleksandrpliskin on 22.04.17.
 */
@Repository
public interface PredictionRepository extends JpaRepository<Prediction, Long> {

    List<Prediction> findByEvent(Event event);

    List<Prediction> findByUser(User user);

}
