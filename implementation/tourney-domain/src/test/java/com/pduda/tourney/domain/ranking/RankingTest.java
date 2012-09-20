package com.pduda.tourney.domain.ranking;

import com.pduda.tourney.domain.Gender;
import com.pduda.tourney.domain.PlayerDescription;
import com.pduda.tourney.domain.RankClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class RankingTest {

    private Ranking cut;

    @Before
    public void setUp() {
        cut = new Ranking();
    }

    @Test
    public void addPlayer() {

        cut.addPlayer(new PlayerDescription("Jakub Ziencikiewicz",Gender.MALE,"Łódź","WKPS"), 1,"WKPS44",759,0,0,RankClass.MASTER);
        
        assertTrue(1 == cut.getPlayersByPlace(1).get(0).getRank());
        assertEquals("WKPS44", cut.getPlayersByPlace(1).get(0).getCode());
        assertEquals("Jakub Ziencikiewicz", cut.getPlayersByPlace(1).get(0).getFullName());
        assertEquals(Gender.MALE, cut.getPlayersByPlace(1).get(0).getGender());
        assertEquals("Łódź", cut.getPlayersByPlace(1).get(0).getCity());
        assertEquals("WKPS", cut.getPlayersByPlace(1).get(0).getClub());
        assertTrue(759 == cut.getPlayersByPlace(1).get(0).getPoints());
        assertTrue(0 == cut.getPlayersByPlace(1).get(0).getPointsAdded());
        assertTrue(0 == cut.getPlayersByPlace(1).get(0).getPointsDeleted());
        assertEquals(RankClass.MASTER, cut.getPlayersByPlace(1).get(0).getRankClass());
    }
}