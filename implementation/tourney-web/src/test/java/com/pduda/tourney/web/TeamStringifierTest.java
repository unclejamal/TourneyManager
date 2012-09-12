package com.pduda.tourney.web;

import com.pduda.tourney.domain.Team;
import com.pduda.tourney.domain.TourneyPlayer;
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
        Team team = new Team(new TourneyPlayer("Pawel", "WKPS69"), new TourneyPlayer("Tomek", "WKPS13"));
        assertEquals("Pawel:::WKPS69:::Tomek:::WKPS13", cut.objectToString(team));
    }

    @Test
    public void objectToString_onlyOneMember() {
        Team team = new Team(new TourneyPlayer("Pawel", "WKPS69"));
        assertEquals("Pawel:::WKPS69", cut.objectToString(team));
    }

    @Test
    public void stringToObject_full() {
        Team team = cut.stringToObject("Pawel:::WKPS69:::Tomek:::WKPS13");

        assertEquals("Pawel", team.getMembers().get(0).getFullName());
        assertEquals("WKPS69", team.getMembers().get(0).getCode());
        assertEquals("Tomek", team.getMembers().get(1).getFullName());
        assertEquals("WKPS13", team.getMembers().get(1).getCode());
    }

    @Test
    public void stringToObject_onlyOneMember() {
        Team team = cut.stringToObject("Pawel:::WKPS69");

        assertEquals("Pawel", team.getMembers().get(0).getFullName());
        assertEquals("WKPS69", team.getMembers().get(0).getCode());
        assertEquals(1, team.getMembers().size());
    }
}