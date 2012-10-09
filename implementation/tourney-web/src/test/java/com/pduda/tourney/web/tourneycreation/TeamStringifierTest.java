package com.pduda.tourney.web.tourneycreation;

import com.pduda.tourney.domain.EventPlayer;
import com.pduda.tourney.domain.Team;
import org.junit.*;
import static org.junit.Assert.*;

public class TeamStringifierTest {

    private TeamStringifier cut;

    @Before
    public void setUp() {
        cut = new TeamStringifier();
    }

    @Test
    public void objectToString_full() {
        Team team = new Team(new EventPlayer("Pawel", "WKPS69"), new EventPlayer("Tomek", "WKPS13"));
        assertEquals("Pawel:::WKPS69:::Tomek:::WKPS13", cut.objectToString(team));
    }

    @Test
    public void objectToString_onlyOneMember() {
        Team team = new Team(new EventPlayer("Pawel", "WKPS69"));
        assertEquals("Pawel:::WKPS69", cut.objectToString(team));
    }

    @Test
    public void stringToObject_full() {
        Team team = cut.stringToObject("Pawel:::WKPS69:::Tomek:::WKPS13");

        assertEquals("Pawel", team.getEventPlayerOne().getFullName());
        assertEquals("WKPS69", team.getEventPlayerOne().getCode());
        assertEquals("Tomek", team.getEventPlayerTwo().getFullName());
        assertEquals("WKPS13", team.getEventPlayerTwo().getCode());
    }

    @Test
    public void stringToObject_onlyOneMember() {
        Team team = cut.stringToObject("Pawel:::WKPS69");

        assertEquals("Pawel", team.getEventPlayerOne().getFullName());
        assertEquals("WKPS69", team.getEventPlayerOne().getCode());
        assertEquals(1, team.getSize());
    }
}