package com.pduda.tourney.domain.adapters.ranking;

import java.util.List;

public interface PzfsRankingEntriesReader {

    List<RankingEntry> loadOs();

    List<RankingEntry> loadOd();

    List<RankingEntry> loadWs();

    List<RankingEntry> loadWd();
}
