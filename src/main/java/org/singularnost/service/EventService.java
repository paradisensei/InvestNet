package org.singularnost.service;

import org.singularnost.model.Event;
import org.singularnost.model.User;

import java.util.List;

/**
 * @author Aidar Shaifutdinov.
 */
public interface EventService {

    Event getOne(long id);

    List<Event> getActive(User user);

    List<Event> getPassed();

}
