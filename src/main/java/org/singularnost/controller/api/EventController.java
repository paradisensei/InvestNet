package org.singularnost.controller.api;

import com.jayway.awaitility.core.ConditionTimeoutException;
import org.singularnost.dto.ApiResponse;
import org.singularnost.model.Event;
import org.singularnost.model.User;
import org.singularnost.service.EventService;
import org.singularnost.service.PredictionService;
import org.singularnost.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.jayway.awaitility.Awaitility.await;
import static com.jayway.awaitility.pollinterval.IterativePollInterval.iterative;

/**
 * @author Aidar Shaifutdinov.
 */
@RestController
@RequestMapping("/events")
public class EventController {

    private final UserService userService;
    private final EventService eventService;
    private final PredictionService predictionService;

    @Autowired
    public EventController(UserService userService, EventService eventService,
                           PredictionService predictionService) {
        this.userService = userService;
        this.eventService = eventService;
        this.predictionService = predictionService;
    }

    @RequestMapping("")
    public ApiResponse<List<Event>> getActive(@RequestParam("token") String token) {
        await().with()
                .pollInterval(iterative(duration -> duration.plus(1000)))
                .atMost(7, TimeUnit.SECONDS)
                .until(() -> userService.get(token) != null);
        User user = userService.get(token);
        return new ApiResponse<>(eventService.getActive(user));
    }

    @RequestMapping(value = "/{eventId}/predictions")
    public void predict(@PathVariable long eventId,
                        @RequestParam("token") String token,
                        @RequestParam("prediction") int prediction) {
        User user = userService.get(token);
        Event event = eventService.getOne(eventId);
        predictionService.add(user, event, prediction);
    }

    @ExceptionHandler(ConditionTimeoutException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ApiResponse handleException(ConditionTimeoutException e) {
        return new ApiResponse("Auth failed!");
    }

}
