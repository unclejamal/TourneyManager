package com.pduda.tourney.domain.fixture.twoko;

import com.pduda.tourney.domain.Game;
import com.pduda.tourney.domain.GameCode;
import com.pduda.tourney.domain.Team;
import com.pduda.tourney.domain.TourneyEvent;
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
@javax.persistence.Table(name = "FINAL_ONE_BRACKET")
@Configurable(autowire = Autowire.BY_TYPE)
public class FinalOneBracket implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String FIN1 = GameCode.PREFIX_FINAL + "1";
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "FINAL_ONE_BRACKET_ID")
    private long id;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "HEAD")
    private Bracket head;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "FINAL_TWO_BRACKET")
    private FinalTwoBracket finalTwoBracket;

    /**
     * JPA only.
     *
     * @deprecated
     */
    public FinalOneBracket() {
    }

    public FinalOneBracket(TourneyEvent tourneyEvent, FinalTwoBracket finalTwoBracket) {
        this.head = new Bracket(tourneyEvent, FIN1, 1, 1);
        this.finalTwoBracket = finalTwoBracket;
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

    public void reportWinner(Team winner) {
        head.setWinner(winner);

        if (winner.equals(head.getGame().getTeamAway())) {
            finalTwoBracket.prepareFinalTwo(head.getGame().getTeamHome(), head.getGame().getTeamAway());
        }
    }

    public boolean contains(GameCode gameCode) {
        return gameCode.equals(head.getGameCode());
    }

    public void winnerOfLoserBracket(Team winner) {
        head.getGame().setTeamAway(winner);
    }

    public void winnerOfWinnerBracket(Team winner) {
        head.getGame().setTeamHome(winner);
    }
}
