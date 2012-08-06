package com.pduda.tourney.domain.fixture.twoko;

import com.pduda.tourney.domain.GameCode;
import com.pduda.tourney.domain.fixture.twoko.PotentialRivalDirections.Direction;
import org.junit.*;
import static org.junit.Assert.*;

public class PotentialRivalDirectionsTest {

    private PotentialRivalDirections cut;

    @Before
    public void setUp() {
        cut = new PotentialRivalDirections();
    }

    @Test
    public void getDegradedTo_firstGame() {
//        assertDirection(new GameId(NumberedLoserBracketFactory.PREFIX, 3, 1), true, cut.getDegradedTo(new GameId(NumberedWinnerBracketFactory.PREFIX, 1, 1), 1));
        assertDirection(new GameCode(NumberedLbrFactory.PREFIX, 4, 1), true, cut.getDegradedTo(new GameCode(NumberedWbrFactory.PREFIX, 2, 1), 1));
        assertDirection(new GameCode(NumberedLbrFactory.PREFIX, 4, 1), false, cut.getDegradedTo(new GameCode(NumberedWbrFactory.PREFIX, 2, 2), 1));
        assertDirection(new GameCode(NumberedLbrFactory.PREFIX, 8, 1), true, cut.getDegradedTo(new GameCode(NumberedWbrFactory.PREFIX, 4, 1), 1));
        assertDirection(new GameCode(NumberedLbrFactory.PREFIX, 8, 1), false, cut.getDegradedTo(new GameCode(NumberedWbrFactory.PREFIX, 4, 2), 1));
        assertDirection(new GameCode(NumberedLbrFactory.PREFIX, 8, 2), true, cut.getDegradedTo(new GameCode(NumberedWbrFactory.PREFIX, 4, 3), 1));
        assertDirection(new GameCode(NumberedLbrFactory.PREFIX, 8, 2), false, cut.getDegradedTo(new GameCode(NumberedWbrFactory.PREFIX, 4, 4), 1));
        assertDirection(new GameCode(NumberedLbrFactory.PREFIX, 16, 1), true, cut.getDegradedTo(new GameCode(NumberedWbrFactory.PREFIX, 8, 1), 1));
        assertDirection(new GameCode(NumberedLbrFactory.PREFIX, 16, 1), false, cut.getDegradedTo(new GameCode(NumberedWbrFactory.PREFIX, 8, 2), 1));
        assertDirection(new GameCode(NumberedLbrFactory.PREFIX, 16, 2), true, cut.getDegradedTo(new GameCode(NumberedWbrFactory.PREFIX, 8, 3), 1));
        assertDirection(new GameCode(NumberedLbrFactory.PREFIX, 16, 2), false, cut.getDegradedTo(new GameCode(NumberedWbrFactory.PREFIX, 8, 4), 1));
        assertDirection(new GameCode(NumberedLbrFactory.PREFIX, 16, 3), true, cut.getDegradedTo(new GameCode(NumberedWbrFactory.PREFIX, 8, 5), 1));
        assertDirection(new GameCode(NumberedLbrFactory.PREFIX, 16, 3), false, cut.getDegradedTo(new GameCode(NumberedWbrFactory.PREFIX, 8, 6), 1));
        assertDirection(new GameCode(NumberedLbrFactory.PREFIX, 16, 4), true, cut.getDegradedTo(new GameCode(NumberedWbrFactory.PREFIX, 8, 7), 1));
        assertDirection(new GameCode(NumberedLbrFactory.PREFIX, 16, 4), false, cut.getDegradedTo(new GameCode(NumberedWbrFactory.PREFIX, 8, 8), 1));
        assertDirection(new GameCode(NumberedLbrFactory.PREFIX, 32, 1), true, cut.getDegradedTo(new GameCode(NumberedWbrFactory.PREFIX, 16, 1), 1));
        assertDirection(new GameCode(NumberedLbrFactory.PREFIX, 32, 1), false, cut.getDegradedTo(new GameCode(NumberedWbrFactory.PREFIX, 16, 2), 1));
        assertDirection(new GameCode(NumberedLbrFactory.PREFIX, 32, 2), true, cut.getDegradedTo(new GameCode(NumberedWbrFactory.PREFIX, 16, 3), 1));
        assertDirection(new GameCode(NumberedLbrFactory.PREFIX, 32, 2), false, cut.getDegradedTo(new GameCode(NumberedWbrFactory.PREFIX, 16, 4), 1));
        assertDirection(new GameCode(NumberedLbrFactory.PREFIX, 32, 3), true, cut.getDegradedTo(new GameCode(NumberedWbrFactory.PREFIX, 16, 5), 1));
        assertDirection(new GameCode(NumberedLbrFactory.PREFIX, 32, 3), false, cut.getDegradedTo(new GameCode(NumberedWbrFactory.PREFIX, 16, 6), 1));
        assertDirection(new GameCode(NumberedLbrFactory.PREFIX, 32, 4), true, cut.getDegradedTo(new GameCode(NumberedWbrFactory.PREFIX, 16, 7), 1));
        assertDirection(new GameCode(NumberedLbrFactory.PREFIX, 32, 4), false, cut.getDegradedTo(new GameCode(NumberedWbrFactory.PREFIX, 16, 8), 1));
        assertDirection(new GameCode(NumberedLbrFactory.PREFIX, 32, 5), true, cut.getDegradedTo(new GameCode(NumberedWbrFactory.PREFIX, 16, 9), 1));
        assertDirection(new GameCode(NumberedLbrFactory.PREFIX, 32, 5), false, cut.getDegradedTo(new GameCode(NumberedWbrFactory.PREFIX, 16, 10), 1));
        assertDirection(new GameCode(NumberedLbrFactory.PREFIX, 32, 6), true, cut.getDegradedTo(new GameCode(NumberedWbrFactory.PREFIX, 16, 11), 1));
        assertDirection(new GameCode(NumberedLbrFactory.PREFIX, 32, 6), false, cut.getDegradedTo(new GameCode(NumberedWbrFactory.PREFIX, 16, 12), 1));
        assertDirection(new GameCode(NumberedLbrFactory.PREFIX, 32, 7), true, cut.getDegradedTo(new GameCode(NumberedWbrFactory.PREFIX, 16, 13), 1));
        assertDirection(new GameCode(NumberedLbrFactory.PREFIX, 32, 7), false, cut.getDegradedTo(new GameCode(NumberedWbrFactory.PREFIX, 16, 14), 1));
        assertDirection(new GameCode(NumberedLbrFactory.PREFIX, 32, 8), true, cut.getDegradedTo(new GameCode(NumberedWbrFactory.PREFIX, 16, 15), 1));
        assertDirection(new GameCode(NumberedLbrFactory.PREFIX, 32, 8), false, cut.getDegradedTo(new GameCode(NumberedWbrFactory.PREFIX, 16, 16), 1));
    }

