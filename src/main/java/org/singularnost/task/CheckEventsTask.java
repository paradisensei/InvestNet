package org.singularnost.task;

import org.singularnost.model.Decision;
import org.singularnost.model.Prediction;
import org.singularnost.model.User;
import org.singularnost.repository.DecisionRepository;
import org.singularnost.repository.UserRepository;
import org.singularnost.service.DecisionService;
import org.singularnost.service.EventService;
import org.singularnost.service.PredictionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Aidar Shaifutdinov.
 */
@Component
public class CheckEventsTask {

    private final EventService eventService;
    private final DecisionService decisionService;
    private final DecisionRepository decisionRepository;
    private final PredictionService predictionService;
    private final UserRepository userRepository;

    @Autowired
    public CheckEventsTask(EventService eventService, DecisionService decisionService,
                           DecisionRepository decisionRepository,
                           PredictionService predictionService, UserRepository userRepository) {
        this.eventService = eventService;
        this.decisionService = decisionService;
        this.decisionRepository = decisionRepository;
        this.predictionService = predictionService;
        this.userRepository = userRepository;
    }

    // fixedRate = 1 day
    @Scheduled(fixedRate = 86400000)
    public void processPassedEvents() {
        eventService.getPassed().stream()
                .filter(event -> decisionRepository.findByEvent(event) == null)
                .forEach(event -> {
                    Decision decision = decisionService.getFinalPredictionForEvent(event);
                    decisionService.add(decision);
                    List<Prediction> predictions = predictionService.findByEvent(event);
                    predictions.forEach(prediction -> {
                        User user = prediction.getUser();
                        //TODO add decision check
                        if (decision.getPrediction() > 50) {
                            user.setWeight(user.getWeight() - 50 + prediction.getPrediction());
                        } else {
                            user.setWeight(user.getWeight() + 50 - prediction.getPrediction());
                        }
                        userRepository.save(user);
                    });
                });
    }


}
