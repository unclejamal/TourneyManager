package com.pduda.tourney.domain;

import java.util.ArrayList;
import java.util.List;

public class ObjectMother {

    public static Table upperTable = new Table("Upper");

    public static Player createPlayer() {
        return new Player(69, "WKPS69", "Pawel Adam Duda", Gender.MALE, "Wroclaw", "WKPS", 1000, 20, 15, RankClass.MASTER);
    }

    public static Tournament createTournament(String name, int teamsTotal) {
        Tournament toReturn = new Tournament(1, TournamentCategory.OD, name);
        toReturn.addTable(upperTable);
        addTeams(toReturn, teamsTotal);
        toReturn.startTournament();
        playTournament(toReturn);

        return toReturn;
    }

    public static List<Team> createSeededTeams(int teamsTotal) {
        return createTeams(teamsTotal, true);
    }
    
    public static List<Team> createUnseededTeams(int teamsTotal) {
        return createTeams(teamsTotal, false);
    }

    private static List<Team> createTeams(int teamsTotal, boolean seeded) {
        List<Team> toReturn = new ArrayList<Team>();

        for (int i = 0; i < teamsTotal; i++) {
            String seed = String.valueOf(i + 1);
            Team team = new Team(new Player(seed + "A"), new Player(seed + "B"));
            if (seeded) {
                team.setSeed(Integer.valueOf(seed));
            }
            toReturn.add(team);
        }

        return toReturn;
    }

    private static void addTeams(Tournament tournament, int teamsTotal) throws NumberFormatException {
        List<Team> teams = createSeededTeams(teamsTotal);
        for (Team team : teams) {
            tournament.addTeam(team);
        }

    }

    private static int playTournament(Tournament tournament) {
        int i = 0;
        while (tournament.getEndDate() == null) {
            Game game = tournament.getWaitingGames().get(0);
            tournament.startGame(game.getId());

            if (game.getTeamHome().getSeed() < game.getTeamAway().getSeed()) {
                tournament.reportWinner(game.getId(), game.getTeamHome().getId());
            } else {
                tournament.reportWinner(game.getId(), game.getTeamAway().getId());
            }

            i++;
        }

        return i;
    }
}