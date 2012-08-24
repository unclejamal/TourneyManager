package com.pduda.tourney.domain.fixture.twoko;

import com.pduda.tourney.domain.GameCode;
import com.pduda.tourney.domain.Game;
import com.pduda.tourney.domain.GameState;
import com.pduda.tourney.domain.Team;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Configurable;

@Entity
@javax.persistence.Table(name = "FIXTURE_2KO_BRACKET")
@Configurable(autowire = Autowire.BY_TYPE)
public class Bracket implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "FIXTURE_2KO_BRACKET_ID")
    private long id;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @PrimaryKeyJoinColumn
    private Game game;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="BRACKET_HOME")
    private Bracket homeBracket;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="BRACKET_AWAY")
    private Bracket awayBracket;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER) 
    @JoinColumn(name="BRACKET_WIN", insertable=false, updatable=false)
    private Bracket winBracket;
    @Column(name = "place")
    private String place;

    public Bracket(String prefix, int round, int match) {
        this.game = new Game(prefix, round, match);
    }

    /**
     * Just for JPA.
     */
    public Bracket() {
        // nothing to do
    }

    public List<Game> findWaitingGames() {
        List<Game> waitingGamesHome = new ArrayList<Game>();
        if (homeBracket != null) {
            waitingGamesHome = homeBracket.findWaitingGames();
        }
        List<Game> waitingGamesAway = new ArrayList<Game>();
        if (awayBracket != null) {
            waitingGamesAway = awayBracket.findWaitingGames();
        }

        List<Game> toReturn = new ArrayList<Game>();
        toReturn.addAll(waitingGamesHome);
        toReturn.addAll(waitingGamesAway);


        if ((toReturn.isEmpty()) && (getGame().isWaiting())) {
            toReturn.add(game);
        }

        return toReturn;
    }

    public List<Game> findOngoingGames() {
        if (game.isInGameState(GameState.Ongoing)) {
            List<Game> toReturn = new ArrayList<Game>();
            toReturn.add(game);

            return toReturn;
        }

        List<Game> ongoingGamesHome = new ArrayList<Game>();
        if (homeBracket != null) {
            ongoingGamesHome = homeBracket.findOngoingGames();
        }
        List<Game> ongoingGamesAway = new ArrayList<Game>();
        if (awayBracket != null) {
            ongoingGamesAway = awayBracket.findOngoingGames();
        }

        List<Game> toReturn = new ArrayList<Game>();
        toReturn.addAll(ongoingGamesHome);
        toReturn.addAll(ongoingGamesAway);
        return toReturn;
    }

    public Bracket findBracket(GameCode findMe) {
        if (findMe.equals(game.getGameCode())) {
            return this;
        }

        if (homeBracket != null) {
            Bracket homeBr = homeBracket.findBracket(findMe);
            if (homeBr != null) {
                return homeBr;
            }
        }

        if (awayBracket != null) {
            Bracket awayBr = awayBracket.findBracket(findMe);
            if (awayBr != null) {
                return awayBr;
            }
        }


        return null;
    }

    public void processByes() {
        if (isLeave()) {
            return;
        }

        if (homeBracket != null) {
            if (homeBracket.isHomeBye()) {
                homeBracket.getGame().setWinner(homeBracket.getGame().getTeamHome());
                game.setTeamHome(homeBracket.getWinner());
            } else if (homeBracket.isAwayBye()) {
                homeBracket.getGame().setWinner(homeBracket.getGame().getTeamAway());
                game.setTeamHome(homeBracket.getWinner());
            } else {
                homeBracket.processByes();
            }
        }

        if (awayBracket != null) {
            if (awayBracket.isHomeBye()) {
                awayBracket.getGame().setWinner(awayBracket.getGame().getTeamHome());
                game.setTeamAway(awayBracket.getWinner());
            } else if (awayBracket.isAwayBye()) {
                awayBracket.getGame().setWinner(awayBracket.getGame().getTeamAway());
                game.setTeamAway(awayBracket.getWinner());
            } else {
                awayBracket.processByes();
            }
        }
    }

    private boolean isHomeBye() {
        return (getGame().getTeamHome() != null) && (getGame().getTeamAway() == null);
    }

    private boolean isAwayBye() {
        return (getGame().getTeamHome() == null) && (getGame().getTeamAway() != null);
    }

    public boolean isLeave() {
        return (homeBracket == null) && (awayBracket == null);
    }

    // FIXME remove
    public String getRoundName() {
        return game.getGameCode().getPrefixedRound();
    }

    /**
     * Final - 1, semi - 2, etc.
     */
    public int getBracketsLeftToWin() {
        int gamesLefToWin = 1;
        Bracket i = this;
        while (i.winBracket != null) {
            i = i.winBracket;
            gamesLefToWin++;
        }
        return gamesLefToWin;
    }

    /**
     * Leaf - 1, next game - 2, etc.
     */
    public int getBracketOrder() {
        int bracketOrder = 1;
        Bracket i = this;
        while (i.awayBracket != null) {
            i = i.awayBracket;
            bracketOrder++;
        }
        return bracketOrder;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public GameCode getGameCode() {
        return game.getGameCode();
    }

    public Bracket getAwayBracket() {
        return awayBracket;
    }

    public void setAwayBracket(Bracket awayBracket) {
        this.awayBracket = awayBracket;
    }

    public Bracket getHomeBracket() {
        return homeBracket;
    }

    public void setHomeBracket(Bracket homeBracket) {
        this.homeBracket = homeBracket;
    }

    public Team getWinner() {
        return game.getWinner();
    }

    public void setWinner(Team winner) {
        this.game.setWinner(winner);
    }

    public Bracket getWinBracket() {
        return winBracket;
    }

    public void setWinBracket(Bracket winBracket) {
        this.winBracket = winBracket;
    }

    public boolean isFinal() {
        return winBracket == null;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
