package com.pduda.tourney.domain.adapters.ranking;

import com.pduda.tourney.domain.ranking.PzfsRanking;
import com.pduda.tourney.domain.ranking.PzfsRankingReader;
import com.pduda.tourney.domain.ranking.Ranking;
import javax.inject.Inject;
import javax.inject.Named;

@Named
public class FilePzfsRankingReader implements PzfsRankingReader {

    public static final String HOME = System.getProperty("user.home");
    @Inject
    private CsvRankingReader csvRankingReader;

    @Override
    public PzfsRanking loadPzfsRanking() {
        Ranking openSingle = csvRankingReader.loadFromFile(HOME + "/progs/tourneyManager/os.csv");
        Ranking openDouble = csvRankingReader.loadFromFile(HOME + "/progs/tourneyManager/od.csv");
        Ranking womenSingle = csvRankingReader.loadFromFile(HOME + "/progs/tourneyManager/ws.csv");
        Ranking womenDouble = csvRankingReader.loadFromFile(HOME + "/progs/tourneyManager/wd.csv");

        PzfsRanking pzfsRanking = new PzfsRanking(openSingle, openDouble, womenSingle, womenDouble);
        return pzfsRanking;
    }
}
