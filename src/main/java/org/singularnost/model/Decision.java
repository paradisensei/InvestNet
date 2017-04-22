package org.singularnost.model;

import org.singularnost.model.enums.Direction;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import static org.singularnost.model.Decision.DECISION;
import static org.singularnost.model.Decision.DECISION_GEN;
import static org.singularnost.model.Decision.DECISION_SEQ;

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

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = DECISION_GEN)
    private Long id;

    @OneToOne
    @JoinColumn(name = "event_id")
    private Event event;

    @Enumerated(EnumType.STRING)
    private Direction direction;

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

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
}
