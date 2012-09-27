package com.pduda.tourney.domain.fixture.twoko;

import com.pduda.tourney.domain.Game;
import com.pduda.tourney.domain.GameCode;
import com.pduda.tourney.domain.GameState;
import com.pduda.tourney.domain.Team;
import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Transient;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Configurable;

@Entity
@javax.persistence.Table(name = "WINNER_BRACKET")
@Configurable(autowire = Autowire.BY_TYPE)
public class WinnerBracket implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "WINNER_BRACKET_ID")
    private long id;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "HEAD")
    private Bracket head;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "FINAL_ONE_BRACKET")
    private FinalOneBracket finalOneBracket;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "LOSER_BRACKET")
    private LoserBracket loserBracket;
    @Transient
    private PotentialRivalDirections potentialRivalDirections;

    public WinnerBracket(Bracket head, FinalOneBracket finalOneBracket, LoserBracket loserBracket) {
        init();
        this.head = head;
        this.finalOneBracket = finalOneBracket;
        this.loserBracket = loserBracket;
    }

    /**
     * JPA only.
     *
     * @deprecated
     */
    public WinnerBracket() {
        init();
    }

    private void init() {
        potentialRivalDirections = new PotentialRivalDirections();
    }

    public Bracket getHead() {
        return head;
    }

    public List<? extends Game> findOngoingGames() {
        return head.findOngoingGames();
    }

    public Bracket findBracket(GameCode gameCode) {
        return head.findBracket(gameCode);
    }

    public void reportWinner(GameCode gameCode, Team winner) {
        Bracket gameBracket = findBracket(gameCode);

        if (!gameBracket.isFinal()) {
            advanceWinnerToNextStage(gameBracket, winner);
        } else {
            gameBracket.setWinner(winner);
            finalOneBracket.winnerOfWinnerBracket(winner);
        }

        moveLoserToLosersBracket(gameBracket.getGame().getLoser(), gameCode);
    }

    public boolean contains(GameCode gameCode) {
        return findBracket(gameCode) != null;
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
        Bracket lostGameBracket = findBracket(lostGameId);

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

            Bracket sourceBracketOne = findBracketInWinnerOrLoserBracket(sourceBracketIds.get(0));
            Bracket sourceBracketTwo = findBracketInWinnerOrLoserBracket(sourceBracketIds.get(1));

            boolean isBracketOneDecided = isBracketDecided(sourceBracketOne);
            boolean isBracketTwoDecided = isBracketDecided(sourceBracketTwo);
            return ((isBracketOneDecided) && (isBracketTwoDecided));
        }
    }

    private Bracket findPotentialRivalBracket(GameCode gameId) {
        Bracket lostGameBracket = findBracket(gameId);
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

        return findBracketInWinnerOrLoserBracket(potentialRival);
    }

    private Bracket findBracketInWinnerOrLoserBracket(GameCode potentialRival) {
        Bracket findBracket = head.findBracket(potentialRival);
        if (findBracket != null) {
            return findBracket;
        }

        return loserBracket.findBracket(potentialRival);
    }
}
