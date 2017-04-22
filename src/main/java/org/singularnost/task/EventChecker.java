package org.singularnost.task;

import org.singularnost.repository.DecisionRepository;
import org.singularnost.repository.EventRepository;
import org.singularnost.service.DecisionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author aleksandrpliskin on 22.04.17.
 */
@Component
public class EventChecker {

    private class EventCheckTask implements Runnable {

        @Override
        public void run() {
            eventRepository.findByCloseDateLessThan(new Date().getTime())
                    .stream()
                    .map(decisionService::getFinalPredictionForEvent)
                    .forEach(decisionRepository::save);
        }
    }

    private final TaskExecutor taskExecutor;
    private final DecisionService decisionService;
    private final EventRepository eventRepository;
    private final DecisionRepository decisionRepository;

    @Autowired
    public EventChecker(TaskExecutor taskExecutor, DecisionService decisionService,
                        EventRepository eventRepository, DecisionRepository decisionRepository) {
        this.taskExecutor = taskExecutor;
        this.decisionService = decisionService;
        this.eventRepository = eventRepository;
        this.decisionRepository = decisionRepository;
    }

    public void checkEvents() {
        taskExecutor.execute(new EventCheckTask());
    }

}
