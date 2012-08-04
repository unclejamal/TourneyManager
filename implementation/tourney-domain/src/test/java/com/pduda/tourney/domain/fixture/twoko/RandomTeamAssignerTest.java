package com.pduda.tourney.domain.fixture.twoko;

import com.pduda.tourney.domain.Game;
import com.pduda.tourney.domain.GameId;
import com.pduda.tourney.domain.ObjectMother;
import com.pduda.tourney.domain.Team;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(value = Parameterized.class)
public class RandomTeamAssignerTest {

    private RandomTeamAssigner cut;
    private WinnerBracketFactory wbrFactory;
    private final int teamsTotal;

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        Collection<Object[]> testcases = new ArrayList<Object[]>();

//        testcases.add(new Object[]{16});
//        testcases.add(new Object[]{32});
//        testcases.add(new Object[]{64});

        testcases.add(new Object[]{11});

        return testcases;
    }

    public RandomTeamAssignerTest(int teamsTotal) {
        this.teamsTotal = teamsTotal;
    }

    @Before
    public void setUp() {
        cut = new RandomTeamAssigner();
        wbrFactory = new WinnerBracketFactory();
    }

    @Test
    public void assignTeams() {
        Bracket wbr = wbrFactory.createWinnerBracket(teamsTotal);
        List<Team> teams = ObjectMother.createTeams(teamsTotal);

        cut.assignTeams(wbr, teams);

        final int firstRoundLevel = (int) ((int) Math.pow(2, wbr.getBracketOrder()) / 2);
        System.out.println(firstRoundLevel);
        assertEquals("Assigned teams total", teamsTotal, calculateAssignedTeamTotal(wbr, firstRoundLevel));
        assertAllFirstRoundGamesNotEmpty(wbr, firstRoundLevel);
        System.out.println(wbr.findBracket(new GameId(NumberedWbrFactory.PREFIX, firstRoundLevel, 1)).getGame());
        System.out.println(wbr.findBracket(new GameId(NumberedWbrFactory.PREFIX, firstRoundLevel, 2)).getGame());
        System.out.println(wbr.findBracket(new GameId(NumberedWbrFactory.PREFIX, firstRoundLevel, 3)).getGame());
        System.out.println(wbr.findBracket(new GameId(NumberedWbrFactory.PREFIX, firstRoundLevel, 4)).getGame());
        System.out.println(wbr.findBracket(new GameId(NumberedWbrFactory.PREFIX, firstRoundLevel, 5)).getGame());
        System.out.println(wbr.findBracket(new GameId(NumberedWbrFactory.PREFIX, firstRoundLevel, 6)).getGame());
        System.out.println(wbr.findBracket(new GameId(NumberedWbrFactory.PREFIX, firstRoundLevel, 7)).getGame());
        System.out.println(wbr.findBracket(new GameId(NumberedWbrFactory.PREFIX, firstRoundLevel, 8)).getGame());
    }

    private int calculateAssignedTeamTotal(Bracket wbr, int firstRoundLevel) {
        Set<Team> assignedTeams = new HashSet<Team>();
        for (int i = 1; i < firstRoundLevel + 1; i++) {
            Game game = wbr.findBracket(new GameId(NumberedWbrFactory.PREFIX, firstRoundLevel, i)).getGame();
            if (game.getTeamHome() != null) {
                assignedTeams.add(game.getTeamHome());
            }
            if (game.getTeamAway() != null) {
                assignedTeams.add(game.getTeamAway());
            }
        }

        return assignedTeams.size();
    }

    private void assertAllFirstRoundGamesNotEmpty(Bracket wbr, int firstRoundLevel) {
        boolean foundOrphan = false;
        for (int i = 1; i < firstRoundLevel + 1; i++) {
            Game game = wbr.findBracket(new GameId(NumberedWbrFactory.PREFIX, firstRoundLevel, i)).getGame();
            if (game.isOrphan()) {
                foundOrphan = true;
                break;
            }
        }

        assertFalse("No orphan games should be found", foundOrphan);
    }
}