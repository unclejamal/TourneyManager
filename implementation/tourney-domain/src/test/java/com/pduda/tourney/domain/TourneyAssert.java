package com.pduda.tourney.domain;

import java.util.List;
import static org.junit.Assert.*;

public class TourneyAssert {

    public static final String EMPTY = "";

    public static void assertGamesContain(String prefix, int homeSeed, int awaySeed, List<Game> games) {
        boolean contain = false;
        for (Game game : games) {
            if (isThisGame(homeSeed, awaySeed, game)) {
                contain = true;
                break;
            }
        }

        assertTrue(prefix + " " + "game " + homeSeed + "vs" + awaySeed + " should be present", contain);
    }

    public static void assertGame(String prefix, int homeSeed, int awaySeed, Game game) {
        if (homeSeed != 0) {
            assertEquals(prefix + " " + game.getId() + " home should be " + homeSeed, homeSeed, game.getTeamHome().getSeed());
        } else {
            assertNull(prefix + " " + game.getId() + " home should be empty (bye)", game.getTeamHome());
        }

        if (awaySeed != 0) {
            assertEquals(prefix + " " + game.getId() + " away should be " + awaySeed, awaySeed, game.getTeamAway().getSeed());
        } else {
            assertNull(prefix + " " + game.getId() + " away should be empty (bye)", game.getTeamAway());
        }
    }

    public static void assertGame(int homeSeed, int awaySeed, Game game) {
        assertGame(EMPTY, homeSeed, awaySeed, game);
    }

    private static boolean isThisGame(int homeSeed, int awaySeed, Game game) {
        if (homeSeed != 0) {
            if (homeSeed != game.getTeamHome().getSeed()) {
                return false;
            }
        } else {
            if (null != game.getTeamHome()) {
                return false;
            }
        }

        if (awaySeed != 0) {
            if (awaySeed != game.getTeamAway().getSeed()) {
                return false;
            }
        } else {
            if (null != game.getTeamAway()) {
                return false;
            }
        }

        return true;
    }
}
