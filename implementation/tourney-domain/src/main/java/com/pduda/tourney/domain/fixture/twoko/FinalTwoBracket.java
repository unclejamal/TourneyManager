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
@javax.persistence.Table(name = "FINAL_TWO_BRACKET")
@Configurable(autowire = Autowire.BY_TYPE)
public class FinalTwoBracket implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String FIN2 = GameCode.PREFIX_FINAL + "2";
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "FINAL_TWO_BRACKET_ID")
    private long id;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "HEAD")
    private Bracket head;

    public FinalTwoBracket() {
        this.head = new Bracket(FIN2, 1, 1);
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
    }

    public boolean contains(GameCode gameCode) {
        return gameCode.equals(head.getGameCode());
    }

    public void prepareFinalTwo(Team teamHome, Team teamAway) {
        head.getGame().setTeamHome(teamHome);
        head.getGame().setTeamAway(teamAway);
    }
}
