package org.singularnost.model;

import javax.persistence.*;

import static org.singularnost.model.Decision.*;

/**
 * @author aleksandrpliskin on 22.04.17.
 */
@Entity
@Table(name = DECISION)
@SequenceGenerator(name = DECISION_GEN, sequenceName = DECISION_SEQ, allocationSize = 1)
public class Decision {

    static final String DECISION = "decision";
    static final String DECISION_GEN = "decision_gen";
    static final String DECISION_SEQ = "decision_seq";

    public Decision() {
    }

    public Decision(Event event, int prediction) {
        this.event = event;
        this.prediction = prediction;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = DECISION_GEN)
    private Long id;

    @OneToOne
    @JoinColumn(name = "event_id")
    private Event event;

    private int prediction;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
