package com.pduda.tourney.domain.fixture.twoko;

import com.pduda.tourney.domain.TourneyEvent;
import com.pduda.tourney.domain.util.MyMath;
import java.io.Serializable;

public class LoserBracketFactory implements Serializable {

    private static final long serialVersionUID = 1L;
    private NumberedLbrFactory loserBracketNbf;

    public LoserBracketFactory(TourneyEvent tourneyEvent) {
        this.loserBracketNbf = new NumberedLbrFactory(tourneyEvent);
    }

    public LoserBracket createLoserBracket(int teamsTotal, FinalOneBracket finalOneBracket) {
        Bracket lbr3 = loserBracketNbf.createNextBracket();
        Bracket lbr4 = loserBracketNbf.createNextBracket();
        lbr4.setWinBracket(lbr3);
        lbr3.setAwayBracket(lbr4);

        for (int i = 0; i < 2; i++) {
            for (int j = 2; j < MyMath.log2(teamsTotal); j++) {
                expandLoserBracket(lbr3.getAwayBracket());
            }
        }

        LoserBracket toReturn = new LoserBracket(lbr3, finalOneBracket);
        return toReturn;
    }

    private void expandLoserBracket(Bracket bracket) {
        if (bracket.getHomeBracket() != null) {
            expandLoserBracket(bracket.getHomeBracket());
        }
        if (bracket.getAwayBracket() != null) {
            expandLoserBracket(bracket.getAwayBracket());
        } else {
            if (bracket.getWinBracket().getHomeBracket() == null) {
                Bracket homeBracket = loserBracketNbf.createNextBracket();
                homeBracket.setWinBracket(bracket);
                bracket.setHomeBracket(homeBracket);

                Bracket awayBracket = loserBracketNbf.createNextBracket();
                awayBracket.setWinBracket(bracket);
                bracket.setAwayBracket(awayBracket);
            } else {

                // 2nd level expand
                Bracket awayBracket = loserBracketNbf.createNextBracket();
                awayBracket.setWinBracket(bracket);
                bracket.setAwayBracket(awayBracket);
            }
        }
    }
}
