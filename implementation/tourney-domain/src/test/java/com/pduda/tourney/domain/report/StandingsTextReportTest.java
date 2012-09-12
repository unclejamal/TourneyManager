package com.pduda.tourney.domain.report;

import com.pduda.tourney.domain.Player;
import com.pduda.tourney.domain.Team;
import com.pduda.tourney.domain.TourneyPlayer;
import org.junit.*;
import static org.junit.Assert.*;

public class StandingsTextReportTest {

    public static final String NL = System.getProperty("line.separator");
    private StandingsTextReport cut;
    private Standings standings;

    @Before
    public void setUp() {
        standings = new Standings();
        cut = new StandingsTextReport();

    }

    public void report_simpleSingles() {
        standings.addPlace("1", createSingleTeam(1));
        standings.addPlace("2", createSingleTeam(2));
        standings.addPlace("3", createSingleTeam(3));

        String report = cut.report(standings);

        String expected = "1. 1A (1)" + NL
                + "2. 2A (2)" + NL
                + "3. 3A (3)";
        assertEquals(expected, report);
    }

    @Test
    public void report_simpleDoubles() {
        standings.addPlace("1", createDoubleTeam(1));
        standings.addPlace("2", createDoubleTeam(2));
        standings.addPlace("3", createDoubleTeam(3));

        String report = cut.report(standings);

        String expected = "1. 1A, 1B (1)" + NL
                + "2. 2A, 2B (2)" + NL
                + "3. 3A, 3B (3)";
        assertEquals(expected, report);
    }

    @Test
    public void report_onePlaceManyDoubles() {
        standings.addPlace("1", createDoubleTeam(1));
        standings.addPlace("2", createDoubleTeam(2));
        standings.addPlace("2", createDoubleTeam(4));
        standings.addPlace("4", createDoubleTeam(5));
        standings.addPlace("4", createDoubleTeam(3));

        String report = cut.report(standings);
        System.out.println(report);

        String expected = "1. 1A, 1B (1)" + NL
                + "2. 2A, 2B (2)" + NL
                + "2. 4A, 4B (4)" + NL
                + "4. 3A, 3B (3)" + NL
                + "4. 5A, 5B (5)";
        assertEquals(expected, report);
    }

    public void report_nobody() {
        String report = cut.report(standings);

        String expected = "";
        assertEquals(expected, report);
    }

    private Team createDoubleTeam(int teamId) {
        String teamIdStr = String.valueOf(teamId);

        Team team = new Team(new TourneyPlayer(teamIdStr + "A"), new TourneyPlayer(teamIdStr + "B"));
        team.setSeed(teamId);

        return team;
    }

    private Team createSingleTeam(int teamId) {
        String teamIdStr = String.valueOf(teamId);

        Team team = new Team(new TourneyPlayer(teamIdStr + "A"));
        team.setSeed(teamId);

        return team;
    }
}
