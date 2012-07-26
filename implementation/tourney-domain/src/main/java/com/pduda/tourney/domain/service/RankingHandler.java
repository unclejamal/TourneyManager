package com.pduda.tourney.domain.service;

import com.pduda.tourney.domain.ranking.Ranking;

public interface RankingHandler {

    Ranking getOpenSingle();

    Ranking getOpenDouble();

    Ranking getWomenSingle();

    Ranking getWomenDouble();
}
