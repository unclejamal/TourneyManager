package com.pduda.tourney.domain.fixture.twoko;

import com.pduda.tourney.domain.GameCode;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PotentialRivalDirections implements Serializable {

    private static final long serialVersionUID = 1L;

    public Direction getDegradedTo(GameCode id, int roundOrder) {
        int gameIdRound = id.getRound();
        int gameIdMatch = id.getMatch();

        if (roundOrder == 1) {
            // first game
            int degradedToGameIdRound = gameIdRound * 2;
            int degradedToGameIdMatch = ((int) (gameIdMatch - 1) / 2) + 1;
            GameCode degradedToGameId = new GameCode(NumberedLbrFactory.PREFIX, degradedToGameIdRound, degradedToGameIdMatch);
            boolean degradedToAsHome = (gameIdMatch % 2 == 1);
            return new Direction(degradedToGameId, degradedToAsHome);
        } else {
            // further games
            boolean degradedToAsHome = true;
            if (roundOrder % 2 == 1) {
                // no crossing
                int degradedToGameIdRound = gameIdRound * 3;
                int degradedToGameIdMatch = gameIdMatch;
                GameCode degradedToGameId = new GameCode(NumberedLbrFactory.PREFIX, degradedToGameIdRound, degradedToGameIdMatch);
                return new Direction(degradedToGameId, degradedToAsHome);
            } else {
                // crossing
                int degradedToGameIdRound = gameIdRound * 3;
                int degradedToGameIdMatch = (gameIdRound - gameIdMatch + 1);
                GameCode degradedToGameId = new GameCode(NumberedLbrFactory.PREFIX, degradedToGameIdRound, degradedToGameIdMatch);
                return new Direction(degradedToGameId, degradedToAsHome);
            }
        }
    }

    public GameCode getPotentialRivalGame(GameCode id, int roundOrder) {
        int gameIdRound = id.getRound();
        int gameIdMatch = id.getMatch();

        if (roundOrder == 1) {
            // first game
            int degradedToGameIdRound = gameIdRound;
            int degradedToGameIdMatch = gameIdMatch;
            if (gameIdMatch % 2 == 0) {
                degradedToGameIdMatch--;
            } else {
                degradedToGameIdMatch++;
            }
            return new GameCode(NumberedWbrFactory.PREFIX, degradedToGameIdRound, degradedToGameIdMatch);

        } else {
            // further games
            if (roundOrder % 2 == 1) {
                // no crossing
                int degradedToGameIdRound = gameIdRound * 4;
                int degradedToGameIdMatch = gameIdMatch;
                return new GameCode(NumberedLbrFactory.PREFIX, degradedToGameIdRound, degradedToGameIdMatch);
            } else {
                // crossing
                int degradedToGameIdRound = gameIdRound * 4;
                int degradedToGameIdMatch = (gameIdRound - gameIdMatch + 1);
                return new GameCode(NumberedLbrFactory.PREFIX, degradedToGameIdRound, degradedToGameIdMatch);
            }
        }
    }

    public List<GameCode> getPotentialRivalGames(GameCode id, int roundOrder) {
        List<GameCode> toReturn = new ArrayList<GameCode>();

        int gameIdRound = id.getRound();
        int gameIdMatch = id.getMatch();
        String prefix = id.getPrefix();

        if (NumberedWbrFactory.PREFIX.equals(prefix)) {
            // wbr
            if (roundOrder != 1) {
                int degradedToGameIdRound = gameIdRound * 2;
                int degradedToGameIdMatch1 = gameIdMatch * 2 - 1;
                int degradedToGameIdMatch2 = gameIdMatch * 2;
                toReturn.add(new GameCode(NumberedWbrFactory.PREFIX, degradedToGameIdRound, degradedToGameIdMatch1));
                toReturn.add(new GameCode(NumberedWbrFactory.PREFIX, degradedToGameIdRound, degradedToGameIdMatch2));
            } else {
                // no potential rivals 
            }

        } else {
            // lbr
            if (roundOrder == 1) {
                // first game
                int degradedToGameIdRound = (int) (gameIdRound / 2);
                int degradedToGameIdMatch1 = gameIdMatch * 2 - 1;
                int degradedToGameIdMatch2 = gameIdMatch * 2;
                toReturn.add(new GameCode(NumberedWbrFactory.PREFIX, degradedToGameIdRound, degradedToGameIdMatch1));
                toReturn.add(new GameCode(NumberedWbrFactory.PREFIX, degradedToGameIdRound, degradedToGameIdMatch2));

            } else {
                // further games
                if (gameIdRound % 3 == 0) {
                    // game against wbr loser
                    int degradedToGameIdRound1 = (int) (gameIdRound / 3);
                    int degradedToGameIdRound2 = (int) (gameIdRound / 3) + gameIdRound;
                    int degradedToGameIdMatch2 = gameIdMatch;
                    if (roundOrder % 4 == 0) {
                        // no crossing
                        int degradedToGameIdMatch1 = gameIdMatch;
                        toReturn.add(new GameCode(NumberedWbrFactory.PREFIX, degradedToGameIdRound1, degradedToGameIdMatch1));
                        toReturn.add(new GameCode(NumberedLbrFactory.PREFIX, degradedToGameIdRound2, degradedToGameIdMatch2));
                    } else {
                        // crossing
                        int degradedToGameIdMatch1 = (((int) (gameIdRound / 3)) - gameIdMatch + 1);
                        toReturn.add(new GameCode(NumberedWbrFactory.PREFIX, degradedToGameIdRound1, degradedToGameIdMatch1));
                        toReturn.add(new GameCode(NumberedLbrFactory.PREFIX, degradedToGameIdRound2, degradedToGameIdMatch2));
                    }
                } else {
                    // game without wbr loser
                    int degradedToGameIdRound = ((int) (gameIdRound / 2) + gameIdRound);
                    int degradedToGameIdMatch1 = (gameIdMatch - 1) * 2 + 1;
                    int degradedToGameIdMatch2 = (gameIdMatch - 1) * 2 + 2;
                    toReturn.add(new GameCode(NumberedLbrFactory.PREFIX, degradedToGameIdRound, degradedToGameIdMatch1));
                    toReturn.add(new GameCode(NumberedLbrFactory.PREFIX, degradedToGameIdRound, degradedToGameIdMatch2));

                }
            }
        }

        return toReturn;
    }

    public static class Direction implements Serializable {

        private static final long serialVersionUID = 1L;
        public GameCode gameId;
        public boolean isHomeTeam;

        public Direction(GameCode gameId, boolean isHomeTeam) {
            this.gameId = gameId;
            this.isHomeTeam = isHomeTeam;
        }

        @Override
        public String toString() {
            return "Direction{" + "gameId=" + gameId + ", isHomeTeam=" + isHomeTeam + '}';
        }
    }
}
