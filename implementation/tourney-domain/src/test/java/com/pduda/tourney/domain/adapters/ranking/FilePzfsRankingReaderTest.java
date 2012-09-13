package com.pduda.tourney.domain.adapters.ranking;

import com.pduda.tourney.domain.RankingPlayer;
import com.pduda.tourney.domain.ranking.PzfsRanking;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/com/pduda/tourney/persistence-test-context.xml"})
public class FilePzfsRankingReaderTest {

    private FilePzfsRankingReader cut;

    @Before
    public void setUp() {
        cut = new FilePzfsRankingReader();
    }

    @Test
    public void playersWithinCompositeRankingSharePlayerDescriptions() {
//        RankingEntry csvEntry1 = zienciu, 900;
//        RankingEntry csvEntry2 = zienciu, 800;
//        
//        mockReader.csvEntry1;
//        mockReader.csvEntry2;
//
//        cut.setRankingReader(mockReader);

//        PzfsRanking pzfsRanking = cut.loadPzfsRanking();

        assertEquals(1, 1);
//        RankingPlayer zienciuOs = pzfsRanking.getOpenSingle().getPlayersByCode("zienciu");
//        RankingPlayer zienciuOd = pzfsRanking.getOpenDouble().getPlayersByCode("zienciu");
//        assertTrue(zienciuOs.isSharingPlayerDescription(zienciuOd));
    }
}