package com.pduda.tourney.domain.fixture.twoko;

import com.pduda.tourney.domain.Game;
import com.pduda.tourney.domain.GameCode;
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
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Configurable;

@Entity
@javax.persistence.Table(name = "LOSER_BRACKET")
@Configurable(autowire = Autowire.BY_TYPE)
public class LoserBracket implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "LOSER_BRACKET_ID")
    private long id;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "HEAD")
    private Bracket head;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "FINAL_ONE_BRACKET")
    private FinalOneBracket finalOneBracket;

    public LoserBracket(Bracket head, FinalOneBracket finalOneBracket) {
        this.head = head;
        this.finalOneBracket = finalOneBracket;
    }

    /**
     * JPA only.
     *
     * @deprecated
     */
    public LoserBracket() {
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
            finalOneBracket.winnerOfLoserBracket(winner);
        }
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
}
