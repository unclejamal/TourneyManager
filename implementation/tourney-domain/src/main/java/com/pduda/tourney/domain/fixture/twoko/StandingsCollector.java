package com.pduda.tourney.domain.fixture.twoko;

import com.pduda.tourney.domain.GameState;
import com.pduda.tourney.domain.report.Standings;
import java.io.Serializable;

public class StandingsCollector implements Serializable {

    private static final long serialVersionUID = 1L;

    public Standings getStandings(FinalTwoBracket finalTwoBracket, FinalOneBracket finalOneBracket, LoserBracket loserBracket) {
        Standings standings = new Standings();
        if (finalTwoBracket.getHead().getGame().isInGameState(GameState.Finished)) {
            standings.addPlace("1", finalTwoBracket.getHead().getGame().getWinner());
            standings.addPlace("2", finalTwoBracket.getHead().getGame().getLoser());
        } else if (finalOneBracket.getHead().getGame().isInGameState(GameState.Finished) && (!finalTwoBracket.getHead().getGame().isOccupied())) {
            standings.addPlace("1", finalOneBracket.getHead().getGame().getWinner());
            standings.addPlace("2", finalOneBracket.getHead().getGame().getLoser());
        }

        collectStandings(loserBracket.getHead(), standings);

        return standings;
    }

    private void collectStandings(Bracket bracket, Standings standings) {
        if (bracket.getGame().getLoser() != null) {
            standings.addPlace(bracket.getPlace(), bracket.getGame().getLoser());
        }

        if (bracket.getHomeBracket() != null) {
            collectStandings(bracket.getHomeBracket(), standings);
        }
        if (bracket.getAwayBracket() != null) {
            collectStandings(bracket.getAwayBracket(), standings);
        }
    }
}
