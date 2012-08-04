package com.pduda.tourney.domain.fixture.twoko;

import com.pduda.tourney.domain.Team;
import com.pduda.tourney.domain.util.MyMath;
import java.io.Serializable;
import java.util.List;

public class PartiallySeededTeamAssigner implements TeamAssigner, Serializable {

    private static final long serialVersionUID = 1L;
    private TeamAssignerDirections directions = new TeamAssignerDirections();

    @Override
    public void assignTeams(Bracket bracket, List<Team> teams) {
        directions.createDirections((int) (Math.ceil(MyMath.log2(teams.size()))));

        for (Team team : teams) {
            assignTeam(bracket, team, 1);
        }
    }

    private void assignTeam(Bracket bracket, Team team, int tournamentStage) {
        boolean isHomeTeam = directions.getDirection(team.getSeed(), tournamentStage);

        if (bracket.isLeave()) {
            if (isHomeTeam) {
                bracket.getGame().setTeamHome(team);
            } else {
                bracket.getGame().setTeamAway(team);
            }

        } else {

            if (isHomeTeam) {
                assignTeam(bracket.getHomeBracket(), team, tournamentStage + 1);
            } else {
                assignTeam(bracket.getAwayBracket(), team, tournamentStage + 1);
            }

        }
    }
}
