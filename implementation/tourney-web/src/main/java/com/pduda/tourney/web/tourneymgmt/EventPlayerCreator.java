package com.pduda.tourney.web.tourneymgmt;

import com.pduda.tourney.domain.EventCategory;
import com.pduda.tourney.domain.EventPlayer;
import com.pduda.tourney.domain.RankingPlayer;
import com.pduda.tourney.domain.ranking.Ranking;
import com.pduda.tourney.domain.service.payments.PaymentsHandler;
import com.pduda.tourney.domain.service.ranking.RankingHandler;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;

@Named
public class EventPlayerCreator {

    @Inject
    private RankingHandler rankingHandler;
    @Inject
    private PaymentsHandler feeHandler;

    public EventPlayer createEventPlayer(String fullName, EventCategory category) {
        List<RankingPlayer> rankedPlayersWithThisName = getRanking(category).getPlayersByFullName(fullName);
        RankingPlayer player;
        // TODO many players with same name
        if (rankedPlayersWithThisName.size() > 0) {
            player = rankedPlayersWithThisName.get(0);
        } else {
            player = new RankingPlayer(fullName);
        }

        EventPlayer eventPlayer = new EventPlayer(player);
        eventPlayer.setFee(feeHandler.getDefaultFee(player, category));
        return eventPlayer;
    }

    private Ranking getRanking(EventCategory category) {
        switch (category) {
            case AS:
                return rankingHandler.getPzfsRanking().getOpenSingle();
            case OS:
                return rankingHandler.getPzfsRanking().getOpenSingle();
            case AD:
                return rankingHandler.getPzfsRanking().getOpenDouble();
            case OD:
                return rankingHandler.getPzfsRanking().getOpenDouble();
            case WS:
                return rankingHandler.getPzfsRanking().getWomenSingle();
            case WD:
                return rankingHandler.getPzfsRanking().getWomenDouble();
            default:
                throw new RuntimeException("Not existing type");
        }

        // TODO add dyp (and specials)
    }
}