    @Test
    public void getDegradedTo_furtherGames() {
        assertDirection(new GameCode(NumberedLbrFactory.PREFIX, 3, 1), true, cut.getDegradedTo(new GameCode(NumberedWbrFactory.PREFIX, 1, 1), 2));

        assertDirection(new GameCode(NumberedLbrFactory.PREFIX, 6, 2), true, cut.getDegradedTo(new GameCode(NumberedWbrFactory.PREFIX, 2, 1), 2));
        assertDirection(new GameCode(NumberedLbrFactory.PREFIX, 6, 1), true, cut.getDegradedTo(new GameCode(NumberedWbrFactory.PREFIX, 2, 2), 2));
        assertDirection(new GameCode(NumberedLbrFactory.PREFIX, 6, 1), true, cut.getDegradedTo(new GameCode(NumberedWbrFactory.PREFIX, 2, 1), 3));
        assertDirection(new GameCode(NumberedLbrFactory.PREFIX, 6, 2), true, cut.getDegradedTo(new GameCode(NumberedWbrFactory.PREFIX, 2, 2), 3));
        assertDirection(new GameCode(NumberedLbrFactory.PREFIX, 6, 2), true, cut.getDegradedTo(new GameCode(NumberedWbrFactory.PREFIX, 2, 1), 4));
        assertDirection(new GameCode(NumberedLbrFactory.PREFIX, 6, 1), true, cut.getDegradedTo(new GameCode(NumberedWbrFactory.PREFIX, 2, 2), 4));


        assertDirection(new GameCode(NumberedLbrFactory.PREFIX, 12, 4), true, cut.getDegradedTo(new GameCode(NumberedWbrFactory.PREFIX, 4, 1), 2));
        assertDirection(new GameCode(NumberedLbrFactory.PREFIX, 12, 3), true, cut.getDegradedTo(new GameCode(NumberedWbrFactory.PREFIX, 4, 2), 2));
        assertDirection(new GameCode(NumberedLbrFactory.PREFIX, 12, 2), true, cut.getDegradedTo(new GameCode(NumberedWbrFactory.PREFIX, 4, 3), 2));
        assertDirection(new GameCode(NumberedLbrFactory.PREFIX, 12, 1), true, cut.getDegradedTo(new GameCode(NumberedWbrFactory.PREFIX, 4, 4), 2));
        assertDirection(new GameCode(NumberedLbrFactory.PREFIX, 12, 1), true, cut.getDegradedTo(new GameCode(NumberedWbrFactory.PREFIX, 4, 1), 3));
        assertDirection(new GameCode(NumberedLbrFactory.PREFIX, 12, 2), true, cut.getDegradedTo(new GameCode(NumberedWbrFactory.PREFIX, 4, 2), 3));
        assertDirection(new GameCode(NumberedLbrFactory.PREFIX, 12, 3), true, cut.getDegradedTo(new GameCode(NumberedWbrFactory.PREFIX, 4, 3), 3));
        assertDirection(new GameCode(NumberedLbrFactory.PREFIX, 12, 4), true, cut.getDegradedTo(new GameCode(NumberedWbrFactory.PREFIX, 4, 4), 3));


        assertDirection(new GameCode(NumberedLbrFactory.PREFIX, 24, 8), true, cut.getDegradedTo(new GameCode(NumberedWbrFactory.PREFIX, 8, 1), 2));
        assertDirection(new GameCode(NumberedLbrFactory.PREFIX, 24, 7), true, cut.getDegradedTo(new GameCode(NumberedWbrFactory.PREFIX, 8, 2), 2));
        assertDirection(new GameCode(NumberedLbrFactory.PREFIX, 24, 6), true, cut.getDegradedTo(new GameCode(NumberedWbrFactory.PREFIX, 8, 3), 2));
        assertDirection(new GameCode(NumberedLbrFactory.PREFIX, 24, 5), true, cut.getDegradedTo(new GameCode(NumberedWbrFactory.PREFIX, 8, 4), 2));
        assertDirection(new GameCode(NumberedLbrFactory.PREFIX, 24, 4), true, cut.getDegradedTo(new GameCode(NumberedWbrFactory.PREFIX, 8, 5), 2));
        assertDirection(new GameCode(NumberedLbrFactory.PREFIX, 24, 3), true, cut.getDegradedTo(new GameCode(NumberedWbrFactory.PREFIX, 8, 6), 2));
        assertDirection(new GameCode(NumberedLbrFactory.PREFIX, 24, 2), true, cut.getDegradedTo(new GameCode(NumberedWbrFactory.PREFIX, 8, 7), 2));
        assertDirection(new GameCode(NumberedLbrFactory.PREFIX, 24, 1), true, cut.getDegradedTo(new GameCode(NumberedWbrFactory.PREFIX, 8, 8), 2));
    }

