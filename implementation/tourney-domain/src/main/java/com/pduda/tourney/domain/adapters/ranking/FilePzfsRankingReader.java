package com.pduda.tourney.domain.adapters.ranking;

import com.pduda.tourney.domain.ranking.PzfsRanking;
import com.pduda.tourney.domain.ranking.PzfsRankingReader;
import javax.inject.Inject;
import javax.inject.Named;

@Named
public class FilePzfsRankingReader implements PzfsRankingReader {

    public static final String HOME = System.getProperty("user.home");
    public static final String FILE_OS = HOME + "/progs/tourneyManager/os.csv";
    public static final String FILE_OD = HOME + "/progs/tourneyManager/od.csv";
    public static final String FILE_WS = HOME + "/progs/tourneyManager/ws.csv";
    public static final String FILE_WD = HOME + "/progs/tourneyManager/wd.csv";
    @Inject
    private CsvRankingReader csvRankingReader;

    @Override
    public PzfsRanking loadPzfsRanking() {
//        Ranking openSingle = csvRankingReader.loadFromFile(FILE_OS);
//        Ranking openDouble = csvRankingReader.loadFromFile(FILE_OD);
//        Ranking womenSingle = csvRankingReader.loadFromFile(FILE_WS);
//        Ranking womenDouble = csvRankingReader.loadFromFile(FILE_WD);
//
//        PzfsRanking pzfsRanking = new PzfsRanking(openSingle, openDouble, womenSingle, womenDouble);
//        return pzfsRanking;
        return null;
    }
}
