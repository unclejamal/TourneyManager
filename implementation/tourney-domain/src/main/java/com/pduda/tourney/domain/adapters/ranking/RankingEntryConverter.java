package com.pduda.tourney.domain.adapters.ranking;

import com.pduda.tourney.domain.Gender;
import com.pduda.tourney.domain.PlayerDescription;
import com.pduda.tourney.domain.RankClass;
import com.pduda.tourney.domain.ranking.Ranking;
import java.util.List;

public class RankingEntryConverter {

    public Ranking convert(List<RankingEntry> rankingEntries) {
        Ranking ranking = new Ranking();

        for (RankingEntry i : rankingEntries) {
            PlayerDescription playerDescription = new PlayerDescription(i.fullName, gender(i.gender), i.city, i.club);
            ranking.addPlayer(playerDescription, i.rank, i.code, i.points, i.pointsAdded, i.pointsDeleted, rankClass(i.rankClass));
        }

        return ranking;
    }

    private RankClass rankClass(String rankClass) {
        if ("NOVICE".equals(rankClass)) {
            return RankClass.NOVICE;
        } else if ("AMATOR".equals(rankClass)) {
            return RankClass.AMATOR;
        } else if (("SEMIPRO".equals(rankClass)) || ("SEMI PRO".equals(rankClass)) || ("SEMI-PRO".equals(rankClass))) {
            return RankClass.SEMIPRO;
        } else if ("PRO".equals(rankClass)) {
            return RankClass.PRO;
        } else if ("MASTER".equals(rankClass)) {
            return RankClass.MASTER;
        }

        return RankClass.NOTRANKED;
    }

    private Gender gender(String gender) {
        if ("M".equals(gender)) {
            return Gender.MALE;
        } else if ("K".equals(gender)) {
            return Gender.FEMALE;
        }

        return Gender.UNKNOWN;
    }
}
