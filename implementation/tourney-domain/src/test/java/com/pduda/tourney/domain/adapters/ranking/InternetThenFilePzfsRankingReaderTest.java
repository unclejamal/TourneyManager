package com.pduda.tourney.domain.adapters.ranking;

import com.pduda.tourney.domain.RankingPlayer;
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
                RankingEntry csvEntry = rankingEntry("zienciu");
                return MyUtils.asList(csvEntry);
            }

            @Override
            public List<RankingEntry> loadOd() {
                RankingEntry csvEntry = rankingEntry("zienciu");
                return MyUtils.asList(csvEntry);
            }

            @Override
            public List<RankingEntry> loadWs() {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public List<RankingEntry> loadWd() {
                throw new UnsupportedOperationException("Not supported yet.");
            }
            
            

            private RankingEntry rankingEntry(String code) {
                return new RankingEntry(1, code, "name", "M", "city", "club", 0, 0, 0, "MASTER");
            }
        });

        PzfsRanking pzfsRanking = cut.loadPzfsRanking();

        assertEquals(1, 1);
        RankingPlayer zienciuOs = pzfsRanking.getOpenSingle().getPlayersByCode("zienciu");
        RankingPlayer zienciuOd = pzfsRanking.getOpenDouble().getPlayersByCode("zienciu");
        assertTrue(zienciuOs.isSharingPlayerDescription(zienciuOd));
    }
}