package com.pduda.tourney.domain.fixture.twoko;

import com.pduda.tourney.domain.Player;
import com.pduda.tourney.domain.Team;
import com.pduda.tourney.domain.TourneyPlayer;
import org.junit.*;
import static org.junit.Assert.*;

public class BracketTest {

    private Bracket cut;

    @Before
    public void setUp() {
        cut = new Bracket(Fixture2KO.FIN1, 1, 1);
    }

    @Test
    public void onlyFinal() {

        Team homeTeam = new Team(new TourneyPlayer("home"));
        Team awayTeam = new Team(new TourneyPlayer("away"));

        cut.getGame().setTeamHome(homeTeam);
        cut.getGame().setTeamAway(awayTeam);

        assertEquals(1, cut.findWaitingGames().size());
        assertEquals(homeTeam, cut.findWaitingGames().get(0).getTeamHome());
        assertEquals(awayTeam, cut.findWaitingGames().get(0).getTeamAway());
    }

    @Test
    public void threeTeams() {
        Team homeTeam = new Team(new TourneyPlayer("home"));
        Team awayHomeTeam = new Team(new TourneyPlayer("awayhome"));
        Team awayAwayTeam = new Team(new TourneyPlayer("awayaway"));

        Bracket homeBracket = new Bracket(NumberedWbrFactory.PREFIX, 2, 1);
        homeBracket.getGame().setTeamHome(homeTeam);
        cut.setHomeBracket(homeBracket);

        Bracket awayBracket = new Bracket(NumberedWbrFactory.PREFIX, 2, 2);
        awayBracket.getGame().setTeamHome(awayHomeTeam);
        awayBracket.getGame().setTeamAway(awayAwayTeam);
        cut.setAwayBracket(awayBracket);

        assertEquals(1, cut.findWaitingGames().size());
        assertEquals(awayHomeTeam, cut.findWaitingGames().get(0).getTeamHome());
        assertEquals(awayAwayTeam, cut.findWaitingGames().get(0).getTeamAway());
    }
}