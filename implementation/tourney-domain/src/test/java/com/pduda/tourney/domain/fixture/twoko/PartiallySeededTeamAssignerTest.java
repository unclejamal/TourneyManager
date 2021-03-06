package com.pduda.tourney.domain.fixture.twoko;

import static com.pduda.tourney.domain.EventAssert.*;
import com.pduda.tourney.domain.Game;
import com.pduda.tourney.domain.GameCode;
import com.pduda.tourney.domain.Team;
import com.pduda.tourney.domain.TourneyObjectMother;
import java.util.HashSet;
import java.util.Set;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class PartiallySeededTeamAssignerTest {

    private PartiallySeededTeamAssigner cut;
    private WinnerBracketFactory wbrFactory;

    @Before
    public void setUp() {
        cut = new PartiallySeededTeamAssigner();
        wbrFactory = new WinnerBracketFactory(null);
    }

    @Test
    public void assignSeededTeams_8() {
        WinnerBracket winnerBracket = wbrFactory.createWinnerBracket(8, null, null);
        Set<Team> teams = TourneyObjectMother.createSeededTeams(8);
        Bracket wbr = winnerBracket.getHead();

        cut.assignTeams(wbr, teams);
        assertGame(1, 8, wbr.findBracket(new GameCode(NumberedWbrFactory.PREFIX, 4, 1)).getGame());
        assertGame(5, 4, wbr.findBracket(new GameCode(NumberedWbrFactory.PREFIX, 4, 2)).getGame());
        assertGame(3, 6, wbr.findBracket(new GameCode(NumberedWbrFactory.PREFIX, 4, 3)).getGame());
        assertGame(7, 2, wbr.findBracket(new GameCode(NumberedWbrFactory.PREFIX, 4, 4)).getGame());
    }

    @Test
    public void assignSeededTeams_32() {
        WinnerBracket winnerBracket = wbrFactory.createWinnerBracket(32, null, null);
        Set<Team> teams = TourneyObjectMother.createSeededTeams(32);
        Bracket wbr = winnerBracket.getHead();

        cut.assignTeams(wbr, teams);
        assertGame(1, 32, wbr.findBracket(new GameCode(NumberedWbrFactory.PREFIX, 16, 1)).getGame());
        assertGame(17, 16, wbr.findBracket(new GameCode(NumberedWbrFactory.PREFIX, 16, 2)).getGame());
        assertGame(9, 24, wbr.findBracket(new GameCode(NumberedWbrFactory.PREFIX, 16, 3)).getGame());
        assertGame(25, 8, wbr.findBracket(new GameCode(NumberedWbrFactory.PREFIX, 16, 4)).getGame());
        assertGame(5, 28, wbr.findBracket(new GameCode(NumberedWbrFactory.PREFIX, 16, 5)).getGame());
        assertGame(21, 12, wbr.findBracket(new GameCode(NumberedWbrFactory.PREFIX, 16, 6)).getGame());
        assertGame(13, 20, wbr.findBracket(new GameCode(NumberedWbrFactory.PREFIX, 16, 7)).getGame());
        assertGame(29, 4, wbr.findBracket(new GameCode(NumberedWbrFactory.PREFIX, 16, 8)).getGame());
        assertGame(3, 30, wbr.findBracket(new GameCode(NumberedWbrFactory.PREFIX, 16, 9)).getGame());
        assertGame(19, 14, wbr.findBracket(new GameCode(NumberedWbrFactory.PREFIX, 16, 10)).getGame());
        assertGame(11, 22, wbr.findBracket(new GameCode(NumberedWbrFactory.PREFIX, 16, 11)).getGame());
        assertGame(27, 6, wbr.findBracket(new GameCode(NumberedWbrFactory.PREFIX, 16, 12)).getGame());
        assertGame(7, 26, wbr.findBracket(new GameCode(NumberedWbrFactory.PREFIX, 16, 13)).getGame());
        assertGame(23, 10, wbr.findBracket(new GameCode(NumberedWbrFactory.PREFIX, 16, 14)).getGame());
        assertGame(15, 18, wbr.findBracket(new GameCode(NumberedWbrFactory.PREFIX, 16, 15)).getGame());
        assertGame(31, 2, wbr.findBracket(new GameCode(NumberedWbrFactory.PREFIX, 16, 16)).getGame());
    }

    @Test
    public void assignUnseededTeams_8() {
        WinnerBracket winnerBracket = wbrFactory.createWinnerBracket(8, null, null);
        Set<Team> teams = TourneyObjectMother.createUnseededTeams(8);
        Bracket wbr = winnerBracket.getHead();

        cut.assignTeams(wbr, teams);

        Set<Team> assignedTeams = new HashSet<Team>();
        for (int i = 1; i < 5; i++) {
            Game game = wbr.findBracket(new GameCode(NumberedWbrFactory.PREFIX, 4, i)).getGame();
            assertFalse(game.isOrphan());
            assignedTeams.add(game.getTeamHome());
            assignedTeams.add(game.getTeamAway());
        }
        assertEquals(8, assignedTeams.size());
    }

    @Test
    public void assignPartiallySeededTeams_8() {
        WinnerBracket winnerBracket = wbrFactory.createWinnerBracket(8, null, null);
        Set<Team> teams = TourneyObjectMother.createUnseededTeams(8);
        makeTwoTeamsSeeded(teams);
        Bracket wbr = winnerBracket.getHead();

        cut.assignTeams(wbr, teams);

        Set<Team> assignedTeams = new HashSet<Team>();
        for (int i = 1; i < 5; i++) {
            Game game = wbr.findBracket(new GameCode(NumberedWbrFactory.PREFIX, 4, i)).getGame();
            assertFalse(game.isOrphan());
            assignedTeams.add(game.getTeamHome());
            assignedTeams.add(game.getTeamAway());
        }
        assertEquals(8, assignedTeams.size());

        assertEquals(1, wbr.findBracket(new GameCode(NumberedWbrFactory.PREFIX, 4, 1)).getGame().getTeamHome().getSeed());
        assertEquals(2, wbr.findBracket(new GameCode(NumberedWbrFactory.PREFIX, 4, 4)).getGame().getTeamAway().getSeed());
    }

    private void makeTwoTeamsSeeded(Set<Team> teams) {
        int i = 1;
        for (Team team : teams) {
            team.setSeed(i);
            if (i++ > 2) {
                break;
            }
        }
    }
}
