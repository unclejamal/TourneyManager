package com.pduda.tourney.domain;

import com.pduda.tourney.domain.fixture.twoko.Fixture2KO;
import com.pduda.tourney.domain.report.FullGamesReport;
import com.pduda.tourney.domain.report.Standings;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.*;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Configurable;

@Entity
@javax.persistence.Table(name = "TOURNEY_EVENT")
@Configurable(autowire = Autowire.BY_TYPE)
public class TourneyEvent implements Serializable {

    private static final long serialVersionUID = 1L;
    private transient Logger log = Logger.getLogger(TourneyEvent.class.getClass().getName());
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "TOURNEY_EVENT_ID")
    private long id;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "START_DATE")
    private Date startDate;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "END_DATE")
    private Date endDate;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @PrimaryKeyJoinColumn
    private Set<Team> teams = new HashSet<Team>();
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "FIXTURE")
    // TODO use interface
    private Fixture2KO fixture;
    @Enumerated
    @Column(name = "TOURNEY_EVENT_CATEGORY")
    private EventCategory eventCategory;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Tourney tourney;

    public TourneyEvent(Tourney tourney, EventCategory category) {
        super();
        this.tourney = tourney;
        this.eventCategory = category;
    }

    public TourneyEvent() {
        //JPA
    }

    public void startEvent() {
        log.log(Level.INFO, "{0} started ", this);
        this.startDate = new Date();

        assignTeamCodes(teams);
        this.fixture = new Fixture2KO(this);
    }

    private void assignTeamCodes(Set<Team> teams) {
        int i = 0;
        for (Team team : teams) {
            team.setTeamCode(i);
            i++;
        }
    }

    public void reportWinner(GameCode gameCode, long winnerTeamCode) {
        Team winner = findTeam(winnerTeamCode);
        fixture.reportWinner(gameCode, winner);
        log.log(Level.INFO, "{0} has reported winner of game {1} - team {2}", new Object[]{this, gameCode, winner});

        if (!fixture.anyGameLeft()) {
            endEvent();
        }
    }

    private void endEvent() {
        this.endDate = new Date();
        log.log(Level.INFO, "{0} has ended", this);
    }

    public void startGame(GameCode gameCode) {
        this.fixture.startGame(gameCode, tourney.findFreeTable());
        log.log(Level.INFO, "{0} has started the game {1}", new Object[]{this, gameCode});
    }

    private Team findTeam(long teamCode) {
        for (Team team : teams) {
            if (team.getTeamCode() == teamCode) {
                return team;
            }
        }

        throw new RuntimeException("team not found");
    }

    public boolean isStarted() {
        return startDate != null;
    }

    public void addTeam(Team team) {
        teams.add(team);
    }

    public Standings getStandings() {
        if (!isStarted()) {
            return new Standings();
        }

        return fixture.getStandings();
    }

    public FullGamesReport getGamesReports() {
        FullGamesReport toReturn = null;
        if (!isStarted()) {
            toReturn = new FullGamesReport();
        } else {
            toReturn = fixture.getGamesReports();
        }

        toReturn.setName(this.getEventCategory().toString());
        return toReturn;
    }

    public List<Game> getOngoingGames() {
        if (isStarted()) {
            return fixture.getOngoingGames();
        } else {
            return new ArrayList<Game>();
        }
    }

    public List<Game> getWaitingGames() {
        if (isStarted()) {
            return fixture.getWaitingGames();
        } else {
            return new ArrayList<Game>();
        }
    }

    public Fixture getFixture() {
        return fixture;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Set<Team> getTeams() {
        return teams;
    }

    public EventCategory getEventCategory() {
        return eventCategory;
    }

    public void setTourneyCategory(EventCategory eventCategory) {
        this.eventCategory = eventCategory;
    }

    @Override
    public String toString() {
        return "Event{" + "id=" + id + ", eventCategory=" + eventCategory + '}';
    }
}
