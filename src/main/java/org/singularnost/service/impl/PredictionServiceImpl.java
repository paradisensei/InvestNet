package org.singularnost.service.impl;

import org.singularnost.model.Decision;
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

    @Autowired
    private
    PredictionRepository predictionRepository;

    @Override
    public List<Prediction> findByEvent(Event event) {
        return predictionRepository.findByEvent(event);
    }

}
