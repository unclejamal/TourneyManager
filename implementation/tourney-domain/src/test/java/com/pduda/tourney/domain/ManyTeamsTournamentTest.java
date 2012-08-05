package com.pduda.tourney.domain;

import com.pduda.tourney.domain.util.MyMath;
import static com.pduda.tourney.domain.TourneyAssert.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import org.junit.*;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(value = Parameterized.class)
public class ManyTeamsTournamentTest {

    public static final String TOURNEY_NAME = "Liga Weekendowa Wroclaw";
    private Tourney cut = new Tourney();
    public static Table upperTable = new Table("Upper");
    private final int teamsTotal;
    private final int expWaitingGames;
    private final int expGamesTotal;

    @Parameters
    public static Collection<Object[]> data() {
        Collection<Object[]> testcases = new ArrayList<Object[]>();

//        for (int iTeamsTotal = 10; iTeamsTotal < 11; iTeamsTotal++) {
        for (int iTeamsTotal = 6; iTeamsTotal < 33; iTeamsTotal++) {
            int limitUpper = (int) Math.pow(2, Math.ceil(MyMath.log2(iTeamsTotal)));
            int limitLower = (int) Math.pow(2, Math.floor(MyMath.log2(iTeamsTotal)));
            int iExpWaitingGamesFromByes = (limitUpper + limitLower) / 2 - iTeamsTotal;
            int iExpWaitingGamesFirstRound = (int) ((iTeamsTotal - (limitUpper - iTeamsTotal)) / 2);
            final int iExpWaitingGames = iExpWaitingGamesFirstRound + Math.max(iExpWaitingGamesFromByes, 0);
//            System.out.println("[" + iTeamsTotal + "]" + limitLower + ", " + limitUpper + ", " + iExpWaitingGamesFromByes + " || " + iExpWaitingGames);
            final int iExpGamesTotal = iTeamsTotal * 2 - 2;
            testcases.add(new Object[]{iTeamsTotal, iExpWaitingGames, iExpGamesTotal});
        }

        return testcases;
    }

    @Before
    public void setUp() {
        cut.setName(TOURNEY_NAME);
        cut.addTable(upperTable);
    }

    public ManyTeamsTournamentTest(int teamsTotal, int expWaitingGames, int expGamesTotal) {
        this.teamsTotal = teamsTotal;
        this.expWaitingGames = expWaitingGames;
        this.expGamesTotal = expGamesTotal;
    }

    @Test
    public void manyTeams_betterWins() {
        addTeams(teamsTotal);
        cut.startTournament();
        assertEquals("[" + teamsTotal + "] wait", expWaitingGames, cut.getWaitingGames().size());
        int gamesTotal = playTournament(WhoWins.BETTER_WINS);
        assertEquals("[" + teamsTotal + "] total", expGamesTotal, gamesTotal);
        List<Game> l6 = cut.getGamesReports().getPartialReport("lbr").getRound("L6").getGames();
        assertEquals("[" + teamsTotal + "] lbr6 size", 2, l6.size());

//        System.out.println("L6-1: " + l6.get(0).getTeamHome().getSeed() + " vs " +l6.get(0).getTeamAway().getSeed());
//        System.out.println("L6-2: " + l6.get(1).getTeamHome().getSeed() + " vs " +l6.get(1).getTeamAway().getSeed());
        // it is now 3vs6, 4vs5
        assertGamesContain("[" + teamsTotal + "] lbr6", 3, 5, l6);
        assertGamesContain("[" + teamsTotal + "] lbr6", 4, 6, l6);
    }

    @Test
    @Ignore
    public void manyTeams_awayWins() {
        addTeams(teamsTotal);
        cut.startTournament();
        assertEquals("[" + teamsTotal + "] wait", expWaitingGames, cut.getWaitingGames().size());
        int gamesTotal = playTournament(WhoWins.AWAY);
        assertEquals("[" + teamsTotal + "] total", expGamesTotal + 1, gamesTotal);
    }

    @Test
    @Ignore
    public void manyTeams_homeAwayWins() {
        addTeams(teamsTotal);
        cut.startTournament();
        assertEquals("[" + teamsTotal + "] wait", expWaitingGames, cut.getWaitingGames().size());
        int gamesTotal = playTournament(WhoWins.HOMEAWAY);
        assertEquals("[" + teamsTotal + "] total", expGamesTotal + 1, gamesTotal);
    }

    @Test
    @Ignore
    public void manyTeams_awayHomeWins() {
        addTeams(teamsTotal);
        cut.startTournament();
        assertEquals("[" + teamsTotal + "] wait", expWaitingGames, cut.getWaitingGames().size());
        int gamesTotal = playTournament(WhoWins.AWAYHOME);
        assertEquals("[" + teamsTotal + "] total", expGamesTotal + 1, gamesTotal);
    }

    private void addTeams(int teamsTotal) throws NumberFormatException {
        Set<Team> teams = ObjectMother.createSeededTeams(teamsTotal);
        for (Team team : teams) {
            cut.addTeam(team);
        }
    }

    private int playTournament(WhoWins whoWins) {
        int i = 0;
        while (cut.getEndDate() == null) {
            Game game = cut.getWaitingGames().get(0);
            cut.startGame(game.getId());

            if (WhoWins.BETTER_WINS.equals(whoWins)) {
                if (game.getTeamHome().getSeed() < game.getTeamAway().getSeed()) {
                    cut.reportWinner(game.getId(), game.getTeamHome().getTeamCode());
                } else {
                    cut.reportWinner(game.getId(), game.getTeamAway().getTeamCode());
                }


            } else if (WhoWins.HOME.equals(whoWins)) {
                cut.reportWinner(game.getId(), game.getTeamHome().getTeamCode());

            } else if (WhoWins.AWAY.equals(whoWins)) {
                cut.reportWinner(game.getId(), game.getTeamAway().getTeamCode());

            } else if (WhoWins.HOMEAWAY.equals(whoWins)) {
                if (i % 2 == 0) {
                    cut.reportWinner(game.getId(), game.getTeamHome().getTeamCode());
                } else {
                    cut.reportWinner(game.getId(), game.getTeamAway().getTeamCode());
                }

            } else if (WhoWins.AWAYHOME.equals(whoWins)) {
                if (i % 2 == 1) {
                    cut.reportWinner(game.getId(), game.getTeamAway().getTeamCode());
                } else {
                    cut.reportWinner(game.getId(), game.getTeamHome().getTeamCode());
                }
            }

            i++;
        }

        return i;
    }

    public enum WhoWins {

        HOME, AWAY, HOMEAWAY, AWAYHOME, BETTER_WINS;
    }
}