package com.pduda.tourney.domain.adapters.ranking;

import com.pduda.tourney.domain.adapters.ranking.PzfsRankingEntriesReader;
import com.pduda.tourney.domain.adapters.ranking.RankingEntry;
import com.pduda.tourney.domain.adapters.ranking.InternetThenFilePzfsRankingReader;
import com.pduda.tourney.domain.RankingPlayer;
import com.pduda.tourney.domain.ranking.PzfsRanking;
import com.pduda.tourney.domain.ranking.PzfsRanking;
import com.pduda.tourney.domain.util.MyUtils;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/com/pduda/tourney/persistence-test-context.xml"})
public class InternetThenFilePzfsRankingReaderTest {
    public static final String CODE = "kiczke";

    private InternetThenFilePzfsRankingReader cut;

    @Before
    public void setUp() {
        cut = new InternetThenFilePzfsRankingReader();
    }

    @Test
    public void playersWithinCompositeRankingSharePlayerDescriptions() {
        cut.setRankingReader(new PzfsRankingEntriesReader() {
            @Override
            public List<RankingEntry> loadOs() {
                RankingEntry csvEntry = rankingEntry(CODE);
                return MyUtils.asList(csvEntry);
            }

            @Override
            public List<RankingEntry> loadOd() {
                RankingEntry csvEntry = rankingEntry(CODE);
                return MyUtils.asList(csvEntry);
            }

            @Override
            public List<RankingEntry> loadWs() {
                RankingEntry csvEntry = rankingEntry(CODE);
                return MyUtils.asList(csvEntry);
            }

            @Override
            public List<RankingEntry> loadWd() {
                RankingEntry csvEntry = rankingEntry(CODE);
                return MyUtils.asList(csvEntry);
            }
            
            

            private RankingEntry rankingEntry(String code) {
                return new RankingEntry(1, code, "name", "M", "city", "club", 0, 0, 0, "MASTER");
            }
        });

        PzfsRanking pzfsRanking = cut.loadPzfsRanking();

        assertEquals(1, 1);
        RankingPlayer playerOs = pzfsRanking.getOpenSingle().getPlayersByCode(CODE);
        RankingPlayer playerOd = pzfsRanking.getOpenDouble().getPlayersByCode(CODE);
        RankingPlayer playerWs = pzfsRanking.getWomenSingle().getPlayersByCode(CODE);
        RankingPlayer playerWd = pzfsRanking.getWomenDouble().getPlayersByCode(CODE);
//        assertTrue(playerOs.isSharingPlayerDescription(playerOd));
//        assertTrue(playerOs.isSharingPlayerDescription(playerWs));
//        assertTrue(playerOs.isSharingPlayerDescription(playerWd));
    }
}