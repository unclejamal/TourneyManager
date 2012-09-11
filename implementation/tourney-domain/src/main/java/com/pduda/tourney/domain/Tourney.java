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
    private Set<Team> teams = new HashSet<Team>();
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="FIXTURE")
    // TODO use interface
    private Fixture2KO fixture;
    @Enumerated
    @Column(name = "TOURNEY_CATEGORY")
    private TourneyCategory tourneyCategory;

    public Tourney() {
    }

    public Tourney(long id, TourneyCategory category, String name) {
        super();
        this.id = id;
        this.tourneyCategory = category;
        this.name = name;
    }

    public void startTourney() {
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
        fixture.reportWinner(gameCode, winnerTeamCode);
        log.log(Level.INFO, "{0} has reported winner of game {1} - team {2}", new Object[]{this, gameCode, winnerTeamCode});

        if (getWaitingGames().isEmpty()) {
            endTournament();
        }
    }

    private void endTournament() {
        this.endDate = new Date();
        log.log(Level.INFO, "{0} has ended", this);
    }

    public void startGame(GameCode gameCode) {
        Game gameToBeStarted = this.fixture.findGame(gameCode);
        gameToBeStarted.setTable(findFreeTable());
        gameToBeStarted.startGame();
        log.log(Level.INFO, "{0} has started the game {1}", new Object[]{this, gameCode});
    }

    private FoosballTable findFreeTable() {
        // TODO
        FoosballTable freeTable = null;
        for (FoosballTable t : tables) {
            freeTable = t;
        }

        return freeTable;
    }

    public boolean isStarted() {
        return startDate != null;
    }

    public void addTable(FoosballTable table) {
        tables.add(table);
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

        toReturn.setName(this.getName());
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

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Set<FoosballTable> getTables() {
        return tables;
    }

    public Set<Team> getTeams() {
        return teams;
    }

    public TourneyCategory getTourneyCategory() {
        return tourneyCategory;
    }

    public void setTourneyCategory(TourneyCategory tourneyCategory) {
        this.tourneyCategory = tourneyCategory;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Tournament [id=");
        builder.append(id);
        builder.append(", name=");
        builder.append(name);
        builder.append("]");
        return builder.toString();
    }
}
