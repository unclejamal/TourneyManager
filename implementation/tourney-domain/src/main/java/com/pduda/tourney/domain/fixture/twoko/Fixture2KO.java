package com.pduda.tourney.domain.fixture.twoko;

import com.pduda.tourney.domain.GameCode;
import com.pduda.tourney.domain.Fixture;
import com.pduda.tourney.domain.Game;
import com.pduda.tourney.domain.GameState;
import com.pduda.tourney.domain.report.Standings;
import com.pduda.tourney.domain.Team;
import com.pduda.tourney.domain.Tourney;
import com.pduda.tourney.domain.report.FullGamesReport;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Configurable;

@Entity
@javax.persistence.Table(name = "FIXTURE_2KO")
@Configurable(autowire = Autowire.BY_TYPE)
public class Fixture2KO implements Fixture {

    private static final long serialVersionUID = 1L;
    public static final String FIN1 = GameCode.PREFIX_FINAL + "1";
    public static final String FIN2 = GameCode.PREFIX_FINAL + "2";
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "FIXTURE_2KO_ID")
    private long id;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @PrimaryKeyJoinColumn
    private Bracket winnerBracket;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @PrimaryKeyJoinColumn
    private Bracket loserBracket;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @PrimaryKeyJoinColumn
    private Bracket finalBracketOne = new Bracket(FIN1, 1, 1);
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @PrimaryKeyJoinColumn
    private Bracket finalBracketTwo = new Bracket(FIN2, 1, 1);
    @Transient
    private PotentialRivalDirections potentialRivalDirections = new PotentialRivalDirections();
    @Transient
    private TeamAssigner teamAssigner = new PartiallySeededTeamAssigner();
    @Transient
    private WinnerBracketFactory winnerBracketFactory = new WinnerBracketFactory();
    @Transient
    private LoserBracketFactory loserBracketFactory = new LoserBracketFactory();
    @Transient
    private GamesReportFactory gamesReportFactory = new Fixture2KOFullGamesReportFactory();
    @OneToOne
    private Tourney tourney;

    public Fixture2KO(Tourney tourney) {
        init();
        this.tourney = tourney;
        this.winnerBracket = winnerBracketFactory.createWinnerBracket(tourney.getTeams().size());
        this.loserBracket = loserBracketFactory.createLoserBracket(tourney.getTeams().size());
        teamAssigner.assignTeams(winnerBracket, tourney.getTeams());
        processByes(winnerBracket);
    }

    /**
     * JPA only.
     */
    public Fixture2KO() {
        init();
    }

    private void init() {
        potentialRivalDirections = new PotentialRivalDirections();
        teamAssigner = new PartiallySeededTeamAssigner();
        winnerBracketFactory = new WinnerBracketFactory();
        loserBracketFactory = new LoserBracketFactory();
        gamesReportFactory = new Fixture2KOFullGamesReportFactory();
    }

    @Override
    public Standings getStandings() {
        // TODO
        Standings standings = new Standings();
        if (finalBracketTwo.getGame().isInGameState(GameState.Finished)) {
            standings.addPlace("1", finalBracketTwo.getGame().getWinner());
            standings.addPlace("2", finalBracketTwo.getGame().getLoser());
        } else if (finalBracketOne.getGame().isInGameState(GameState.Finished) && (!finalBracketTwo.getGame().isOccupied())) {
            standings.addPlace("1", finalBracketOne.getGame().getWinner());
            standings.addPlace("2", finalBracketOne.getGame().getLoser());
        }

        if (tourney.getTeams().size() < 3) {
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
        if (finalBracketOne.getGame().isInGameState(GameState.Ongoing)) {
            toReturn.add(finalBracketOne.getGame());
        }
        if (finalBracketTwo.getGame().isInGameState(GameState.Ongoing)) {
            toReturn.add(finalBracketTwo.getGame());
        }

        return toReturn;
    }

    @Override
    public void reportWinner(GameCode gameCode, long winnerTeamCode) {
        Team winner = findTeam(winnerTeamCode);

        // game in WBR
        Bracket gameBracket = winnerBracket.findBracket(gameCode);
        if (gameBracket != null) {
            if (!gameBracket.isFinal()) {
                advanceWinnerToNextStage(gameBracket, winner);
            } else {
                gameBracket.setWinner(winner);
                finalBracketOne.getGame().setTeamHome(winner);
            }

            moveLoserToLosersBracket(gameBracket.getGame().getLoser(), gameCode);

            return;
        }

        // game in LBR
        gameBracket = loserBracket.findBracket(gameCode);
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
        if (gameCode.equals(finalBracketOne.getGameCode())) {
            finalBracketOne.setWinner(winner);

            if (winner.equals(finalBracketOne.getGame().getTeamAway())) {
                finalBracketTwo.getGame().setTeamHome(finalBracketOne.getGame().getTeamHome());
                finalBracketTwo.getGame().setTeamAway(finalBracketOne.getGame().getTeamAway());
            }

            return;
        }

        // game in FIN2
        if (gameCode.equals(finalBracketTwo.getGameCode())) {
            finalBracketTwo.setWinner(winner);
        }
    }

    private Team findTeam(long teamId) {
        for (Team team : tourney.getTeams()) {
            if (team.getTeamCode() == teamId) {
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

    private void moveLoserToLosersBracket(Team loser, GameCode lostGameId) {
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
        if (bracket.getGame().isInGameState(GameState.Finished)) {
            return true;
        } else {
            if (bracket.getGame().isOccupied()) {
                return false;
            }

            List<GameCode> sourceBracketIds = potentialRivalDirections.getPotentialRivalGames(bracket.getGameCode(), bracket.getBracketOrder());

            Bracket sourceBracketOne = findBracket(sourceBracketIds.get(0));
            Bracket sourceBracketTwo = findBracket(sourceBracketIds.get(1));

            boolean isBracketOneDecided = isBracketDecided(sourceBracketOne);
            boolean isBracketTwoDecided = isBracketDecided(sourceBracketTwo);
            return ((isBracketOneDecided) && (isBracketTwoDecided));
        }
    }

    private Bracket findPotentialRivalBracket(GameCode gameId) {
        Bracket lostGameBracket = winnerBracket.findBracket(gameId);
        GameCode potentialRival = potentialRivalDirections.getPotentialRivalGame(gameId, lostGameBracket.getBracketOrder());
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
    public Game findGame(GameCode gameId) {
        return findBracket(gameId).getGame();
    }

    @Override
    public FullGamesReport getGamesReports() {
        return gamesReportFactory.create(this);
    }

    private Bracket findBracket(GameCode gameId) {
        Bracket wbrBracket = this.winnerBracket.findBracket(gameId);
        if (wbrBracket != null) {
            return wbrBracket;
        }

        Bracket lbrBracket = this.loserBracket.findBracket(gameId);
        if (lbrBracket != null) {
            return lbrBracket;
        }

        if (gameId.equals(finalBracketOne.getGameCode())) {
            return finalBracketOne;
        }

        if (gameId.equals(finalBracketTwo.getGameCode())) {
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Tourney getTourney() {
        return tourney;
    }

    public void setTourney(Tourney tourney) {
        this.tourney = tourney;
    }
}
