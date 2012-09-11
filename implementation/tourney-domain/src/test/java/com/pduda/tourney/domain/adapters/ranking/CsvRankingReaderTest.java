package com.pduda.tourney.domain.adapters.ranking;

import au.com.bytecode.opencsv.CSVReader;
import com.pduda.tourney.domain.Gender;
import com.pduda.tourney.domain.Player;
import com.pduda.tourney.domain.RankClass;
import com.pduda.tourney.domain.ranking.Ranking;
import java.io.InputStreamReader;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class CsvRankingReaderTest {

    private CsvRankingReader cut;

    @Before
    public void setUp() {
        cut = new CsvRankingReader();
    }

    @Test
    public void load() throws Exception {
        Ranking ranking = cut.loadFromCsvReader(new CSVReader(new InputStreamReader(getClass().getResourceAsStream("/lipiec-os.csv"), CsvRankingReader.FILE_ENCODING)));

        //,1,"WKPS44","Jakub Ziencikiewicz","M","Łódź","WKPS",759,0,0,"MASTER","MASTER"
        final Player playerZiencik = ranking.getPlayersByPlace(1).get(0);
        assertTrue(1 == playerZiencik.getRank());
        assertEquals("WKPS44", playerZiencik.getCode());
        assertEquals("Jakub Ziencikiewicz", playerZiencik.getFullName());
        assertEquals(Gender.MALE, playerZiencik.getGender());
        assertEquals("Łódź", playerZiencik.getCity());
        assertEquals("WKPS", playerZiencik.getClub());
        assertTrue(759 == playerZiencik.getPoints());
        assertTrue(0 == playerZiencik.getPointsAdded());
        assertTrue(0 == playerZiencik.getPointsDeleted());
        assertEquals(RankClass.MASTER, playerZiencik.getRankClass());

        assertEquals(101, ranking.getPlayers().size());
    }
}