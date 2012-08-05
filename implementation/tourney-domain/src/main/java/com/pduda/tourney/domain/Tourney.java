package com.pduda.tourney.domain;

import com.pduda.tourney.domain.report.Standings;
import com.pduda.tourney.domain.fixture.twoko.Fixture2KO;
import com.pduda.tourney.domain.report.FullGamesReport;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashSet;
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
    @Transient
    private List<Table> tables = new ArrayList<Table>();
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "ID")
    private Set<Team> teams = new LinkedHashSet<Team>();
    @Transient
    private Fixture fixture;
    @Transient
    private TourneyCategory tournamentCategory;
    
    public Tourney() {
    }
    
    public Tourney(int id, TourneyCategory category, String name) {
        super();
        this.id = id;
        this.tournamentCategory = category;
        this.name = name;
    }
    
    public void startTournament() {
        log.log(Level.INFO, "{0} started ", this);
        this.startDate = new Date();
        
        assignTeamCodes(teams);
        this.fixture = new Fixture2KO(teams);
    }
    
    private void assignTeamCodes(Set<Team> teams) {
        int i = 0;
        for (Team team : teams) {
            team.setTeamCode(i);
            i++;
        }
    }
    
    public void reportWinner(GameId gameId, int winnerId) {
        fixture.reportWinner(gameId, winnerId);
        log.log(Level.INFO, "{0} has reported winner of game {1} - team {2}", new Object[]{this, gameId, winnerId});
        
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
    
    public Set<Team> getTeams() {
        return teams;
    }
    
    public TourneyCategory getTournamentCategory() {
        return tournamentCategory;
    }
    
    public void setTournamentCategory(TourneyCategory tournamentCategory) {
        this.tournamentCategory = tournamentCategory;
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
