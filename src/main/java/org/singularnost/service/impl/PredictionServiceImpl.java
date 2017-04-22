package org.singularnost.service.impl;

import org.singularnost.model.Event;
import org.singularnost.model.Prediction;
import org.singularnost.repository.PredictionRepository;
import org.singularnost.service.PredictionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author aleksandrpliskin on 22.04.17.
 */
@Service
public class PredictionServiceImpl implements PredictionService {

    private static final int M = 2;

    @Autowired
    private
    PredictionRepository predictionRepository;

    @Override
    public List<Prediction> findByEvent(Event event) {
        return predictionRepository.findByEvent(event);
    }

    @Override
    public Prediction getFinalPredictionForEvent(Event event) {
        List<Prediction> predictions = findByEvent(event);
        predictions.stream().map(prediction -> {
            int percent = prediction.getPrediction();
            int userWeight = prediction.getUser().getWeight();
            return 0;
        }).reduce((p1, p2) -> (int) p1 + (int) p2);
        return new Prediction();
    }
}
