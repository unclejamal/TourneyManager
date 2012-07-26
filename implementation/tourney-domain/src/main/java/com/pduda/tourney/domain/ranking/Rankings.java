package com.pduda.tourney.domain.ranking;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

@Named
public class Rankings {
    
    @Inject
    private RankingCsvLoader loader;
    
    private Ranking openSingle;
    private Ranking openDouble;
    private Ranking womenSingle;
    private Ranking womenDouble;
    
    @PostConstruct
    public void loadRankings() {
        openSingle = loader.loadFromFile("D:\\lipiec-os.csv");
        openDouble = loader.loadFromFile("D:\\lipiec-od.csv");
        womenSingle = loader.loadFromFile("D:\\lipiec-ws.csv");        
        womenDouble = loader.loadFromFile("D:\\lipiec-wd.csv");        
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
