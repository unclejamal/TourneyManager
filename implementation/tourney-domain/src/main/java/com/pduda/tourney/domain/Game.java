package com.pduda.tourney.domain;

import com.pduda.tourney.infrastructure.gson.GsonExclude;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Configurable;

@Entity
@javax.persistence.Table(name = "GAME")
@Configurable(autowire = Autowire.BY_TYPE)
public class Game implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "GAME_ID")
    private long id;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @PrimaryKeyJoinColumn
    private GameCode gameCode;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "FOOSBALL_TABLE")
    private FoosballTable table;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "TEAM_HOME")
    private Team teamHome;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "TEAM_AWAY")
    private Team teamAway;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "WINNER")
    private Team winner;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "START_DATE")
    private Date startDate;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "END_DATE")
    private Date endDate;
    @Enumerated
    @Column(name = "GAME_STATE")
    private GameState gameState = GameState.NotStartedYet;
    @GsonExclude
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="TOURNEY_EVENT")
    private TourneyEvent tourneyEvent;

    public Game(TourneyEvent tourneyEvent, String prefix, int round, int match) {
        this.gameCode = new GameCode(prefix, round, match);
        this.tourneyEvent = tourneyEvent;
    }

    /**
     * Just for JPA.
     *
     * @deprecated
     */
    public Game() {
    }

    public boolean isInGameState(GameState state) {
        return state.equals(this.gameState);
    }

    public GameCode getGameCode() {
        return gameCode;
    }

    public void setGameCode(GameCode gameCode) {
        this.gameCode = gameCode;
    }

    public Team getTeamAway() {
        return teamAway;
    }

    public void setTeamAway(Team teamAway) {
        this.teamAway = teamAway;
    }

    public Team getTeamHome() {
        return teamHome;
    }

    public void setTeamHome(Team teamHome) {
        this.teamHome = teamHome;
    }

    public FoosballTable getTable() {
        return table;
    }

    public void setTable(FoosballTable table) {
        this.table = table;
    }

    public Team getWinner() {
        return winner;
    }

    public Team getLoser() {
        if (winner != null) {
            if (winner == teamHome) {
                return teamAway;
            } else {
                return teamHome;
            }
        }

        return null;
    }

    public void setWinner(Team winner) {
        this.gameState = GameState.Finished;
        this.winner = winner;
        this.endDate = new Date();
    }

    public Date getEndDate() {
        return endDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void startGame() {
        this.gameState = GameState.Ongoing;
        this.startDate = new Date();
    }

    public boolean isWaiting() {
        return ((isInGameState(GameState.NotStartedYet)) && (teamHome != null) && (teamAway != null) && (winner == null));
    }

    public boolean isOccupied() {
        return (teamHome != null) && (teamAway != null);
    }

    public boolean isOrphan() {
        return (teamHome == null) && (teamAway == null);
    }

    public TourneyEvent getTourneyEvent() {
        return tourneyEvent;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Game{" + "gameCode=" + gameCode + ", table=" + table + ", teamHome=" + teamHome + ", teamAway=" + teamAway + ", winner=" + winner + ", state=" + gameState + '}';
    }
}
