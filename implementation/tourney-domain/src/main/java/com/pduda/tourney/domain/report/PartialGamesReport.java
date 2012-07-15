package com.pduda.tourney.domain.report;

import com.pduda.tourney.domain.Team;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PartialGamesReport {

    private String name;
    private Team winner;
    private List<GamesReportRound> rounds = new ArrayList<GamesReportRound>();

    public Team getWinner() {
        return winner;
    }

    public void setWinner(Team winner) {
        this.winner = winner;
    }

    public List<GamesReportRound> getRounds() {
        return rounds;
    }

    public void setRounds(List<GamesReportRound> rounds) {
        this.rounds = rounds;
    }

    public void addRound(GamesReportRound round) {
        this.rounds.add(round);
        Collections.sort(rounds, new RoundComparator());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public GamesReportRound getRound(String name) {
        for (GamesReportRound round : rounds) {
            if (name.equals(round.getName())) {
                return round;
            }
        }

        return null;
    }
}
