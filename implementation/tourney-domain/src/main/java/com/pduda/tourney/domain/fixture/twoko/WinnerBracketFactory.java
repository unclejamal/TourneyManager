package com.pduda.tourney.domain.fixture.twoko;

import com.pduda.tourney.domain.util.MyMath;
import java.io.Serializable;

public class WinnerBracketFactory implements Serializable {

    private static final long serialVersionUID = 1L;
    private NumberedWbrFactory winnerBracketNbf = new NumberedWbrFactory();

    public WinnerBracket createWinnerBracket(int teamsTotal, FinalOneBracket finalOneBracket, LoserBracket loserBracket) {
        Bracket head = winnerBracketNbf.createNextBracket();
        for (int i = 1; i < MyMath.log2(teamsTotal); i++) {
            expandWinnerBracket(head);
        }

        WinnerBracket winnerBracket = new WinnerBracket(head, finalOneBracket, loserBracket);
        return winnerBracket;
    }

    private void expandWinnerBracket(Bracket bracket) {
        if (bracket.getHomeBracket() != null) {
            expandWinnerBracket(bracket.getHomeBracket());
            expandWinnerBracket(bracket.getAwayBracket());
        } else {
            Bracket homeBracket = winnerBracketNbf.createNextBracket();
            homeBracket.setWinBracket(bracket);
            bracket.setHomeBracket(homeBracket);

            Bracket awayBracket = winnerBracketNbf.createNextBracket();
            awayBracket.setWinBracket(bracket);
            bracket.setAwayBracket(awayBracket);
        }
    }
}
