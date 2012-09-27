package com.pduda.tourney.domain.fixture.twoko;

import com.pduda.tourney.domain.Fixture;
import com.pduda.tourney.domain.FoosballTable;
import com.pduda.tourney.domain.Game;
import com.pduda.tourney.domain.GameCode;
import com.pduda.tourney.domain.GameState;
import com.pduda.tourney.domain.Team;
import com.pduda.tourney.domain.TourneyEvent;
import com.pduda.tourney.domain.report.FullGamesReport;
import com.pduda.tourney.domain.report.Standings;
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
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "FIXTURE_2KO_ID")
    private long id;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "BRACKET_WINNER")
    private WinnerBracket winnerBracket;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "BRACKET_LOSER")
    private LoserBracket loserBracket;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "BRACKET_FIN1")
    private FinalOneBracket finalOneBracket;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "BRACKET_FIN2")
    private FinalTwoBracket finalTwoBracket;
    @Transient
    private TeamAssigner teamAssigner;
    @Transient
    private WinnerBracketFactory winnerBracketFactory;
    @Transient
    private LoserBracketFactory loserBracketFactory;
    @Transient
    private GamesReportFactory gamesReportFactory;
    @Transient
    private StandingsCollector standingsCollector;
    @OneToOne(mappedBy = "fixture")
    private TourneyEvent event;

    public Fixture2KO(TourneyEvent event) {
        init();
        this.event = event;
        this.finalTwoBracket = new FinalTwoBracket();
        this.finalOneBracket = new FinalOneBracket(finalTwoBracket);
        this.loserBracket = loserBracketFactory.createLoserBracket(event.getTeams().size(), finalOneBracket);
        this.winnerBracket = winnerBracketFactory.createWinnerBracket(event.getTeams().size(), finalOneBracket, loserBracket);

        teamAssigner.assignTeams(winnerBracket.getHead(), event.getTeams());
        processByes(winnerBracket);
    }

    public Fixture2KO() {
        // JPA
        init();
    }

    private void init() {
        teamAssigner = new PartiallySeededTeamAssigner();
        winnerBracketFactory = new WinnerBracketFactory();
        loserBracketFactory = new LoserBracketFactory();
        gamesReportFactory = new Fixture2KOFullGamesReportFactory();
        standingsCollector = new StandingsCollector();
    }

    @Override
    public Standings getStandings() {
        return standingsCollector.getStandings(finalTwoBracket, finalOneBracket, loserBracket);
    }

    @Override
    public List<Game> getOngoingGames() {
        List<Game> toReturn = new ArrayList<Game>();
        toReturn.addAll(winnerBracket.findOngoingGames());
        toReturn.addAll(loserBracket.findOngoingGames());
        if (finalOneBracket.getHead().getGame().isInGameState(GameState.Ongoing)) {
            toReturn.add(finalOneBracket.getHead().getGame());
        }
        if (finalTwoBracket.getHead().getGame().isInGameState(GameState.Ongoing)) {
            toReturn.add(finalTwoBracket.getHead().getGame());
        }

        return toReturn;
    }

    @Override
    public void startGame(GameCode gameCode, FoosballTable freeTable) {
        Game gameToBeStarted = findGame(gameCode);
        gameToBeStarted.setTable(freeTable);
        gameToBeStarted.startGame();
    }

    @Override
    public void reportWinner(GameCode gameCode, Team winner) {
        if (winnerBracket.contains(gameCode)) {
            winnerBracket.reportWinner(gameCode, winner);

        } else if (loserBracket.contains(gameCode)) {
            loserBracket.reportWinner(gameCode, winner);

        } else if (finalOneBracket.contains(gameCode)) {
            finalOneBracket.reportWinner(winner);

        } else if (finalTwoBracket.contains(gameCode)) {
            finalTwoBracket.reportWinner(winner);

        } else {

            throw new RuntimeException("Unknown code " + gameCode);
        }
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

    private void processByes(WinnerBracket bracket) {
        bracket.getHead().processByes();
    }

    @Override
    public boolean anyGameLeft() {
        return !getOngoingGames().isEmpty() || !getWaitingGames().isEmpty();
    }

    @Override
    public List<Game> getWaitingGames() {
        List<Game> toReturn = new ArrayList<Game>();
        toReturn.addAll(loserBracket.getHead().findWaitingGames());
        toReturn.addAll(winnerBracket.getHead().findWaitingGames());

        if (finalOneBracket.getHead().getGame().isWaiting()) {
            toReturn.add(finalOneBracket.getHead().getGame());
        }
        if (finalTwoBracket.getHead().getGame().isWaiting()) {
            toReturn.add(finalTwoBracket.getHead().getGame());
        }
        return toReturn;
    }

    @Override
    public FullGamesReport getGamesReports() {
        return gamesReportFactory.create(this);
    }

    public Game findGame(GameCode gameCode) {
        return findBracket(gameCode).getGame();
    }

    private Bracket findBracket(GameCode gameCode) {
        if (loserBracket.contains(gameCode)) {
            return loserBracket.findBracket(gameCode);

        } else if (winnerBracket.contains(gameCode)) {
            return winnerBracket.findBracket(gameCode);

        } else if (finalOneBracket.contains(gameCode)) {
            return finalOneBracket.getHead();

        } else if (finalTwoBracket.contains(gameCode)) {
            return finalTwoBracket.getHead();
        }

        throw new RuntimeException("Bracket " + gameCode + " not found");
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public TourneyEvent getEvent() {
        return event;
    }

    public WinnerBracket getWinnerBracket() {
        return winnerBracket;
    }

    public LoserBracket getLoserBracket() {
        return loserBracket;
    }

    public FinalOneBracket getFinalOneBracket() {
        return finalOneBracket;
    }

    public FinalTwoBracket getFinalTwoBracket() {
        return finalTwoBracket;
    }
}