    @Test
    public void getOtherGameDegradingToSameLoserBracketGame_firstGame() {
//        assertNull(new GameId(NumberedLoserBracketFactory.PREFIX, 4, 1), cut.getPotentialRivalGame(new GameId(NumberedWinnerBracketFactory.PREFIX, 1, 1), 1));
        assertEquals(new GameCode(NumberedWbrFactory.PREFIX, 2, 2), cut.getPotentialRivalGame(new GameCode(NumberedWbrFactory.PREFIX, 2, 1), 1));
        assertEquals(new GameCode(NumberedWbrFactory.PREFIX, 2, 1), cut.getPotentialRivalGame(new GameCode(NumberedWbrFactory.PREFIX, 2, 2), 1));
        assertEquals(new GameCode(NumberedWbrFactory.PREFIX, 4, 2), cut.getPotentialRivalGame(new GameCode(NumberedWbrFactory.PREFIX, 4, 1), 1));
        assertEquals(new GameCode(NumberedWbrFactory.PREFIX, 4, 1), cut.getPotentialRivalGame(new GameCode(NumberedWbrFactory.PREFIX, 4, 2), 1));
        assertEquals(new GameCode(NumberedWbrFactory.PREFIX, 4, 4), cut.getPotentialRivalGame(new GameCode(NumberedWbrFactory.PREFIX, 4, 3), 1));
        assertEquals(new GameCode(NumberedWbrFactory.PREFIX, 4, 3), cut.getPotentialRivalGame(new GameCode(NumberedWbrFactory.PREFIX, 4, 4), 1));
        assertEquals(new GameCode(NumberedWbrFactory.PREFIX, 8, 2), cut.getPotentialRivalGame(new GameCode(NumberedWbrFactory.PREFIX, 8, 1), 1));
        assertEquals(new GameCode(NumberedWbrFactory.PREFIX, 8, 1), cut.getPotentialRivalGame(new GameCode(NumberedWbrFactory.PREFIX, 8, 2), 1));
        assertEquals(new GameCode(NumberedWbrFactory.PREFIX, 8, 4), cut.getPotentialRivalGame(new GameCode(NumberedWbrFactory.PREFIX, 8, 3), 1));
        assertEquals(new GameCode(NumberedWbrFactory.PREFIX, 8, 3), cut.getPotentialRivalGame(new GameCode(NumberedWbrFactory.PREFIX, 8, 4), 1));
        assertEquals(new GameCode(NumberedWbrFactory.PREFIX, 8, 6), cut.getPotentialRivalGame(new GameCode(NumberedWbrFactory.PREFIX, 8, 5), 1));
        assertEquals(new GameCode(NumberedWbrFactory.PREFIX, 8, 5), cut.getPotentialRivalGame(new GameCode(NumberedWbrFactory.PREFIX, 8, 6), 1));
        assertEquals(new GameCode(NumberedWbrFactory.PREFIX, 8, 8), cut.getPotentialRivalGame(new GameCode(NumberedWbrFactory.PREFIX, 8, 7), 1));
        assertEquals(new GameCode(NumberedWbrFactory.PREFIX, 8, 7), cut.getPotentialRivalGame(new GameCode(NumberedWbrFactory.PREFIX, 8, 8), 1));
        assertEquals(new GameCode(NumberedWbrFactory.PREFIX, 16, 2), cut.getPotentialRivalGame(new GameCode(NumberedWbrFactory.PREFIX, 16, 1), 1));
        assertEquals(new GameCode(NumberedWbrFactory.PREFIX, 16, 1), cut.getPotentialRivalGame(new GameCode(NumberedWbrFactory.PREFIX, 16, 2), 1));
        assertEquals(new GameCode(NumberedWbrFactory.PREFIX, 16, 4), cut.getPotentialRivalGame(new GameCode(NumberedWbrFactory.PREFIX, 16, 3), 1));
        assertEquals(new GameCode(NumberedWbrFactory.PREFIX, 16, 3), cut.getPotentialRivalGame(new GameCode(NumberedWbrFactory.PREFIX, 16, 4), 1));
        assertEquals(new GameCode(NumberedWbrFactory.PREFIX, 16, 6), cut.getPotentialRivalGame(new GameCode(NumberedWbrFactory.PREFIX, 16, 5), 1));
        assertEquals(new GameCode(NumberedWbrFactory.PREFIX, 16, 5), cut.getPotentialRivalGame(new GameCode(NumberedWbrFactory.PREFIX, 16, 6), 1));
        assertEquals(new GameCode(NumberedWbrFactory.PREFIX, 16, 8), cut.getPotentialRivalGame(new GameCode(NumberedWbrFactory.PREFIX, 16, 7), 1));
        assertEquals(new GameCode(NumberedWbrFactory.PREFIX, 16, 7), cut.getPotentialRivalGame(new GameCode(NumberedWbrFactory.PREFIX, 16, 8), 1));
        assertEquals(new GameCode(NumberedWbrFactory.PREFIX, 16, 10), cut.getPotentialRivalGame(new GameCode(NumberedWbrFactory.PREFIX, 16, 9), 1));
        assertEquals(new GameCode(NumberedWbrFactory.PREFIX, 16, 9), cut.getPotentialRivalGame(new GameCode(NumberedWbrFactory.PREFIX, 16, 10), 1));
        assertEquals(new GameCode(NumberedWbrFactory.PREFIX, 16, 12), cut.getPotentialRivalGame(new GameCode(NumberedWbrFactory.PREFIX, 16, 11), 1));
        assertEquals(new GameCode(NumberedWbrFactory.PREFIX, 16, 11), cut.getPotentialRivalGame(new GameCode(NumberedWbrFactory.PREFIX, 16, 12), 1));
        assertEquals(new GameCode(NumberedWbrFactory.PREFIX, 16, 14), cut.getPotentialRivalGame(new GameCode(NumberedWbrFactory.PREFIX, 16, 13), 1));
        assertEquals(new GameCode(NumberedWbrFactory.PREFIX, 16, 13), cut.getPotentialRivalGame(new GameCode(NumberedWbrFactory.PREFIX, 16, 14), 1));
        assertEquals(new GameCode(NumberedWbrFactory.PREFIX, 16, 16), cut.getPotentialRivalGame(new GameCode(NumberedWbrFactory.PREFIX, 16, 15), 1));
        assertEquals(new GameCode(NumberedWbrFactory.PREFIX, 16, 15), cut.getPotentialRivalGame(new GameCode(NumberedWbrFactory.PREFIX, 16, 16), 1));
    }

