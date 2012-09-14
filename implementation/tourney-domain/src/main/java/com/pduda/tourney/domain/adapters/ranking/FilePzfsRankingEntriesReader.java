package com.pduda.tourney.domain.adapters.ranking;

import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;

@Named
public class FilePzfsRankingEntriesReader implements PzfsRankingEntriesReader {

    public static final String HOME = System.getProperty("user.home");
    public static final String FILE_OS = HOME + "/progs/tourneyManager/os.csv";
    public static final String FILE_OD = HOME + "/progs/tourneyManager/od.csv";
    public static final String FILE_WS = HOME + "/progs/tourneyManager/ws.csv";
    public static final String FILE_WD = HOME + "/progs/tourneyManager/wd.csv";
    @Inject
    private CsvRankingReader csvRankingReader;

    @Override
    public List<RankingEntry> loadOs() {
        return csvRankingReader.loadFromFile(FILE_OS);
    }

    @Override
    public List<RankingEntry> loadOd() {
        return csvRankingReader.loadFromFile(FILE_OD);
    }

    @Override
    public List<RankingEntry> loadWs() {
        return csvRankingReader.loadFromFile(FILE_WS);
    }

    @Override
    public List<RankingEntry> loadWd() {
        return csvRankingReader.loadFromFile(FILE_WD);
    }
}
