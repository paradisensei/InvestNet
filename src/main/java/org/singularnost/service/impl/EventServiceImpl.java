package org.singularnost.service.impl;

import org.singularnost.model.Event;
import org.singularnost.model.Prediction;
import org.singularnost.model.User;
import org.singularnost.repository.EventRepository;
import org.singularnost.repository.PredictionRepository;
import org.singularnost.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Aidar Shaifutdinov.
 */
@Service
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final PredictionRepository predictionRepository;

    @Autowired
    public EventServiceImpl(EventRepository eventRepository,
                            PredictionRepository predictionRepository) {
        this.eventRepository = eventRepository;
        this.predictionRepository = predictionRepository;
    }

    @Override
    public Event getOne(long id) {
        return eventRepository.findOne(id);
    }

    @Override
    public List<Event> getActive(User user) {
        List<Event> allEvents = eventRepository
                .findByCloseDateGreaterThan(System.currentTimeMillis());
        Set<Event> userEvents = predictionRepository.findByUser(user.getId())
                .stream().map(Prediction::getEvent).collect(Collectors.toSet());
        allEvents.removeAll(userEvents);
        return allEvents;
    }

}