    @Test
    public void getOtherGameDegradingToSameLoserBracketGame_furtherGames() {
        assertEquals(new GameCode(NumberedLbrFactory.PREFIX, 4, 1), cut.getPotentialRivalGame(new GameCode(NumberedWbrFactory.PREFIX, 1, 1), 2));
        assertEquals(new GameCode(NumberedLbrFactory.PREFIX, 4, 1), cut.getPotentialRivalGame(new GameCode(NumberedWbrFactory.PREFIX, 1, 1), 3));
        assertEquals(new GameCode(NumberedLbrFactory.PREFIX, 4, 1), cut.getPotentialRivalGame(new GameCode(NumberedWbrFactory.PREFIX, 1, 1), 4));
        assertEquals(new GameCode(NumberedLbrFactory.PREFIX, 4, 1), cut.getPotentialRivalGame(new GameCode(NumberedWbrFactory.PREFIX, 1, 1), 5));

        assertEquals(new GameCode(NumberedLbrFactory.PREFIX, 8, 2), cut.getPotentialRivalGame(new GameCode(NumberedWbrFactory.PREFIX, 2, 1), 2));
        assertEquals(new GameCode(NumberedLbrFactory.PREFIX, 8, 1), cut.getPotentialRivalGame(new GameCode(NumberedWbrFactory.PREFIX, 2, 2), 2));
        assertEquals(new GameCode(NumberedLbrFactory.PREFIX, 8, 1), cut.getPotentialRivalGame(new GameCode(NumberedWbrFactory.PREFIX, 2, 1), 3));
        assertEquals(new GameCode(NumberedLbrFactory.PREFIX, 8, 2), cut.getPotentialRivalGame(new GameCode(NumberedWbrFactory.PREFIX, 2, 2), 3));
        assertEquals(new GameCode(NumberedLbrFactory.PREFIX, 8, 2), cut.getPotentialRivalGame(new GameCode(NumberedWbrFactory.PREFIX, 2, 1), 4));
        assertEquals(new GameCode(NumberedLbrFactory.PREFIX, 8, 1), cut.getPotentialRivalGame(new GameCode(NumberedWbrFactory.PREFIX, 2, 2), 4));

        assertEquals(new GameCode(NumberedLbrFactory.PREFIX, 16, 4), cut.getPotentialRivalGame(new GameCode(NumberedWbrFactory.PREFIX, 4, 1), 2));
        assertEquals(new GameCode(NumberedLbrFactory.PREFIX, 16, 3), cut.getPotentialRivalGame(new GameCode(NumberedWbrFactory.PREFIX, 4, 2), 2));
        assertEquals(new GameCode(NumberedLbrFactory.PREFIX, 16, 2), cut.getPotentialRivalGame(new GameCode(NumberedWbrFactory.PREFIX, 4, 3), 2));
        assertEquals(new GameCode(NumberedLbrFactory.PREFIX, 16, 1), cut.getPotentialRivalGame(new GameCode(NumberedWbrFactory.PREFIX, 4, 4), 2));
        assertEquals(new GameCode(NumberedLbrFactory.PREFIX, 16, 1), cut.getPotentialRivalGame(new GameCode(NumberedWbrFactory.PREFIX, 4, 1), 3));
        assertEquals(new GameCode(NumberedLbrFactory.PREFIX, 16, 2), cut.getPotentialRivalGame(new GameCode(NumberedWbrFactory.PREFIX, 4, 2), 3));
        assertEquals(new GameCode(NumberedLbrFactory.PREFIX, 16, 3), cut.getPotentialRivalGame(new GameCode(NumberedWbrFactory.PREFIX, 4, 3), 3));
        assertEquals(new GameCode(NumberedLbrFactory.PREFIX, 16, 4), cut.getPotentialRivalGame(new GameCode(NumberedWbrFactory.PREFIX, 4, 4), 3));

        assertEquals(new GameCode(NumberedLbrFactory.PREFIX, 32, 8), cut.getPotentialRivalGame(new GameCode(NumberedWbrFactory.PREFIX, 8, 1), 2));
        assertEquals(new GameCode(NumberedLbrFactory.PREFIX, 32, 7), cut.getPotentialRivalGame(new GameCode(NumberedWbrFactory.PREFIX, 8, 2), 2));
        assertEquals(new GameCode(NumberedLbrFactory.PREFIX, 32, 6), cut.getPotentialRivalGame(new GameCode(NumberedWbrFactory.PREFIX, 8, 3), 2));
        assertEquals(new GameCode(NumberedLbrFactory.PREFIX, 32, 5), cut.getPotentialRivalGame(new GameCode(NumberedWbrFactory.PREFIX, 8, 4), 2));
        assertEquals(new GameCode(NumberedLbrFactory.PREFIX, 32, 4), cut.getPotentialRivalGame(new GameCode(NumberedWbrFactory.PREFIX, 8, 5), 2));
        assertEquals(new GameCode(NumberedLbrFactory.PREFIX, 32, 3), cut.getPotentialRivalGame(new GameCode(NumberedWbrFactory.PREFIX, 8, 6), 2));
        assertEquals(new GameCode(NumberedLbrFactory.PREFIX, 32, 2), cut.getPotentialRivalGame(new GameCode(NumberedWbrFactory.PREFIX, 8, 7), 2));
        assertEquals(new GameCode(NumberedLbrFactory.PREFIX, 32, 1), cut.getPotentialRivalGame(new GameCode(NumberedWbrFactory.PREFIX, 8, 8), 2));
    }

