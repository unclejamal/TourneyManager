package com.pduda.tourney.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;
import javax.persistence.*;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Configurable;

@Entity
@javax.persistence.Table(name = "TOURNEY")
@Configurable(autowire = Autowire.BY_TYPE)
public class Tourney implements Serializable {

    private static final long serialVersionUID = 1L;
    private transient Logger log = Logger.getLogger(Tourney.class.getClass().getName());
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "TOURNEY_ID")
    private long id;
    @Column(name = "NAME")
    private String name;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "START_DATE")
    private Date startDate;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "END_DATE")
    private Date endDate;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @PrimaryKeyJoinColumn
    private Set<FoosballTable> tables = new HashSet<FoosballTable>();
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @PrimaryKeyJoinColumn
    private Set<TourneyEvent> events = new HashSet<TourneyEvent>();

    public Tourney(String name) {
        super();
        this.name = name;
    }

    /**
     * JPA only.
     *
     * @deprecated
     */
    public Tourney() {
    }

    public FoosballTable findFreeTable() {
        // TODO
        FoosballTable freeTable = null;
        for (FoosballTable t : tables) {
            freeTable = t;
        }

        return freeTable;
    }

    public void addTable(FoosballTable table) {
        tables.add(table);
    }

    public void addEvent(TourneyEvent event) {
        events.add(event);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Tourney [id=");
        builder.append(id);
        builder.append(", name=");
        builder.append(name);
        builder.append("]");
        return builder.toString();
    }

    public Set<TourneyEvent> getEvents() {
        return events;
    }

    public List<Game> getWaitingGames() {
        List<Game> waitingGames = new ArrayList<Game>();
        for (TourneyEvent event : events) {
            waitingGames.addAll(event.getWaitingGames());
        }

        return waitingGames;
    }

    public List<Game> getOngoingGames() {
        List<Game> ongoingGames = new ArrayList<Game>();
        for (TourneyEvent event : events) {
            ongoingGames.addAll(event.getOngoingGames());
        }

        return ongoingGames;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Set<FoosballTable> getTables() {
        return tables;
    }
}
