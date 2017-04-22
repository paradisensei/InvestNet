package org.singularnost.service.impl;

import org.singularnost.model.Decision;
import org.singularnost.model.Event;
import org.singularnost.model.Prediction;
import org.singularnost.repository.DecisionRepository;
import org.singularnost.service.DecisionService;
import org.singularnost.service.PredictionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author aleksandrpliskin on 22.04.17.
 */
@Service
public class DecisionServiceImpl implements DecisionService {

    private final PredictionService predictionService;
    private final DecisionRepository decisionRepository;

    @Autowired
    public DecisionServiceImpl(PredictionService predictionService,
                               DecisionRepository decisionRepository) {
        this.predictionService = predictionService;
        this.decisionRepository = decisionRepository;
    }

    @Override
    public Decision getFinalPredictionForEvent(Event event) {
        List<Prediction> predictions = predictionService.findByEvent(event);

        // compute weighted mean
        int prod = 0;
        int weightSum = 0;
        for (Prediction p : predictions) {
            int percent = p.getPrediction();
            int weight = p.getUser().getWeight();
            if (weight < 0) {
                continue;
            }
            prod += weight * percent;
            weightSum += weight;
        }

        return new Decision(event, prod / weightSum);
    }

    @Override
    public void add(Decision decision) {
        decisionRepository.save(decision);
    }

}