    @Test
    public void getPotentialRivalGames_firstGame() {
        assertEquals(new GameCode(NumberedWbrFactory.PREFIX, 2, 1), cut.getPotentialRivalGames(new GameCode(NumberedLbrFactory.PREFIX, 4, 1), 1).get(0));
        assertEquals(new GameCode(NumberedWbrFactory.PREFIX, 2, 2), cut.getPotentialRivalGames(new GameCode(NumberedLbrFactory.PREFIX, 4, 1), 1).get(1));

        assertEquals(new GameCode(NumberedWbrFactory.PREFIX, 4, 1), cut.getPotentialRivalGames(new GameCode(NumberedLbrFactory.PREFIX, 8, 1), 1).get(0));
        assertEquals(new GameCode(NumberedWbrFactory.PREFIX, 4, 2), cut.getPotentialRivalGames(new GameCode(NumberedLbrFactory.PREFIX, 8, 1), 1).get(1));
        assertEquals(new GameCode(NumberedWbrFactory.PREFIX, 4, 3), cut.getPotentialRivalGames(new GameCode(NumberedLbrFactory.PREFIX, 8, 2), 1).get(0));
        assertEquals(new GameCode(NumberedWbrFactory.PREFIX, 4, 4), cut.getPotentialRivalGames(new GameCode(NumberedLbrFactory.PREFIX, 8, 2), 1).get(1));

        assertEquals(new GameCode(NumberedWbrFactory.PREFIX, 8, 1), cut.getPotentialRivalGames(new GameCode(NumberedLbrFactory.PREFIX, 16, 1), 1).get(0));
        assertEquals(new GameCode(NumberedWbrFactory.PREFIX, 8, 2), cut.getPotentialRivalGames(new GameCode(NumberedLbrFactory.PREFIX, 16, 1), 1).get(1));
        assertEquals(new GameCode(NumberedWbrFactory.PREFIX, 8, 3), cut.getPotentialRivalGames(new GameCode(NumberedLbrFactory.PREFIX, 16, 2), 1).get(0));
        assertEquals(new GameCode(NumberedWbrFactory.PREFIX, 8, 4), cut.getPotentialRivalGames(new GameCode(NumberedLbrFactory.PREFIX, 16, 2), 1).get(1));
        assertEquals(new GameCode(NumberedWbrFactory.PREFIX, 8, 5), cut.getPotentialRivalGames(new GameCode(NumberedLbrFactory.PREFIX, 16, 3), 1).get(0));
        assertEquals(new GameCode(NumberedWbrFactory.PREFIX, 8, 6), cut.getPotentialRivalGames(new GameCode(NumberedLbrFactory.PREFIX, 16, 3), 1).get(1));
        assertEquals(new GameCode(NumberedWbrFactory.PREFIX, 8, 7), cut.getPotentialRivalGames(new GameCode(NumberedLbrFactory.PREFIX, 16, 4), 1).get(0));
        assertEquals(new GameCode(NumberedWbrFactory.PREFIX, 8, 8), cut.getPotentialRivalGames(new GameCode(NumberedLbrFactory.PREFIX, 16, 4), 1).get(1));

        assertEquals(new GameCode(NumberedWbrFactory.PREFIX, 16, 1), cut.getPotentialRivalGames(new GameCode(NumberedLbrFactory.PREFIX, 32, 1), 1).get(0));
        assertEquals(new GameCode(NumberedWbrFactory.PREFIX, 16, 2), cut.getPotentialRivalGames(new GameCode(NumberedLbrFactory.PREFIX, 32, 1), 1).get(1));
        assertEquals(new GameCode(NumberedWbrFactory.PREFIX, 16, 3), cut.getPotentialRivalGames(new GameCode(NumberedLbrFactory.PREFIX, 32, 2), 1).get(0));
        assertEquals(new GameCode(NumberedWbrFactory.PREFIX, 16, 4), cut.getPotentialRivalGames(new GameCode(NumberedLbrFactory.PREFIX, 32, 2), 1).get(1));
        assertEquals(new GameCode(NumberedWbrFactory.PREFIX, 16, 5), cut.getPotentialRivalGames(new GameCode(NumberedLbrFactory.PREFIX, 32, 3), 1).get(0));
        assertEquals(new GameCode(NumberedWbrFactory.PREFIX, 16, 6), cut.getPotentialRivalGames(new GameCode(NumberedLbrFactory.PREFIX, 32, 3), 1).get(1));
        assertEquals(new GameCode(NumberedWbrFactory.PREFIX, 16, 7), cut.getPotentialRivalGames(new GameCode(NumberedLbrFactory.PREFIX, 32, 4), 1).get(0));
        assertEquals(new GameCode(NumberedWbrFactory.PREFIX, 16, 8), cut.getPotentialRivalGames(new GameCode(NumberedLbrFactory.PREFIX, 32, 4), 1).get(1));
        assertEquals(new GameCode(NumberedWbrFactory.PREFIX, 16, 9), cut.getPotentialRivalGames(new GameCode(NumberedLbrFactory.PREFIX, 32, 5), 1).get(0));
        assertEquals(new GameCode(NumberedWbrFactory.PREFIX, 16, 10), cut.getPotentialRivalGames(new GameCode(NumberedLbrFactory.PREFIX, 32, 5), 1).get(1));
        assertEquals(new GameCode(NumberedWbrFactory.PREFIX, 16, 11), cut.getPotentialRivalGames(new GameCode(NumberedLbrFactory.PREFIX, 32, 6), 1).get(0));
        assertEquals(new GameCode(NumberedWbrFactory.PREFIX, 16, 12), cut.getPotentialRivalGames(new GameCode(NumberedLbrFactory.PREFIX, 32, 6), 1).get(1));
        assertEquals(new GameCode(NumberedWbrFactory.PREFIX, 16, 13), cut.getPotentialRivalGames(new GameCode(NumberedLbrFactory.PREFIX, 32, 7), 1).get(0));
        assertEquals(new GameCode(NumberedWbrFactory.PREFIX, 16, 14), cut.getPotentialRivalGames(new GameCode(NumberedLbrFactory.PREFIX, 32, 7), 1).get(1));
        assertEquals(new GameCode(NumberedWbrFactory.PREFIX, 16, 15), cut.getPotentialRivalGames(new GameCode(NumberedLbrFactory.PREFIX, 32, 8), 1).get(0));
        assertEquals(new GameCode(NumberedWbrFactory.PREFIX, 16, 16), cut.getPotentialRivalGames(new GameCode(NumberedLbrFactory.PREFIX, 32, 8), 1).get(1));
        
        // games in wbr
        assertEquals(0, cut.getPotentialRivalGames(new GameCode(NumberedWbrFactory.PREFIX, 4, 4), 1).size());
    }

