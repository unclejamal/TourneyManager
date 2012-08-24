package com.pduda.tourney.domain.ranking;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

@Named
public class Rankings {

    public static final String HOME = System.getProperty("user.home");
    @Inject
    private RankingCsvLoader loader;
    private Ranking openSingle;
    private Ranking openDouble;
    private Ranking womenSingle;
    private Ranking womenDouble;

    @PostConstruct
    public void loadRankings() {
        openSingle = loader.loadFromFile(HOME + "/progs/tourneyManager/os.csv");
        openDouble = loader.loadFromFile(HOME + "/progs/tourneyManager/od.csv");
        womenSingle = loader.loadFromFile(HOME + "/progs/tourneyManager/ws.csv");
        womenDouble = loader.loadFromFile(HOME + "/progs/tourneyManager/wd.csv");
    }

    public Ranking getOpenDouble() {
        return openDouble;
    }

    public Ranking getOpenSingle() {
        return openSingle;
    }

    public Ranking getWomenSingle() {
        return womenSingle;
    }

    public Ranking getWomenDouble() {
        return womenDouble;
    }
}
