package com.pduda.tourney.domain.report;

import com.pduda.tourney.domain.Game;
import java.util.ArrayList;
import java.util.List;

public class GamesReportRound {

    private int order;
    private String name;
    private List<Game> games = new ArrayList<Game>();

    public GamesReportRound(String name, int order) {
        this.name = name;
        this.order = order;
    }

    public List<Game> getGames() {
        return games;
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }

    public void addGame(Game game) {
        this.games.add(game);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }
}
