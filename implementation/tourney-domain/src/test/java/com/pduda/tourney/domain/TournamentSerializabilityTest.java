package com.pduda.tourney.domain;

import java.io.Serializable;
import junitx.extensions.SerializabilityTestCase;

public class TournamentSerializabilityTest extends SerializabilityTestCase {

    public TournamentSerializabilityTest(String name) {
        super(name);
    }

    @Override
    protected void checkThawedObject(Serializable expected, Serializable actual) throws Exception {
        Tourney expectedTourney = (Tourney) expected;
        Tourney actualTourney = (Tourney) actual;
        assertEquals(expectedTourney.getId(), actualTourney.getId());
        assertEquals(expectedTourney.getName(), actualTourney.getName());
        assertEquals(expectedTourney.getWaitingGames().size(), actualTourney.getWaitingGames().size());
        assertEquals(expectedTourney.getOngoingGames().size(), actualTourney.getOngoingGames().size());
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    @Override
    protected Serializable createInstance() throws Exception {
        Tourney tourney = new Tourney(1, TourneyCategory.OD, "Liga Weekendowa");
        Team teama = new Team(new Player("a"));
        teama.setSeed(1);
        tourney.addTeam(teama);
        Team teamb = new Team(new Player("b"));
        teamb.setSeed(2);
        tourney.addTeam(teamb);
        Team teamc = new Team(new Player("c"));
        teamc.setSeed(3);
        tourney.addTeam(teamc);

        tourney.addTable(new FoosballTable("upper"));

        tourney.startTourney();

        return tourney;
    }
}
