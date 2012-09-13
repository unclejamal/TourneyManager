package com.pduda.tourney.domain;

import org.junit.Test;
import static org.junit.Assert.*;

public class PlayerTest {

    private RankingPlayer cut;

    @Test
    public void getShortName_onlyLastName() {
        cut = new RankingPlayer("Duda");
        assertEquals("Duda", cut.getShortName());
    }

    @Test
    public void getShortName_firstAndLastName() {
        cut = new RankingPlayer("Pawel Duda");
        assertEquals("P. Duda", cut.getShortName());
    }

    @Test
    public void getShortName_complexFirstName() {
        cut = new RankingPlayer("Pawel Adam Duda");
        assertEquals("P. A. Duda", cut.getShortName());
    }
}