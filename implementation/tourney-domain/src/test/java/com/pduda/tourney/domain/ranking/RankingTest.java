package com.pduda.tourney.domain.ranking;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

//,1,"WKPS44","Jakub Ziencikiewicz","M","Łódź","WKPS",759,0,0,"MASTER","MASTER"
public class RankingTest {

    private Ranking cut;

    @Before
    public void setUp() {
        cut = new Ranking();
    }

    @Test
    public void addPlayer() {

        cut.addPlayer(1,"WKPS44","Jakub Ziencikiewicz","M","Łódź","WKPS",759,0,0,"MASTER");
        
        assertTrue(1 == cut.getPlayersByPlace(1).get(0).getRank());
        assertEquals("WKPS44", cut.getPlayersByPlace(1).get(0).getCode());
        assertEquals("Jakub Ziencikiewicz", cut.getPlayersByPlace(1).get(0).getFullName());
        assertEquals("M", cut.getPlayersByPlace(1).get(0).getGender());
        assertEquals("Łódź", cut.getPlayersByPlace(1).get(0).getCity());
        assertEquals("WKPS", cut.getPlayersByPlace(1).get(0).getClub());
        assertTrue(759 == cut.getPlayersByPlace(1).get(0).getPoints());
        assertTrue(0 == cut.getPlayersByPlace(1).get(0).getPointsAdded());
        assertTrue(0 == cut.getPlayersByPlace(1).get(0).getPointsDeleted());
        assertEquals("MASTER", cut.getPlayersByPlace(1).get(0).getRankClass());
    }
}