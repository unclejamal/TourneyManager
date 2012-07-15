package com.pduda.tourney.web;

import com.pduda.tourney.domain.Player;
import java.io.Serializable;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Scope;

import com.pduda.tourney.domain.ranking.Ranking;
import com.pduda.tourney.domain.ranking.Rankings;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;

@Named("ranking")
@Scope("session")
public class RankingBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final transient Logger log = Logger.getLogger(RankingBean.class.getClass().getName());
    @Inject
    private Rankings rankings;
    private Ranking shown;
    private Ranking os;
    private Ranking od;
    private Ranking ws;
    private Ranking wd;
    private Player filter = new Player();
    private List<String> osSuggestions = new ArrayList<String>();
    private List<String> odSuggestions = new ArrayList<String>();
    private List<String> wsSuggestions = new ArrayList<String>();
    private List<String> wdSuggestions = new ArrayList<String>();

    @PostConstruct
    public void init() {
        loadOpenSingle();
        loadOpenDouble();
        loadWomenSingle();
        loadWomenDouble();
    }

    private void loadOpenSingle() {
        os = rankings.getOpenSingle();

        for (Player player : os.getPlayers()) {
            osSuggestions.add(player.getFullName());
        }
    }

    private void loadOpenDouble() {
        od = rankings.getOpenDouble();

        for (Player player : od.getPlayers()) {
            odSuggestions.add(player.getFullName());
        }
    }

    private void loadWomenSingle() {
        ws = rankings.getWomenSingle();

        for (Player player : ws.getPlayers()) {
            wsSuggestions.add(player.getFullName());
        }
    }

    private void loadWomenDouble() {
        wd = rankings.getWomenDouble();

        for (Player player : wd.getPlayers()) {
            wdSuggestions.add(player.getFullName());
        }
    }

    public Ranking getOd() {
        return od;
    }

    public Ranking getOs() {
        return os;
    }

    public Ranking getWd() {
        return wd;
    }

    public Ranking getWs() {
        return ws;
    }
    
    public List<String> getOdSuggestions() {
        return odSuggestions;
    }

    public List<String> getOsSuggestions() {
        return osSuggestions;
    }

    public List<String> getWsSuggestions() {
        return wsSuggestions;
    }

    public List<String> getWdSuggestions() {
        return wdSuggestions;
    }

    public Player getFilter() {
        return filter;
    }

    public void setFilter(Player filter) {
        this.filter = filter;
    }
}