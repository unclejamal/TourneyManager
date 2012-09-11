package com.pduda.tourney.domain.repository;

import com.pduda.tourney.domain.ranking.PzfsRanking;

public interface PzfsRankingRepo extends BaseRepo<PzfsRanking, Long> {

    PzfsRanking getNewestPzfsRanking();
}
