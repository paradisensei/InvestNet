package org.singularnost.service.impl;

import org.singularnost.model.Event;
import org.singularnost.model.Prediction;
import org.singularnost.model.User;
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

    private final PredictionRepository predictionRepository;

    @Autowired
    public PredictionServiceImpl(PredictionRepository predictionRepository) {
        this.predictionRepository = predictionRepository;
    }

    @Override
    public List<Prediction> findByEvent(Event event) {
        return predictionRepository.findByEvent(event);
    }

    @Override
    public void add(User user, Event event, int prediction) {
        predictionRepository.save(new Prediction(user, event, prediction));
    }

}
