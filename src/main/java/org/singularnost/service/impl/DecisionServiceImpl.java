package org.singularnost.service.impl;

import org.singularnost.model.Decision;
import org.singularnost.model.Event;
import org.singularnost.model.Prediction;
import org.singularnost.model.enums.Direction;
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

    private static final int M = 2;

    @Autowired
    private PredictionService predictionService;

    @Override
    public Decision getFinalPredictionForEvent(Event event) {

        List<Prediction> predictions = predictionService.findByEvent(event);

        double positive = predictions.stream()
                .filter(prediction -> prediction.getPrediction() > 50)
                .map(prediction -> {
                    int percent = prediction.getPrediction();
                    int userWeight = prediction.getUser().getWeight();
                    return percent * Math.pow(userWeight, M);
                })
                .reduce((p1, p2) -> p1 + p2).get();
        double negative = predictions.stream()
                .filter(prediction -> prediction.getPrediction() < 50)
                .map(prediction -> {
                    int percent = 100 - prediction.getPrediction();
                    int userWeight = prediction.getUser().getWeight();
                    return percent * Math.pow(userWeight, M);
                })
                .reduce((p1, p2) -> p1 + p2).get();

        Decision decision = new Decision();
        decision.setEvent(event);

        decision.setDirection(
                Math.abs(positive - negative) < 15 ? Direction.NONE :
                        positive - negative > 0 ? Direction.UP : Direction.DOWN);

        return decision;

    }

}
