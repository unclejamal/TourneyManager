package com.pduda.tourney.domain.fixture.twoko;

import com.pduda.tourney.domain.GameState;
import com.pduda.tourney.domain.report.Standings;
import java.io.Serializable;

public class StandingsCollector implements Serializable {

    private static final long serialVersionUID = 1L;

    public Standings getStandings(Bracket finalBracketTwo, Bracket finalBracketOne, Bracket loserBracket) {
        Standings standings = new Standings();
        if (finalBracketTwo.getGame().isInGameState(GameState.Finished)) {
            standings.addPlace("1", finalBracketTwo.getGame().getWinner());
            standings.addPlace("2", finalBracketTwo.getGame().getLoser());
        } else if (finalBracketOne.getGame().isInGameState(GameState.Finished) && (!finalBracketTwo.getGame().isOccupied())) {
            standings.addPlace("1", finalBracketOne.getGame().getWinner());
            standings.addPlace("2", finalBracketOne.getGame().getLoser());
        }

        collectStandings(loserBracket, standings);

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
