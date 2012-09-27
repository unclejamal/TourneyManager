package com.pduda.tourney.domain;

import com.pduda.tourney.domain.fixture.twoko.FinalOneBracket;
import com.pduda.tourney.domain.fixture.twoko.FinalTwoBracket;
import com.pduda.tourney.domain.fixture.twoko.NumberedLbrFactory;
import com.pduda.tourney.domain.fixture.twoko.NumberedWbrFactory;
import org.junit.*;
import static org.junit.Assert.*;

public class FourTeamsEventTest {

    public static final String TOURNEY_NAME = "Liga Weekendowa Wroclaw";
    public static FoosballTable upperTable;
    public static Team teamQuaquaraqua;
    public static Team teamTosty;
    public static Team teamMafia;
    public static Team teamMaly;
    private Tourney tourney = new Tourney(TOURNEY_NAME);
    private TourneyEvent event;

    @Before
    public void setUp() {
        upperTable = new FoosballTable("Upper");
        teamMafia = new Team(new EventPlayer("Casd"), new EventPlayer("Krzysiek"));
        teamMafia.setSeed(1);
        teamQuaquaraqua = new Team(new EventPlayer("Czarek"), new EventPlayer("Darek"));
        teamQuaquaraqua.setSeed(2);
        teamTosty = new Team(new EventPlayer("Kacper"), new EventPlayer("Seba"));
        teamTosty.setSeed(3);
        teamMaly = new Team(new EventPlayer("Gracjan"), new EventPlayer("Patol"));
        teamMaly.setSeed(4);

        event = new TourneyEvent(tourney, EventCategory.OD);
        event.addTeam(teamMafia);
        event.addTeam(teamQuaquaraqua);
        event.addTeam(teamTosty);
        event.addTeam(teamMaly);

        tourney.addTable(upperTable);
        tourney.addEvent(event);
        
        event.startEvent();
    }

    /**
     * W2-2: QQQ - Tosty -> QQQ W2-1: Mafia gets a bye WFIN: QQQ -> Mafia -> QQQ
     * L3: Mafia - Tosty -> Tosty FIN: QQQ - Tosty -> QQQ
     */
    @Test
    public void qqqTostyMafia() {

        event.startGame(tourney.getWaitingGames().get(0).getGameCode());
        assertEquals(1, tourney.getOngoingGames().size());
        assertEquals(new GameCode(NumberedWbrFactory.PREFIX, 2, 1), tourney.getOngoingGames().get(0).getGameCode());
        assertEquals(upperTable, tourney.getOngoingGames().get(0).getTable());
        assertEquals(teamMafia, tourney.getOngoingGames().get(0).getTeamHome());
        assertEquals(teamMaly, tourney.getOngoingGames().get(0).getTeamAway());
        event.reportWinner(tourney.getOngoingGames().get(0).getGameCode(), teamMafia.getTeamCode());

        event.startGame(tourney.getWaitingGames().get(0).getGameCode());
        assertEquals(1, tourney.getOngoingGames().size());
        assertEquals(new GameCode(NumberedWbrFactory.PREFIX, 2, 2), tourney.getOngoingGames().get(0).getGameCode());
        assertEquals(upperTable, tourney.getOngoingGames().get(0).getTable());
        assertEquals(teamTosty, tourney.getOngoingGames().get(0).getTeamHome());
        assertEquals(teamQuaquaraqua, tourney.getOngoingGames().get(0).getTeamAway());
        event.reportWinner(tourney.getOngoingGames().get(0).getGameCode(), teamTosty.getTeamCode());

        event.startGame(tourney.getWaitingGames().get(0).getGameCode());
        assertEquals(1, tourney.getOngoingGames().size());
        assertEquals(new GameCode(NumberedLbrFactory.PREFIX, 4, 1), tourney.getOngoingGames().get(0).getGameCode());
        assertEquals(upperTable, tourney.getOngoingGames().get(0).getTable());
        assertEquals(teamMaly, tourney.getOngoingGames().get(0).getTeamHome());
        assertEquals(teamQuaquaraqua, tourney.getOngoingGames().get(0).getTeamAway());
        event.reportWinner(tourney.getOngoingGames().get(0).getGameCode(), teamQuaquaraqua.getTeamCode());

        event.getStandings();

        event.startGame(tourney.getWaitingGames().get(0).getGameCode());
        assertEquals(1, tourney.getOngoingGames().size());
        assertEquals(new GameCode(NumberedWbrFactory.PREFIX, 1, 1), tourney.getOngoingGames().get(0).getGameCode());
        assertEquals(upperTable, tourney.getOngoingGames().get(0).getTable());
        assertEquals(teamMafia, tourney.getOngoingGames().get(0).getTeamHome());
        assertEquals(teamTosty, tourney.getOngoingGames().get(0).getTeamAway());
        event.reportWinner(tourney.getOngoingGames().get(0).getGameCode(), teamMafia.getTeamCode());

        event.startGame(tourney.getWaitingGames().get(0).getGameCode());
        assertEquals(1, tourney.getOngoingGames().size());
        assertEquals(new GameCode(NumberedLbrFactory.PREFIX, 3, 1), tourney.getOngoingGames().get(0).getGameCode());
        assertEquals(upperTable, tourney.getOngoingGames().get(0).getTable());
        assertEquals(teamTosty, tourney.getOngoingGames().get(0).getTeamHome());
        assertEquals(teamQuaquaraqua, tourney.getOngoingGames().get(0).getTeamAway());
        event.reportWinner(tourney.getOngoingGames().get(0).getGameCode(), teamQuaquaraqua.getTeamCode());

        event.startGame(tourney.getWaitingGames().get(0).getGameCode());
        assertEquals(1, tourney.getOngoingGames().size());
        assertEquals(new GameCode(FinalOneBracket.FIN1, 1, 1), tourney.getOngoingGames().get(0).getGameCode());
        assertEquals(upperTable, tourney.getOngoingGames().get(0).getTable());
        assertEquals(teamMafia, tourney.getOngoingGames().get(0).getTeamHome());
        assertEquals(teamQuaquaraqua, tourney.getOngoingGames().get(0).getTeamAway());
        event.reportWinner(tourney.getOngoingGames().get(0).getGameCode(), teamQuaquaraqua.getTeamCode());

        event.startGame(tourney.getWaitingGames().get(0).getGameCode());
        assertEquals(1, tourney.getOngoingGames().size());
        assertEquals(new GameCode(FinalTwoBracket.FIN2, 1, 1), tourney.getOngoingGames().get(0).getGameCode());
        assertEquals(upperTable, tourney.getOngoingGames().get(0).getTable());
        assertEquals(teamMafia, tourney.getOngoingGames().get(0).getTeamHome());
        assertEquals(teamQuaquaraqua, tourney.getOngoingGames().get(0).getTeamAway());
        event.reportWinner(tourney.getOngoingGames().get(0).getGameCode(), teamQuaquaraqua.getTeamCode());

        assertEquals(TOURNEY_NAME, tourney.getName());
        assertTrue(event.getStartDate() != null);
        assertTrue(event.getEndDate() != null);
        assertEquals(4, event.getStandings().size());
        assertTrue(event.getStandings().getByPlace("1").contains(teamQuaquaraqua));
        assertTrue(event.getStandings().getByPlace("2").contains(teamMafia));
        assertTrue(event.getStandings().getByPlace("3").contains(teamTosty));
        assertTrue(event.getStandings().getByPlace("4").contains(teamMaly));
    }
}