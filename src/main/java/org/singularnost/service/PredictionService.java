package org.singularnost.service;

import org.singularnost.model.Event;
import org.singularnost.model.Prediction;

import java.util.List;

/**
 * @author aleksandrpliskin on 22.04.17.
 */
public interface PredictionService {

    List<Prediction> findByEvent(Event event);

    Prediction getFinalPredictionForEvent(Event event);

}
