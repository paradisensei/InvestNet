package org.singularnost.controller.api;

import org.singularnost.dto.ApiResponse;
import org.singularnost.model.Event;
import org.singularnost.model.User;
import org.singularnost.service.EventService;
import org.singularnost.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Aidar Shaifutdinov.
 */
@RestController
@RequestMapping("/events")
public class EventController {

    private final UserService userService;
    private final EventService eventService;

    @Autowired
    public EventController(UserService userService, EventService eventService) {
        this.userService = userService;
        this.eventService = eventService;
    }

    @RequestMapping("/")
    public ApiResponse<List<Event>> getActive(@RequestParam("token") String token) {
        User user = userService.get(token);
        return new ApiResponse<>(eventService.getActive(user));
    }

}
