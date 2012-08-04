package com.pduda.tourney.domain.fixture.twoko;

import com.pduda.tourney.domain.Game;
import com.pduda.tourney.domain.Team;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RandomTeamAssigner implements TeamAssigner, Serializable {

    private static final long serialVersionUID = 1L;

    @Override
    public void assignTeams(Bracket bracket, List<Team> teams) {

        List<Team> randomTeams = randomizeList(teams);

        for (Team randomTeam : randomTeams) {
            assignTeam(bracket, randomTeam);
        }
    }

    private void assignTeam(Bracket bracket, Team team) {

        Bracket homeLeaf = findLeaf(bracket, FindLeafCriteria.FREE_HOME);

        if (homeLeaf != null) {
            homeLeaf.getGame().setTeamHome(team);
        } else {
            Bracket awayLeaf = findLeaf(bracket, FindLeafCriteria.FREE_AWAY);
            awayLeaf.getGame().setTeamAway(team);
        }
    }

    private List<Team> randomizeList(List<Team> teams) {
        List<Team> random = new ArrayList<Team>(teams);
        Collections.shuffle(random);
        return random;
    }

    private Bracket findLeaf(Bracket bracket, FindLeafCriteria criteria) {
        if (bracket.isLeave()) {
            if (criteria.matching(bracket)) {
                return bracket;
            } else {
                return null;
            }

        } else {
            Bracket bh = findLeaf(bracket.getHomeBracket(), criteria);
            if (bh != null) {
                return bh;
            } else {
                Bracket ba = findLeaf(bracket.getAwayBracket(), criteria);
                if (ba != null) {
                    return ba;
                }
            }
            return null;
        }
    }

    public static enum FindLeafCriteria {

        FREE_HOME, FREE_AWAY;

        public boolean matching(Bracket bracket) {
            if (FREE_HOME.equals(this)) {
                if (bracket.getGame().getTeamHome() == null) {
                    return true;
                }

            } else if (FREE_AWAY.equals(this)) {
                if (bracket.getGame().getTeamAway() == null) {
                    return true;
                }
            }

            return false;
        }
    }
}