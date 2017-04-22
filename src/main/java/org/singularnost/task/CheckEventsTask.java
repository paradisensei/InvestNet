package org.singularnost.task;

import org.singularnost.service.DecisionService;
import org.singularnost.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author Aidar Shaifutdinov.
 */
@Component
public class CheckEventsTask {

    private final EventService eventService;
    private final DecisionService decisionService;

    @Autowired
    public CheckEventsTask(EventService eventService, DecisionService decisionService) {
        this.eventService = eventService;
        this.decisionService = decisionService;
    }

    // fixedRate = 1 day
    @Scheduled(fixedRate = 86400000)
    public void checkEvents() {
        eventService.getPassed().stream()
                .map(decisionService::getFinalPredictionForEvent)
                .forEach(decisionService::add);
    }

}