    @Test
    public void getPotentialRivalGames_furtherGames() {
        // games with lbr loser
        assertEquals(new GameCode(NumberedWbrFactory.PREFIX, 1, 1), cut.getPotentialRivalGames(new GameCode(NumberedLbrFactory.PREFIX, 3, 1), 2).get(0));
        assertEquals(new GameCode(NumberedLbrFactory.PREFIX, 4, 1), cut.getPotentialRivalGames(new GameCode(NumberedLbrFactory.PREFIX, 3, 1), 2).get(1));
        assertEquals(new GameCode(NumberedWbrFactory.PREFIX, 1, 1), cut.getPotentialRivalGames(new GameCode(NumberedLbrFactory.PREFIX, 3, 1), 4).get(0));
        assertEquals(new GameCode(NumberedLbrFactory.PREFIX, 4, 1), cut.getPotentialRivalGames(new GameCode(NumberedLbrFactory.PREFIX, 3, 1), 4).get(1));
        assertEquals(new GameCode(NumberedWbrFactory.PREFIX, 1, 1), cut.getPotentialRivalGames(new GameCode(NumberedLbrFactory.PREFIX, 3, 1), 6).get(0));
        assertEquals(new GameCode(NumberedLbrFactory.PREFIX, 4, 1), cut.getPotentialRivalGames(new GameCode(NumberedLbrFactory.PREFIX, 3, 1), 6).get(1));
        assertEquals(new GameCode(NumberedWbrFactory.PREFIX, 1, 1), cut.getPotentialRivalGames(new GameCode(NumberedLbrFactory.PREFIX, 3, 1), 8).get(0));
        assertEquals(new GameCode(NumberedLbrFactory.PREFIX, 4, 1), cut.getPotentialRivalGames(new GameCode(NumberedLbrFactory.PREFIX, 3, 1), 8).get(1));
        
        assertEquals(new GameCode(NumberedWbrFactory.PREFIX, 2, 2), cut.getPotentialRivalGames(new GameCode(NumberedLbrFactory.PREFIX, 6, 1), 2).get(0));
        assertEquals(new GameCode(NumberedLbrFactory.PREFIX, 8, 1), cut.getPotentialRivalGames(new GameCode(NumberedLbrFactory.PREFIX, 6, 1), 2).get(1));
        assertEquals(new GameCode(NumberedWbrFactory.PREFIX, 2, 1), cut.getPotentialRivalGames(new GameCode(NumberedLbrFactory.PREFIX, 6, 2), 2).get(0));
        assertEquals(new GameCode(NumberedLbrFactory.PREFIX, 8, 2), cut.getPotentialRivalGames(new GameCode(NumberedLbrFactory.PREFIX, 6, 2), 2).get(1));
        assertEquals(new GameCode(NumberedWbrFactory.PREFIX, 2, 1), cut.getPotentialRivalGames(new GameCode(NumberedLbrFactory.PREFIX, 6, 1), 4).get(0));
        assertEquals(new GameCode(NumberedLbrFactory.PREFIX, 8, 1), cut.getPotentialRivalGames(new GameCode(NumberedLbrFactory.PREFIX, 6, 1), 4).get(1));
        assertEquals(new GameCode(NumberedWbrFactory.PREFIX, 2, 2), cut.getPotentialRivalGames(new GameCode(NumberedLbrFactory.PREFIX, 6, 2), 4).get(0));
        assertEquals(new GameCode(NumberedLbrFactory.PREFIX, 8, 2), cut.getPotentialRivalGames(new GameCode(NumberedLbrFactory.PREFIX, 6, 2), 4).get(1));
        assertEquals(new GameCode(NumberedWbrFactory.PREFIX, 2, 2), cut.getPotentialRivalGames(new GameCode(NumberedLbrFactory.PREFIX, 6, 1), 6).get(0));
        assertEquals(new GameCode(NumberedLbrFactory.PREFIX, 8, 1), cut.getPotentialRivalGames(new GameCode(NumberedLbrFactory.PREFIX, 6, 1), 6).get(1));
        assertEquals(new GameCode(NumberedWbrFactory.PREFIX, 2, 1), cut.getPotentialRivalGames(new GameCode(NumberedLbrFactory.PREFIX, 6, 2), 6).get(0));
        assertEquals(new GameCode(NumberedLbrFactory.PREFIX, 8, 2), cut.getPotentialRivalGames(new GameCode(NumberedLbrFactory.PREFIX, 6, 2), 6).get(1));
        
        assertEquals(new GameCode(NumberedWbrFactory.PREFIX, 4, 4), cut.getPotentialRivalGames(new GameCode(NumberedLbrFactory.PREFIX, 12, 1), 2).get(0));
        assertEquals(new GameCode(NumberedLbrFactory.PREFIX, 16, 1), cut.getPotentialRivalGames(new GameCode(NumberedLbrFactory.PREFIX, 12, 1), 2).get(1));
        assertEquals(new GameCode(NumberedWbrFactory.PREFIX, 4, 3), cut.getPotentialRivalGames(new GameCode(NumberedLbrFactory.PREFIX, 12, 2), 2).get(0));
        assertEquals(new GameCode(NumberedLbrFactory.PREFIX, 16, 2), cut.getPotentialRivalGames(new GameCode(NumberedLbrFactory.PREFIX, 12, 2), 2).get(1));
        assertEquals(new GameCode(NumberedWbrFactory.PREFIX, 4, 2), cut.getPotentialRivalGames(new GameCode(NumberedLbrFactory.PREFIX, 12, 3), 2).get(0));
        assertEquals(new GameCode(NumberedLbrFactory.PREFIX, 16, 3), cut.getPotentialRivalGames(new GameCode(NumberedLbrFactory.PREFIX, 12, 3), 2).get(1));
        assertEquals(new GameCode(NumberedWbrFactory.PREFIX, 4, 1), cut.getPotentialRivalGames(new GameCode(NumberedLbrFactory.PREFIX, 12, 4), 2).get(0));
        assertEquals(new GameCode(NumberedLbrFactory.PREFIX, 16, 4), cut.getPotentialRivalGames(new GameCode(NumberedLbrFactory.PREFIX, 12, 4), 2).get(1));
        assertEquals(new GameCode(NumberedWbrFactory.PREFIX, 4, 1), cut.getPotentialRivalGames(new GameCode(NumberedLbrFactory.PREFIX, 12, 1), 4).get(0));
        assertEquals(new GameCode(NumberedLbrFactory.PREFIX, 16, 1), cut.getPotentialRivalGames(new GameCode(NumberedLbrFactory.PREFIX, 12, 1), 4).get(1));
        assertEquals(new GameCode(NumberedWbrFactory.PREFIX, 4, 2), cut.getPotentialRivalGames(new GameCode(NumberedLbrFactory.PREFIX, 12, 2), 4).get(0));
        assertEquals(new GameCode(NumberedLbrFactory.PREFIX, 16, 2), cut.getPotentialRivalGames(new GameCode(NumberedLbrFactory.PREFIX, 12, 2), 4).get(1));
        assertEquals(new GameCode(NumberedWbrFactory.PREFIX, 4, 3), cut.getPotentialRivalGames(new GameCode(NumberedLbrFactory.PREFIX, 12, 3), 4).get(0));
        assertEquals(new GameCode(NumberedLbrFactory.PREFIX, 16, 3), cut.getPotentialRivalGames(new GameCode(NumberedLbrFactory.PREFIX, 12, 3), 4).get(1));
        assertEquals(new GameCode(NumberedWbrFactory.PREFIX, 4, 4), cut.getPotentialRivalGames(new GameCode(NumberedLbrFactory.PREFIX, 12, 4), 4).get(0));
        assertEquals(new GameCode(NumberedLbrFactory.PREFIX, 16, 4), cut.getPotentialRivalGames(new GameCode(NumberedLbrFactory.PREFIX, 12, 4), 4).get(1));
        
        assertEquals(new GameCode(NumberedWbrFactory.PREFIX, 8, 8), cut.getPotentialRivalGames(new GameCode(NumberedLbrFactory.PREFIX, 24, 1), 2).get(0));
        assertEquals(new GameCode(NumberedLbrFactory.PREFIX, 32, 1), cut.getPotentialRivalGames(new GameCode(NumberedLbrFactory.PREFIX, 24, 1), 2).get(1));
        assertEquals(new GameCode(NumberedWbrFactory.PREFIX, 8, 7), cut.getPotentialRivalGames(new GameCode(NumberedLbrFactory.PREFIX, 24, 2), 2).get(0));
        assertEquals(new GameCode(NumberedLbrFactory.PREFIX, 32, 2), cut.getPotentialRivalGames(new GameCode(NumberedLbrFactory.PREFIX, 24, 2), 2).get(1));
        assertEquals(new GameCode(NumberedWbrFactory.PREFIX, 8, 6), cut.getPotentialRivalGames(new GameCode(NumberedLbrFactory.PREFIX, 24, 3), 2).get(0));
        assertEquals(new GameCode(NumberedLbrFactory.PREFIX, 32, 3), cut.getPotentialRivalGames(new GameCode(NumberedLbrFactory.PREFIX, 24, 3), 2).get(1));
        assertEquals(new GameCode(NumberedWbrFactory.PREFIX, 8, 5), cut.getPotentialRivalGames(new GameCode(NumberedLbrFactory.PREFIX, 24, 4), 2).get(0));
        assertEquals(new GameCode(NumberedLbrFactory.PREFIX, 32, 4), cut.getPotentialRivalGames(new GameCode(NumberedLbrFactory.PREFIX, 24, 4), 2).get(1));
        assertEquals(new GameCode(NumberedWbrFactory.PREFIX, 8, 4), cut.getPotentialRivalGames(new GameCode(NumberedLbrFactory.PREFIX, 24, 5), 2).get(0));
        assertEquals(new GameCode(NumberedLbrFactory.PREFIX, 32, 5), cut.getPotentialRivalGames(new GameCode(NumberedLbrFactory.PREFIX, 24, 5), 2).get(1));
        assertEquals(new GameCode(NumberedWbrFactory.PREFIX, 8, 3), cut.getPotentialRivalGames(new GameCode(NumberedLbrFactory.PREFIX, 24, 6), 2).get(0));
        assertEquals(new GameCode(NumberedLbrFactory.PREFIX, 32, 6), cut.getPotentialRivalGames(new GameCode(NumberedLbrFactory.PREFIX, 24, 6), 2).get(1));
        assertEquals(new GameCode(NumberedWbrFactory.PREFIX, 8, 2), cut.getPotentialRivalGames(new GameCode(NumberedLbrFactory.PREFIX, 24, 7), 2).get(0));
        assertEquals(new GameCode(NumberedLbrFactory.PREFIX, 32, 7), cut.getPotentialRivalGames(new GameCode(NumberedLbrFactory.PREFIX, 24, 7), 2).get(1));
        assertEquals(new GameCode(NumberedWbrFactory.PREFIX, 8, 1), cut.getPotentialRivalGames(new GameCode(NumberedLbrFactory.PREFIX, 24, 8), 2).get(0));
        assertEquals(new GameCode(NumberedLbrFactory.PREFIX, 32, 8), cut.getPotentialRivalGames(new GameCode(NumberedLbrFactory.PREFIX, 24, 8), 2).get(1));
        
        // games without wbr loser
        assertEquals(new GameCode(NumberedLbrFactory.PREFIX, 6, 1), cut.getPotentialRivalGames(new GameCode(NumberedLbrFactory.PREFIX, 4, 1), 3).get(0));
        assertEquals(new GameCode(NumberedLbrFactory.PREFIX, 6, 2), cut.getPotentialRivalGames(new GameCode(NumberedLbrFactory.PREFIX, 4, 1), 3).get(1));
        assertEquals(new GameCode(NumberedLbrFactory.PREFIX, 6, 1), cut.getPotentialRivalGames(new GameCode(NumberedLbrFactory.PREFIX, 4, 1), 5).get(0));
        assertEquals(new GameCode(NumberedLbrFactory.PREFIX, 6, 2), cut.getPotentialRivalGames(new GameCode(NumberedLbrFactory.PREFIX, 4, 1), 5).get(1));
        assertEquals(new GameCode(NumberedLbrFactory.PREFIX, 6, 1), cut.getPotentialRivalGames(new GameCode(NumberedLbrFactory.PREFIX, 4, 1), 7).get(0));
        assertEquals(new GameCode(NumberedLbrFactory.PREFIX, 6, 2), cut.getPotentialRivalGames(new GameCode(NumberedLbrFactory.PREFIX, 4, 1), 7).get(1));
        
        assertEquals(new GameCode(NumberedLbrFactory.PREFIX, 12, 1), cut.getPotentialRivalGames(new GameCode(NumberedLbrFactory.PREFIX, 8, 1), 3).get(0));
        assertEquals(new GameCode(NumberedLbrFactory.PREFIX, 12, 2), cut.getPotentialRivalGames(new GameCode(NumberedLbrFactory.PREFIX, 8, 1), 3).get(1));
        assertEquals(new GameCode(NumberedLbrFactory.PREFIX, 12, 3), cut.getPotentialRivalGames(new GameCode(NumberedLbrFactory.PREFIX, 8, 2), 3).get(0));
        assertEquals(new GameCode(NumberedLbrFactory.PREFIX, 12, 4), cut.getPotentialRivalGames(new GameCode(NumberedLbrFactory.PREFIX, 8, 2), 3).get(1));
        assertEquals(new GameCode(NumberedLbrFactory.PREFIX, 12, 1), cut.getPotentialRivalGames(new GameCode(NumberedLbrFactory.PREFIX, 8, 1), 5).get(0));
        assertEquals(new GameCode(NumberedLbrFactory.PREFIX, 12, 2), cut.getPotentialRivalGames(new GameCode(NumberedLbrFactory.PREFIX, 8, 1), 5).get(1));
        assertEquals(new GameCode(NumberedLbrFactory.PREFIX, 12, 3), cut.getPotentialRivalGames(new GameCode(NumberedLbrFactory.PREFIX, 8, 2), 5).get(0));
        assertEquals(new GameCode(NumberedLbrFactory.PREFIX, 12, 4), cut.getPotentialRivalGames(new GameCode(NumberedLbrFactory.PREFIX, 8, 2), 5).get(1));
        
        assertEquals(new GameCode(NumberedLbrFactory.PREFIX, 24, 1), cut.getPotentialRivalGames(new GameCode(NumberedLbrFactory.PREFIX, 16, 1), 3).get(0));
        assertEquals(new GameCode(NumberedLbrFactory.PREFIX, 24, 2), cut.getPotentialRivalGames(new GameCode(NumberedLbrFactory.PREFIX, 16, 1), 3).get(1));
        assertEquals(new GameCode(NumberedLbrFactory.PREFIX, 24, 3), cut.getPotentialRivalGames(new GameCode(NumberedLbrFactory.PREFIX, 16, 2), 3).get(0));
        assertEquals(new GameCode(NumberedLbrFactory.PREFIX, 24, 4), cut.getPotentialRivalGames(new GameCode(NumberedLbrFactory.PREFIX, 16, 2), 3).get(1));
        assertEquals(new GameCode(NumberedLbrFactory.PREFIX, 24, 5), cut.getPotentialRivalGames(new GameCode(NumberedLbrFactory.PREFIX, 16, 3), 3).get(0));
        assertEquals(new GameCode(NumberedLbrFactory.PREFIX, 24, 6), cut.getPotentialRivalGames(new GameCode(NumberedLbrFactory.PREFIX, 16, 3), 3).get(1));
        assertEquals(new GameCode(NumberedLbrFactory.PREFIX, 24, 7), cut.getPotentialRivalGames(new GameCode(NumberedLbrFactory.PREFIX, 16, 4), 3).get(0));
        assertEquals(new GameCode(NumberedLbrFactory.PREFIX, 24, 8), cut.getPotentialRivalGames(new GameCode(NumberedLbrFactory.PREFIX, 16, 4), 3).get(1));
        
        // games in wbr
        assertEquals(new GameCode(NumberedWbrFactory.PREFIX, 8, 7), cut.getPotentialRivalGames(new GameCode(NumberedWbrFactory.PREFIX, 4, 4), 2).get(0));
        assertEquals(new GameCode(NumberedWbrFactory.PREFIX, 8, 8), cut.getPotentialRivalGames(new GameCode(NumberedWbrFactory.PREFIX, 4, 4), 2).get(1));
    }

    private void assertDirection(GameCode degradedToGame, boolean degradedAsHometeam, Direction actual) {
        assertEquals(degradedToGame, actual.gameId);
        assertEquals(degradedAsHometeam, actual.isHomeTeam);
    }
}