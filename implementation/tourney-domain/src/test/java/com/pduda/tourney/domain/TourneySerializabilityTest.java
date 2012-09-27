package com.pduda.tourney.domain;

import java.io.Serializable;
import junitx.extensions.SerializabilityTestCase;

public class TourneySerializabilityTest extends SerializabilityTestCase {

    public TourneySerializabilityTest(String name) {
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
        Tourney tourney = new Tourney("Liga Weekendowa");
        tourney.addTable(new FoosballTable("upper"));

        TourneyEvent event = new TourneyEvent(tourney, EventCategory.OD);
        Team teama = new Team(new EventPlayer("a"));
        teama.setSeed(1);
        event.addTeam(teama);
        Team teamb = new Team(new EventPlayer("b"));
        teamb.setSeed(2);
        event.addTeam(teamb);
        Team teamc = new Team(new EventPlayer("c"));
        teamc.setSeed(3);
        event.addTeam(teamc);
        
        tourney.addEvent(event);


        event.startEvent();

        return tourney;
    }
}
