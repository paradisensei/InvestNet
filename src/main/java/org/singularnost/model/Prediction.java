package org.singularnost.model;

import javax.persistence.*;

/**
 * @author Aidar Shaifutdinov.
 */
@Entity
@Table(name = "prediction")
@SequenceGenerator(name = "prediction_gen", sequenceName = "prediction_seq", allocationSize = 1)
public class Prediction {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "prediction_gen")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

    private int prediction;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public int getPrediction() {
        return prediction;
    }

    public void setPrediction(int prediction) {
        this.prediction = prediction;
    }
}
