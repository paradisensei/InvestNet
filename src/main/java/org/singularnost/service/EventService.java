package org.singularnost.service;

import org.singularnost.model.Event;
import org.singularnost.model.User;

import java.util.List;

/**
 * @author Aidar Shaifutdinov.
 */
public interface EventService {

    List<Event> getActive(User user);

    void checkEvent();

}
