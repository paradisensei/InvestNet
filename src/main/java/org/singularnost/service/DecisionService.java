package org.singularnost.service;

import org.singularnost.model.Decision;
import org.singularnost.model.Event;

/**
 * @author aleksandrpliskin on 22.04.17.
 */
public interface DecisionService {

    Decision getFinalPredictionForEvent(Event event);

}
