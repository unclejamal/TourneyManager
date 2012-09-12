package com.pduda.tourney.domain;

import org.junit.Test;
import static org.junit.Assert.*;

public class TeamTest {

    private static final int PLAYER1_POINTS = 50;
    private static final int PLAYER2_POINTS = 30;
    private static final TourneyPlayer PLAYER1 = new TourneyPlayer(PLAYER1_POINTS, "adam");
    private static final TourneyPlayer PLAYER2 = new TourneyPlayer(PLAYER2_POINTS, "bartek");

    @Test
    public void getRank_single() {
        Team team = new Team(PLAYER1);

        assertEquals(50, team.getPoints());
    }

    @Test
    public void getRank_double() {
        Team team1 = new Team(PLAYER1, PLAYER2);
        Team team2 = new Team(PLAYER2, PLAYER1);

        assertEquals(65, team1.getPoints());
        assertEquals(65, team2.getPoints());
    }
}