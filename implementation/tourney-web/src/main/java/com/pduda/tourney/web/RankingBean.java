package com.pduda.tourney.web;

import com.pduda.tourney.domain.RankingPlayer;
import com.pduda.tourney.domain.ranking.Ranking;
import com.pduda.tourney.domain.service.RankingHandler;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import org.springframework.context.annotation.Scope;

@Named("ranking")
@Scope("session")
public class RankingBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final transient Logger log = Logger.getLogger(RankingBean.class.getClass().getName());
    @Inject
    private RankingHandler rankingHandler;
    private Ranking shown;
    private Ranking os;
    private Ranking od;
    private Ranking ws;
    private Ranking wd;
    private RankingPlayer filter = new RankingPlayer();
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
        os = rankingHandler.getPzfsRanking().getOpenSingle();

        for (RankingPlayer player : os.getPlayers()) {
            osSuggestions.add(player.getFullName());
        }
    }

    private void loadOpenDouble() {
        od = rankingHandler.getPzfsRanking().getOpenDouble();

        for (RankingPlayer player : od.getPlayers()) {
            odSuggestions.add(player.getFullName());
        }
    }

    private void loadWomenSingle() {
        ws = rankingHandler.getPzfsRanking().getWomenSingle();

        for (RankingPlayer player : ws.getPlayers()) {
            wsSuggestions.add(player.getFullName());
        }
    }

    private void loadWomenDouble() {
        wd = rankingHandler.getPzfsRanking().getWomenDouble();

        for (RankingPlayer player : wd.getPlayers()) {
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

    public RankingPlayer getFilter() {
        return filter;
    }

    public void setFilter(RankingPlayer filter) {
        this.filter = filter;
    }
}