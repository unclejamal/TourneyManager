package com.pduda.tourney.domain;

import java.io.Serializable;
import java.util.Date;

public class Game implements Serializable {

    private static final long serialVersionUID = 1L;

    public enum GameState {

        NotStartedYet, Ongoing, Finished
    };
    private GameId id;
    private Table table;
    private Team teamHome;
    private Team teamAway;
    private Team winner;
    private Date startDate;
    private Date endDate;
    private GameState state = GameState.NotStartedYet;

    public Game(String prefix, int round, int match) {
        this.id = new GameId(prefix, round, match);
    }

    public Game(Table table, Team teamHome, Team teamAway) {
        this.table = table;
        this.teamHome = teamHome;
        this.teamAway = teamAway;
    }

    public boolean isInState(GameState state) {
        return state.equals(this.state);
    }

    public GameId getId() {
        return id;
    }

    public void setId(GameId id) {
        this.id = id;
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

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
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
        this.state = GameState.Finished;
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
        this.state = GameState.Ongoing;
        this.startDate = new Date();
    }

    public boolean isWaiting() {
        return ((isInState(GameState.NotStartedYet)) && (teamHome != null) && (teamAway != null) && (winner == null));
    }

    public boolean isOccupied() {
        return (teamHome != null) && (teamAway != null);
    }

    @Override
    public String toString() {
        return "Game{" + "id=" + id + ", table=" + table + ", teamHome=" + teamHome + ", teamAway=" + teamAway + ", winner=" + winner + ", state=" + state + '}';
    }
}
