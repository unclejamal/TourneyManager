package com.pduda.tourney.domain.fixture.twoko;

import com.pduda.tourney.domain.GameId;
import com.pduda.tourney.domain.Fixture;
import com.pduda.tourney.domain.Game;
import com.pduda.tourney.domain.report.Standings;
import com.pduda.tourney.domain.Team;
import com.pduda.tourney.domain.report.FullGamesReport;
import java.util.ArrayList;
import java.util.List;

public class Fixture2KO implements Fixture {

    public static final String FIN1 = GameId.PREFIX_FINAL + "1";
    public static final String FIN2 = GameId.PREFIX_FINAL + "2";
    
    private static final long serialVersionUID = 1L;
    private List<Team> teams = new ArrayList<Team>();
    private Bracket winnerBracket;
    private WinnerBracketFactory winnerBracketFactory = new WinnerBracketFactory();
    private Bracket loserBracket;
    private LoserBracketFactory loserBracketFactory = new LoserBracketFactory();
    private TeamAssigner teamAssigner = new PartiallySeededTeamAssigner();
    private final static PotentialRivalDirections potentialRivalDirections = new PotentialRivalDirections();
    private Bracket finalBracketOne = new Bracket(FIN1, 1, 1);
    private Bracket finalBracketTwo = new Bracket(FIN2, 1, 1);
    private Fixture2KOFullGamesReportFactory gamesReportFactory = new Fixture2KOFullGamesReportFactory();

    public Fixture2KO(List<Team> teams) {
        this.teams = teams;
        this.winnerBracket = winnerBracketFactory.createWinnerBracket(teams.size());
        this.loserBracket = loserBracketFactory.createLoserBracket(teams.size());
        teamAssigner.assignTeams(winnerBracket, teams);
        processByes(winnerBracket);
    }

    @Override
    public Standings getStandings() {
        // TODO
        Standings standings = new Standings();
        if (finalBracketTwo.getGame().isInState(Game.GameState.Finished)) {
            standings.addPlace("1", finalBracketTwo.getGame().getWinner());
            standings.addPlace("2", finalBracketTwo.getGame().getLoser());
        } else if (finalBracketOne.getGame().isInState(Game.GameState.Finished) && (!finalBracketTwo.getGame().isOccupied())) {
            standings.addPlace("1", finalBracketOne.getGame().getWinner());
            standings.addPlace("2", finalBracketOne.getGame().getLoser());
        }

        if (teams.size() < 3) {
            return standings;
        }

        collectStandings(loserBracket, standings);

        return standings;
    }

    private void collectStandings(Bracket bracket, Standings standings) {
        if (bracket.getGame().getLoser() != null) {
            standings.addPlace(bracket.getPlace(), bracket.getGame().getLoser());
        }
        if (bracket.getHomeBracket() != null) {
            collectStandings(bracket.getHomeBracket(), standings);
        }
        if (bracket.getAwayBracket() != null) {
            collectStandings(bracket.getAwayBracket(), standings);
        }
    }

    @Override
    public List<Game> getOngoingGames() {
        List<Game> toReturn = new ArrayList<Game>();
        toReturn.addAll(winnerBracket.findOngoingGames());
        toReturn.addAll(loserBracket.findOngoingGames());
        if (finalBracketOne.getGame().isInState(Game.GameState.Ongoing)) {
            toReturn.add(finalBracketOne.getGame());
        }
        if (finalBracketTwo.getGame().isInState(Game.GameState.Ongoing)) {
            toReturn.add(finalBracketTwo.getGame());
        }

        return toReturn;
    }

    @Override
    public void reportWinner(GameId gameId, int teamId) {
        Team winner = findTeam(teamId);

        // game in WBR
        Bracket gameBracket = winnerBracket.findBracket(gameId);
        if (gameBracket != null) {
            if (!gameBracket.isFinal()) {
                advanceWinnerToNextStage(gameBracket, winner);
            } else {
                gameBracket.setWinner(winner);
                finalBracketOne.getGame().setTeamHome(winner);
            }

            moveLoserToLosersBracket(gameBracket.getGame().getLoser(), gameId);

            return;
        }

        // game in LBR
        gameBracket = loserBracket.findBracket(gameId);
        if (gameBracket != null) {
            if (!gameBracket.isFinal()) {
                advanceWinnerToNextStage(gameBracket, winner);
            } else {
                gameBracket.setWinner(winner);
                finalBracketOne.getGame().setTeamAway(winner);
            }

            return;
        }

        // game in FIN1
        if (gameId.equals(finalBracketOne.getId())) {
            finalBracketOne.setWinner(winner);

            if (winner.equals(finalBracketOne.getGame().getTeamAway())) {
                finalBracketTwo.getGame().setTeamHome(finalBracketOne.getGame().getTeamHome());
                finalBracketTwo.getGame().setTeamAway(finalBracketOne.getGame().getTeamAway());
            }

            return;
        }

        // game in FIN2
        if (gameId.equals(finalBracketTwo.getId())) {
            finalBracketTwo.setWinner(winner);
        }
    }

    private Team findTeam(int teamId) {
        for (Team team : teams) {
            if (team.getId() == teamId) {
                return team;
            }
        }

        throw new RuntimeException("team not found");
    }

