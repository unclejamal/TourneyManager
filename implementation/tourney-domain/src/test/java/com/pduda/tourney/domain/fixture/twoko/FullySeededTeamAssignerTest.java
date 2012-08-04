package com.pduda.tourney.domain.fixture.twoko;

import com.pduda.tourney.domain.GameId;
import com.pduda.tourney.domain.ObjectMother;
import com.pduda.tourney.domain.Team;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.Ignore;
import static com.pduda.tourney.domain.TourneyAssert.*;

public class FullySeededTeamAssignerTest {

    private FullySeededTeamAssigner cut;
    private WinnerBracketFactory wbrFactory;

    @Before
    public void setUp() {
        cut = new FullySeededTeamAssigner();
        wbrFactory = new WinnerBracketFactory();
    }

    @Test
    public void assignTeams_8() {
        Bracket wbr = wbrFactory.createWinnerBracket(8);
        List<Team> teams = ObjectMother.createTeams(8);

        cut.assignTeams(wbr, teams);
        assertGame(1, 8, wbr.findBracket(new GameId(NumberedWbrFactory.PREFIX, 4, 1)).getGame());
        assertGame(5, 4, wbr.findBracket(new GameId(NumberedWbrFactory.PREFIX, 4, 2)).getGame());
        assertGame(3, 6, wbr.findBracket(new GameId(NumberedWbrFactory.PREFIX, 4, 3)).getGame());
        assertGame(7, 2, wbr.findBracket(new GameId(NumberedWbrFactory.PREFIX, 4, 4)).getGame());
    }

    @Test
    public void assignTeams_16() {
        Bracket wbr = wbrFactory.createWinnerBracket(16);
        List<Team> teams = ObjectMother.createTeams(16);

        cut.assignTeams(wbr, teams);
        assertGame(1, 16, wbr.findBracket(new GameId(NumberedWbrFactory.PREFIX, 8, 1)).getGame());
        assertGame(9, 8, wbr.findBracket(new GameId(NumberedWbrFactory.PREFIX, 8, 2)).getGame());
        assertGame(5, 12, wbr.findBracket(new GameId(NumberedWbrFactory.PREFIX, 8, 3)).getGame());
        assertGame(13, 4, wbr.findBracket(new GameId(NumberedWbrFactory.PREFIX, 8, 4)).getGame());
        assertGame(3, 14, wbr.findBracket(new GameId(NumberedWbrFactory.PREFIX, 8, 5)).getGame());
        assertGame(11, 6, wbr.findBracket(new GameId(NumberedWbrFactory.PREFIX, 8, 6)).getGame());
        assertGame(7, 10, wbr.findBracket(new GameId(NumberedWbrFactory.PREFIX, 8, 7)).getGame());
        assertGame(15, 2, wbr.findBracket(new GameId(NumberedWbrFactory.PREFIX, 8, 8)).getGame());
    }

    @Test
    public void assignTeams_32() {
        Bracket wbr = wbrFactory.createWinnerBracket(32);
        List<Team> teams = ObjectMother.createTeams(32);

        cut.assignTeams(wbr, teams);
        assertGame(1, 32, wbr.findBracket(new GameId(NumberedWbrFactory.PREFIX, 16, 1)).getGame());
        assertGame(17, 16, wbr.findBracket(new GameId(NumberedWbrFactory.PREFIX, 16, 2)).getGame());
        assertGame(9, 24, wbr.findBracket(new GameId(NumberedWbrFactory.PREFIX, 16, 3)).getGame());
        assertGame(25, 8, wbr.findBracket(new GameId(NumberedWbrFactory.PREFIX, 16, 4)).getGame());
        assertGame(5, 28, wbr.findBracket(new GameId(NumberedWbrFactory.PREFIX, 16, 5)).getGame());
        assertGame(21, 12, wbr.findBracket(new GameId(NumberedWbrFactory.PREFIX, 16, 6)).getGame());
        assertGame(13, 20, wbr.findBracket(new GameId(NumberedWbrFactory.PREFIX, 16, 7)).getGame());
        assertGame(29, 4, wbr.findBracket(new GameId(NumberedWbrFactory.PREFIX, 16, 8)).getGame());
        assertGame(3, 30, wbr.findBracket(new GameId(NumberedWbrFactory.PREFIX, 16, 9)).getGame());
        assertGame(19, 14, wbr.findBracket(new GameId(NumberedWbrFactory.PREFIX, 16, 10)).getGame());
        assertGame(11, 22, wbr.findBracket(new GameId(NumberedWbrFactory.PREFIX, 16, 11)).getGame());
        assertGame(27, 6, wbr.findBracket(new GameId(NumberedWbrFactory.PREFIX, 16, 12)).getGame());
        assertGame(7, 26, wbr.findBracket(new GameId(NumberedWbrFactory.PREFIX, 16, 13)).getGame());
        assertGame(23, 10, wbr.findBracket(new GameId(NumberedWbrFactory.PREFIX, 16, 14)).getGame());
        assertGame(15, 18, wbr.findBracket(new GameId(NumberedWbrFactory.PREFIX, 16, 15)).getGame());
        assertGame(31, 2, wbr.findBracket(new GameId(NumberedWbrFactory.PREFIX, 16, 16)).getGame());
    }
}