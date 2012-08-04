package com.pduda.tourney.domain.fixture.twoko;

import com.pduda.tourney.domain.Team;
import com.pduda.tourney.domain.util.MyMath;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PartiallySeededTeamAssigner implements TeamAssigner, Serializable {

    private static final long serialVersionUID = 1L;
    private TeamAssignerDirections directions = new TeamAssignerDirections();

    @Override
    public void assignTeams(Bracket bracket, List<Team> teams) {
        directions.createDirections((int) (Math.ceil(MyMath.log2(teams.size()))));


        List<Team> seededTeams = new ArrayList<Team>();
        List<Team> unseededTeams = new ArrayList<Team>();
        for (Team team : teams) {
            if (team.isSeeded()) {
                seededTeams.add(team);
            } else {
                unseededTeams.add(team);
            }
        }
        assignSeededTeams(bracket, seededTeams);
        randomlyAssignUnseeded(bracket, unseededTeams, seededTeams.size());
    }

    private void assignSeededTeams(Bracket bracket, List<Team> seededTeams) {
        for (Team team : seededTeams) {
            assignTeam(bracket, team, team.getSeed(), 1);
        }
    }

    private void randomlyAssignUnseeded(Bracket bracket, List<Team> unseededTeams, int seededTeamsTotal) {
        Collections.shuffle(unseededTeams);
        for (int i = 0; i < unseededTeams.size(); i++) {
            Team team = unseededTeams.get(i);
            assignTeam(bracket, team, i + 1 + seededTeamsTotal, 1);
        }
    }

    private void assignTeam(Bracket bracket, Team team, int pseudoSeed, int tournamentStage) {
        boolean isHomeTeam = directions.getDirection(pseudoSeed, tournamentStage);

        if (bracket.isLeave()) {
            if (isHomeTeam) {
                bracket.getGame().setTeamHome(team);
            } else {
                bracket.getGame().setTeamAway(team);
            }

        } else {

            if (isHomeTeam) {
                assignTeam(bracket.getHomeBracket(), team, pseudoSeed, tournamentStage + 1);
            } else {
                assignTeam(bracket.getAwayBracket(), team, pseudoSeed, tournamentStage + 1);
            }

        }
    }
}