    private void advanceWinnerToNextStage(Bracket gameBracket, Team winner) {
        gameBracket.setWinner(winner);

        if (gameBracket.equals(gameBracket.getWinBracket().getHomeBracket())) {
            gameBracket.getWinBracket().getGame().setTeamHome(winner);
        }
        if (gameBracket.equals(gameBracket.getWinBracket().getAwayBracket())) {
            gameBracket.getWinBracket().getGame().setTeamAway(winner);
        }
    }

    private void moveLoserToLosersBracket(Team loser, GameId lostGameId) {
        Bracket lostGameBracket = winnerBracket.findBracket(lostGameId);
        PotentialRivalDirections.Direction direction = potentialRivalDirections.getDegradedTo(lostGameId, lostGameBracket.getBracketOrder());
        Bracket degradedToBracket = loserBracket.findBracket(direction.gameId);
        Bracket potentialRivalBracket = findPotentialRivalBracket(lostGameId);

        if (direction.isHomeTeam) {
            degradedToBracket.getGame().setTeamHome(loser);

            if ((degradedToBracket.getGame().getTeamAway() == null) && (isBracketDecided(potentialRivalBracket))) {
                //bye
                advanceWinnerToNextStage(degradedToBracket, loser);
            }

        } else {
            degradedToBracket.getGame().setTeamAway(loser);

            if ((degradedToBracket.getGame().getTeamHome() == null) && (isBracketDecided(potentialRivalBracket))) {
                //bye
                advanceWinnerToNextStage(degradedToBracket, loser);
            }

        }
    }

    private boolean isBracketDecided(Bracket bracket) {
        if (bracket.getGame().isInState(Game.GameState.Finished)) {
            return true;
        } else {
            if (bracket.getGame().isOccupied()) {
                return false;
            }

            List<GameId> sourceBracketIds = potentialRivalDirections.getPotentialRivalGames(bracket.getId(), bracket.getBracketOrder());

            Bracket sourceBracketOne = findBracket(sourceBracketIds.get(0));
            Bracket sourceBracketTwo = findBracket(sourceBracketIds.get(1));

            boolean isBracketOneDecided = isBracketDecided(sourceBracketOne);
            boolean isBracketTwoDecided = isBracketDecided(sourceBracketTwo);
            return ((isBracketOneDecided) && (isBracketTwoDecided));
        }
    }

    private Bracket findPotentialRivalBracket(GameId gameId) {
        Bracket lostGameBracket = winnerBracket.findBracket(gameId);
        GameId potentialRival = potentialRivalDirections.getPotentialRivalGame(gameId, lostGameBracket.getBracketOrder());
        if (potentialRival == null) {
            PotentialRivalDirections.Direction direction = potentialRivalDirections.getDegradedTo(gameId, lostGameBracket.getBracketOrder());
            Bracket degradedTo = loserBracket.findBracket(direction.gameId);
            if (direction.isHomeTeam) {
                return degradedTo.getAwayBracket();
            } else {
                return degradedTo.getHomeBracket();
            }
        }

        return findBracket(potentialRival);
    }

    private void processByes(Bracket bracket) {
        bracket.processByes();
    }

    @Override
    public List<Game> getWaitingGames() {
        List<Game> toReturn = new ArrayList<Game>();
        toReturn.addAll(loserBracket.findWaitingGames());
        toReturn.addAll(winnerBracket.findWaitingGames());

        if (finalBracketOne.getGame().isWaiting()) {
            toReturn.add(finalBracketOne.getGame());
        }
        if (finalBracketTwo.getGame().isWaiting()) {
            toReturn.add(finalBracketTwo.getGame());
        }
        return toReturn;
    }

    @Override
    public Game findGame(GameId gameId) {
        return findBracket(gameId).getGame();
    }

    @Override
    public FullGamesReport getGamesReports() {
        return gamesReportFactory.create(this);
    }

    private Bracket findBracket(GameId gameId) {
        Bracket wbrBracket = this.winnerBracket.findBracket(gameId);
        if (wbrBracket != null) {
            return wbrBracket;
        }

        Bracket lbrBracket = this.loserBracket.findBracket(gameId);
        if (lbrBracket != null) {
            return lbrBracket;
        }

        if (gameId.equals(finalBracketOne.getId())) {
            return finalBracketOne;
        }

        if (gameId.equals(finalBracketTwo.getId())) {
            return finalBracketTwo;
        }

        throw new RuntimeException("Bracket " + gameId + " not found");
    }

    public Bracket getWinnerBracket() {
        return winnerBracket;
    }

    public void setWinnerBracket(Bracket winnerBracket) {
        this.winnerBracket = winnerBracket;
    }

    public Bracket getLoserBracket() {
        return loserBracket;
    }

    public void setLoserBracket(Bracket loserBracket) {
        this.loserBracket = loserBracket;
    }

    public Bracket getFinalBracketOne() {
        return finalBracketOne;
    }

    public void setFinalBracketOne(Bracket finalBracketOne) {
        this.finalBracketOne = finalBracketOne;
    }

    public Bracket getFinalBracketTwo() {
        return finalBracketTwo;
    }

    public void setFinalBracketTwo(Bracket finalBracketTwo) {
        this.finalBracketTwo = finalBracketTwo;
    }
}
