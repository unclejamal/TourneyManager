package com.pduda.tourney.domain;

import com.pduda.tourney.domain.util.MyUtils;
import java.util.HashSet;
import java.util.Set;

public class ObjectMother {

    public static FoosballTable upperTable = new FoosballTable("Upper");

    public static RankingPlayer createRankingPlayer() {
        PlayerDescription playerDescription = new PlayerDescription("Pawel Adam Duda", Gender.MALE, "Wroclaw", "WKPS");
        return new RankingPlayer(playerDescription, 69, "WKPS69", 1000, 20, 15, RankClass.MASTER);

    }

    public static Tourney createTourneyNotPlayed(String name, int teamsTotal) {
        Tourney tourney = new Tourney(name);
        tourney.addTable(upperTable);

        TourneyEvent event = new TourneyEvent(tourney, EventCategory.OD);
        tourney.addEvent(event);

        addTeams(event, teamsTotal);
        event.startEvent();

        return tourney;
    }

    public static Tourney createTourneyPlayed(String name, int teamsTotal) {
        Tourney tourney = createTourneyNotPlayed(name, teamsTotal);

        playTournament(MyUtils.any(tourney.getEvents()));

        return tourney;
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
            Team team = new Team(tourneyPlayer(seed + "A"), tourneyPlayer(seed + "B"));
            if (seeded) {
                team.setSeed(Integer.valueOf(seed));
            }
            toReturn.add(team);
        }

        return toReturn;
    }

    private static void addTeams(TourneyEvent tournament, int teamsTotal) throws NumberFormatException {
        Set<Team> teams = createSeededTeams(teamsTotal);
        for (Team team : teams) {
            tournament.addTeam(team);
        }
    }

    private static int playTournament(TourneyEvent tournament) {
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

    private static EventPlayer tourneyPlayer(String code) {
        return new EventPlayer(code, code);
    }
}