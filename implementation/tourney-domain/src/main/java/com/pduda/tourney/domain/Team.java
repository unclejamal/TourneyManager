package com.pduda.tourney.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import javax.persistence.*;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Configurable;

@Entity
@javax.persistence.Table(name = "TEAM")
@Configurable(autowire = Autowire.BY_TYPE)
public class Team implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "TEAM_ID")
    private long id;
    @Column(name = "TEAM_CODE")
    private int teamCode;
    @Column(name = "SEED")
    private int seed = Integer.MAX_VALUE;
    @Column(name = "NAME")
    private String name;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private EventPlayer eventPlayerOne;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private EventPlayer eventPlayerTwo;

    public Team(EventPlayer... memberz) {
        eventPlayerOne = memberz[0];
        if (memberz.length == 2) {
            eventPlayerTwo = memberz[1];
        }
        this.name = computeName();
    }

    public Team() {
    }

    private String computeName() {
        StringBuilder sb = new StringBuilder();
        sb.append(eventPlayerOne.getShortName());

        if (isDouble()) {
            sb.append(", ");
            sb.append(eventPlayerTwo.getShortName());
        }

        return sb.toString();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getTeamCode() {
        return teamCode;
    }

    public void setTeamCode(int teamCode) {
        this.teamCode = teamCode;
    }

    public int getSeed() {
        return seed;
    }

    public boolean isSeeded() {
        return seed != Integer.MAX_VALUE;
    }

    public void setSeed(int seed) {
        this.seed = seed;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public EventPlayer getEventPlayerOne() {
        return eventPlayerOne;
    }

    public EventPlayer getEventPlayerTwo() {
        return eventPlayerTwo;
    }

    public int getPoints() {
        int points1 = eventPlayerOne.getPoints();
        int points2 = 0;
        if (isDouble()) {
            points2 = eventPlayerTwo.getPoints();
        }

        return Math.max(points1, points2) + (int) (Math.min(points1, points2) / 2);
    }

    public boolean isSingle() {
        return (eventPlayerOne != null) && (eventPlayerTwo == null);
    }

    public boolean isDouble() {
        return (eventPlayerOne != null) && (eventPlayerTwo != null);
    }

    public int getSize() {
        if (isSingle()) {
            return 1;
        } else if (isDouble()) {
            return 2;
        }

        throw new RuntimeException();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("Team {");
        sb.append("id: ").append(id).append(", ");
        sb.append("name: ").append(name).append(", ");
        sb.append("}");

        return sb.toString();
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + Objects.hashCode(this.eventPlayerOne);
        hash = 37 * hash + Objects.hashCode(this.eventPlayerTwo);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Team other = (Team) obj;
        if (!Objects.equals(this.eventPlayerOne, other.eventPlayerOne)) {
            return false;
        }
        if (!Objects.equals(this.eventPlayerTwo, other.eventPlayerTwo)) {
            return false;
        }
        return true;
    }
}
