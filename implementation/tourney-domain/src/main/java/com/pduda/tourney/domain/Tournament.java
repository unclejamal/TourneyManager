package com.pduda.tourney.domain;

import com.pduda.tourney.domain.report.Standings;
import com.pduda.tourney.domain.fixture.twoko.Fixture2KO;
import com.pduda.tourney.domain.report.FullGamesReport;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.*;

import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Configurable;

@Entity
@javax.persistence.Table(name = "TOURNAMENT")
@Configurable(autowire = Autowire.BY_TYPE)
public class Tournament implements Serializable {

    private static final long serialVersionUID = 1L;
    private transient Logger log = Logger.getLogger(Tournament.class.getClass().getName());
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private int id;
    @Column(name = "NAME")
    private String name;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "START_DATE")
    private Date startDate;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "END_DATE")
    private Date endDate;
    private List<Table> tables = new ArrayList<Table>();
    private List<Team> teams = new ArrayList<Team>();
    private Fixture fixture;
    private TournamentCategory category;

    public Tournament() {
    }

    public Tournament(int id, TournamentCategory category, String name) {
        super();
        this.id = id;
        this.category = category;
        this.name = name;
    }

    public void startTournament() {
        log.log(Level.INFO, "{0} started ", this);
        this.startDate = new Date();
        this.fixture = new Fixture2KO(teams);
    }

    public void reportWinner(GameId gameId, int teamSeed) {
        fixture.reportWinner(gameId, teamSeed);
        log.log(Level.INFO, "{0} has reported winner of game {1} - team {2}", new Object[]{this, gameId, teamSeed});

        if (getWaitingGames().isEmpty()) {
            endTournament();
        }
    }

    private void endTournament() {
        this.endDate = new Date();
        log.log(Level.INFO, "{0} has ended", this);
    }

    public void startGame(GameId gameId) {
        Game gameToBeStarted = this.fixture.findGame(gameId);
        gameToBeStarted.setTable(findFreeTable());
        gameToBeStarted.startGame();
        log.log(Level.INFO, "{0} has started the game {1}", new Object[]{this, gameId});
    }

    private Table findFreeTable() {
        // TODO
        Table freeTable = null;
        for (Table t : tables) {
            freeTable = t;
        }

        return freeTable;
    }

    public boolean getStarted() {
        return startDate != null;
    }

    public void addTable(Table table) {
        tables.add(table);
    }

    public void addTeam(Team team) {
        teams.add(team);
    }

    public Standings getStandings() {
        if (!getStarted()) {
            return new Standings();
        }

        return fixture.getStandings();
    }

    public FullGamesReport getGamesReports() {
        FullGamesReport toReturn = null;
        if (!getStarted()) {
            toReturn = new FullGamesReport();
        } else {
            toReturn = fixture.getGamesReports();
        }

        toReturn.setName(this.getName());
        return toReturn;
    }

    public List<Game> getOngoingGames() {
        if (getStarted()) {
            return fixture.getOngoingGames();
        } else {
            return new ArrayList<Game>();
        }
    }

    public List<Game> getWaitingGames() {
        if (getStarted()) {
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Table> getTables() {
        return tables;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public TournamentCategory getCategory() {
        return category;
    }

    public void setCategory(TournamentCategory category) {
        this.category = category;
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
