package com.pduda.tourney.domain.service;

import com.pduda.tourney.domain.ranking.Ranking;
import com.pduda.tourney.domain.ranking.Rankings;
import java.io.Serializable;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.inject.Named;

@Named
public class DefaultRankingHandler implements RankingHandler, Serializable {

    private static final long serialVersionUID = 1L;
    private transient Logger log = Logger.getLogger(DefaultRankingHandler.class.getName());
    @Inject
    private Rankings rankings;

    @Override
    public Ranking getOpenSingle() {
        return rankings.getOpenSingle();
    }

    @Override
    public Ranking getOpenDouble() {
        return rankings.getOpenDouble();
    }

    @Override
    public Ranking getWomenSingle() {
        return rankings.getWomenSingle();
    }

    @Override
    public Ranking getWomenDouble() {
        return rankings.getWomenDouble();
    }
}
