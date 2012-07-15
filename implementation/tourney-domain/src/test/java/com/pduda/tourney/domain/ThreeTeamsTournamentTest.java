package com.pduda.tourney.domain;

import com.pduda.tourney.domain.fixture.twoko.Fixture2KO;
import com.pduda.tourney.domain.fixture.twoko.NumberedLbrFactory;
import com.pduda.tourney.domain.fixture.twoko.NumberedWbrFactory;
import org.junit.*;
import static org.junit.Assert.*;

public class ThreeTeamsTournamentTest {

    public static final String TOURNEY_NAME = "Liga Weekendowa Wroclaw";
    public static Table upperTable;
    public static Team teamQuaquaraqua;
    public static Team teamTosty;
    public static Team teamMafia;
    private Tournament cut = new Tournament();

    @Before
    public void setUp() {
        upperTable = new Table("Upper");
        teamMafia = new Team(new Player("Casd"), new Player("Krzysiek"));
        teamMafia.setSeed(1);
        teamQuaquaraqua = new Team(new Player("Czarek"), new Player("Darek"));
        teamQuaquaraqua.setSeed(2);
        teamTosty = new Team(new Player("Kacper"), new Player("Seba"));
        teamTosty.setSeed(3);

        cut.setName(TOURNEY_NAME);
        cut.addTable(upperTable);
        cut.addTeam(teamQuaquaraqua);
        cut.addTeam(teamTosty);
        cut.addTeam(teamMafia);

        cut.startTournament();
    }

    /**
     * W2-2: QQQ - Tosty -> QQQ W2-1: Mafia gets a bye WFIN: QQQ -> Mafia -> QQQ
     * L3: Mafia - Tosty -> Tosty FIN: QQQ - Tosty -> QQQ
     */
    @Test
    public void qqqTostyMafia() {

        cut.startGame(cut.getWaitingGames().get(0).getId());
        assertEquals(1, cut.getOngoingGames().size());
        assertEquals(new GameId(NumberedWbrFactory.PREFIX, 2, 2), cut.getOngoingGames().get(0).getId());
        assertEquals(upperTable, cut.getOngoingGames().get(0).getTable());
        assertEquals(teamTosty, cut.getOngoingGames().get(0).getTeamHome());
        assertEquals(teamQuaquaraqua, cut.getOngoingGames().get(0).getTeamAway());
        cut.reportWinner(cut.getOngoingGames().get(0).getId(), teamQuaquaraqua.getSeed());

        cut.startGame(cut.getWaitingGames().get(0).getId());
        assertEquals(1, cut.getOngoingGames().size());
        assertEquals(new GameId(NumberedWbrFactory.PREFIX, 1, 1), cut.getOngoingGames().get(0).getId());
        assertEquals(upperTable, cut.getOngoingGames().get(0).getTable());
        assertEquals(teamMafia, cut.getOngoingGames().get(0).getTeamHome());
        assertEquals(teamQuaquaraqua, cut.getOngoingGames().get(0).getTeamAway());
        cut.reportWinner(cut.getOngoingGames().get(0).getId(), teamQuaquaraqua.getSeed());

        cut.startGame(cut.getWaitingGames().get(0).getId());
        assertEquals(1, cut.getOngoingGames().size());
        assertEquals(new GameId(NumberedLbrFactory.PREFIX, 3, 1), cut.getOngoingGames().get(0).getId());
        assertEquals(upperTable, cut.getOngoingGames().get(0).getTable());
        assertEquals(teamMafia, cut.getOngoingGames().get(0).getTeamHome());
        assertEquals(teamTosty, cut.getOngoingGames().get(0).getTeamAway());
        cut.reportWinner(cut.getOngoingGames().get(0).getId(), teamTosty.getSeed());

        cut.startGame(cut.getWaitingGames().get(0).getId());
        assertEquals(1, cut.getOngoingGames().size());
        assertEquals(new GameId(Fixture2KO.FIN1, 1, 1), cut.getOngoingGames().get(0).getId());
        assertEquals(upperTable, cut.getOngoingGames().get(0).getTable());
        assertEquals(teamQuaquaraqua, cut.getOngoingGames().get(0).getTeamHome());
        assertEquals(teamTosty, cut.getOngoingGames().get(0).getTeamAway());
        cut.reportWinner(cut.getOngoingGames().get(0).getId(), teamTosty.getSeed());

        cut.startGame(cut.getWaitingGames().get(0).getId());
        assertEquals(1, cut.getOngoingGames().size());
        assertEquals(new GameId(Fixture2KO.FIN2, 1, 1), cut.getOngoingGames().get(0).getId());
        assertEquals(upperTable, cut.getOngoingGames().get(0).getTable());
        assertEquals(teamQuaquaraqua, cut.getOngoingGames().get(0).getTeamHome());
        assertEquals(teamTosty, cut.getOngoingGames().get(0).getTeamAway());
        cut.reportWinner(cut.getOngoingGames().get(0).getId(), teamQuaquaraqua.getSeed());

        assertEquals(TOURNEY_NAME, cut.getName());
        assertTrue(cut.getStartDate() != null);
        assertTrue(cut.getEndDate() != null);
        assertEquals(3, cut.getStandings().size());
        assertTrue(cut.getStandings().getByPlace("1").contains(teamQuaquaraqua));
        assertTrue(cut.getStandings().getByPlace("2").contains(teamTosty));
        assertTrue(cut.getStandings().getByPlace("3").contains(teamMafia));
    }
}