package com.pduda.tourney.domain;

import java.util.HashSet;
import java.util.Set;

public class ObjectMother {

    public static FoosballTable upperTable = new FoosballTable("Upper");

    public static Player createPlayer() {
        return new Player(69, "WKPS69", "Pawel Adam Duda", Gender.MALE, "Wroclaw", "WKPS", 1000, 20, 15, RankClass.MASTER);
    }

    public static Tourney createTourneyNotPlayed(String name, int teamsTotal) {
        Tourney toReturn = new Tourney(1, TourneyCategory.OD, name);
        toReturn.addTable(upperTable);
        addTeams(toReturn, teamsTotal);
        toReturn.startTournament();

        return toReturn;
    }

    public static Tourney createTourneyPlayed(String name, int teamsTotal) {
        Tourney toReturn = new Tourney(1, TourneyCategory.OD, name);
        toReturn.addTable(upperTable);
        addTeams(toReturn, teamsTotal);
        toReturn.startTournament();
        playTournament(toReturn);

        return toReturn;
    }

    public static Set<Team> createSeededTeams(int teamsTotal) {
        return createTeams(teamsTotal, true);
    }

    public static Set<Team> createUnseededTeams(int teamsTotal) {
        return createTeams(teamsTotal, false);
    }

    private static Set<Team> createTeams(int teamsTotal, boolean seeded) {
        Set<Team> toReturn = new HashSet<Team>();

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

    private static void addTeams(Tourney tournament, int teamsTotal) throws NumberFormatException {
        Set<Team> teams = createSeededTeams(teamsTotal);
        for (Team team : teams) {
            tournament.addTeam(team);
        }
    }

    private static int playTournament(Tourney tournament) {
        int i = 0;
        while (tournament.getEndDate() == null) {
            Game game = tournament.getWaitingGames().get(0);
            tournament.startGame(game.getGameCode());

            if (game.getTeamHome().getSeed() < game.getTeamAway().getSeed()) {
                tournament.reportWinner(game.getGameCode(), game.getTeamHome().getTeamCode());
            } else {
                tournament.reportWinner(game.getGameCode(), game.getTeamAway().getTeamCode());
            }

            i++;
        }

        return i;
    }
}