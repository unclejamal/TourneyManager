package com.pduda.tourney.domain.fixture.twoko;

import com.pduda.tourney.domain.Fixture;
import com.pduda.tourney.domain.report.PartialGamesReport;
import com.pduda.tourney.domain.report.GamesReportRound;
import com.pduda.tourney.domain.report.FullGamesReport;

public class Fixture2KOFullGamesReportFactory implements GamesReportFactory {

    private static final long serialVersionUID = 1L;

    @Override
    public FullGamesReport create(Fixture fixture) {
        Fixture2KO f = (Fixture2KO) fixture;

        FullGamesReport full = new FullGamesReport();

        full.addPartialReport(processBracket("wbr", f.getWinnerBracket()));
        full.addPartialReport(processBracket("lbr", f.getLoserBracket()));
        full.addPartialReport(processBracket("fin1", f.getFinalBracketOne()));
        full.addPartialReport(processBracket("fin2", f.getFinalBracketTwo()));

        return full;
    }

    private PartialGamesReport processBracket(String reportName, Bracket br) {
        PartialGamesReport partial = new PartialGamesReport();
        partial.setName(reportName);
        partial.setWinner(br.getWinner());
        collectGamesReport(partial, br);

        return partial;
    }

    private void collectGamesReport(PartialGamesReport report, Bracket bracket) {
        GamesReportRound round = report.getRound(bracket.getRoundName());
        if (round == null) {
            round = new GamesReportRound(bracket.getRoundName(), bracket.getBracketOrder());
            report.addRound(round);
        }
        round.addGame(bracket.getGame());

        if (bracket.getHomeBracket() != null) {
            collectGamesReport(report, bracket.getHomeBracket());
        }
        if (bracket.getAwayBracket() != null) {
            collectGamesReport(report, bracket.getAwayBracket());
        }
    }
}
