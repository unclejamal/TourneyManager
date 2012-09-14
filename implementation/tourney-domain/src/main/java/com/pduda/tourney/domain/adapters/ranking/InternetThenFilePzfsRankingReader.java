package com.pduda.tourney.domain.adapters.ranking;

import com.pduda.tourney.domain.PlayerDescription;
import com.pduda.tourney.domain.ranking.PzfsRanking;
import com.pduda.tourney.domain.ranking.PzfsRankingReader;
import com.pduda.tourney.domain.ranking.Ranking;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;

@Named
public class InternetThenFilePzfsRankingReader implements PzfsRankingReader {

    @Inject
    private CsvRankingReader csvRankingReader;
    private PzfsRankingEntriesReader reader;
    private RankingEntryConverter converter = new RankingEntryConverter();

    @Override
    public PzfsRanking loadPzfsRanking() {
        List<PlayerDescription> playerDescriptions = new ArrayList<PlayerDescription>();
        
        Ranking openSingle = createRanking(reader.loadOs());
        Ranking openDouble = createRanking(reader.loadOd());
        Ranking womenSingle = createRanking(reader.loadWs());
        Ranking womenDouble = createRanking(reader.loadWd());

        PzfsRanking pzfsRanking = new PzfsRanking(openSingle, openDouble, womenSingle, womenDouble);

        return pzfsRanking;
    }

    void setRankingReader(PzfsRankingEntriesReader reader) {
        this.reader = reader;
    }

    private Ranking createRanking(List<RankingEntry> entries) {
        Ranking openSingle = converter.convert(entries);
        return openSingle;
    }
}
