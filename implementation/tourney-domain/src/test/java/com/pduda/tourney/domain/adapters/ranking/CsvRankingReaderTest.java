package com.pduda.tourney.domain.adapters.ranking;

import au.com.bytecode.opencsv.CSVReader;
import java.io.InputStreamReader;
import java.util.List;
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
        List<RankingEntry> ranking = cut.loadFromCsvReader(new CSVReader(new InputStreamReader(getClass().getResourceAsStream("/lipiec-os.csv"), CsvRankingReader.FILE_ENCODING)));

        //,1,"WKPS44","Jakub Ziencikiewicz","M","Łódź","WKPS",759,0,0,"MASTER","MASTER"
        final RankingEntry playerZiencik = ranking.get(0);
        assertTrue(1 == playerZiencik.rank);
        assertEquals("WKPS44", playerZiencik.code);
        assertEquals("Jakub Ziencikiewicz", playerZiencik.fullName);
        assertEquals("M", playerZiencik.gender);
        assertEquals("Łódź", playerZiencik.city);
        assertEquals("WKPS", playerZiencik.club);
        assertTrue(759 == playerZiencik.points);
        assertTrue(0 == playerZiencik.pointsAdded);
        assertTrue(0 == playerZiencik.pointsDeleted);
        assertEquals("MASTER", playerZiencik.rankClass);

        assertEquals(101, ranking.size());
    }
}