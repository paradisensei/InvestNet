package org.singularnost.model;

import org.singularnost.model.enums.Direction;

import javax.persistence.*;

/**
 * @author Aidar Shaifutdinov.
 */
@Entity
@Table(name = "event")
@SequenceGenerator(name = "event_gen", sequenceName = "event_seq", allocationSize = 1)
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "event_gen")
    private Long id;

    private String company;

    @Enumerated(EnumType.STRING)
    private Direction direction;

    private long closeDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public long getCloseDate() {
        return closeDate;
    }

    public void setCloseDate(long closeDate) {
        this.closeDate = closeDate;
    }
}
