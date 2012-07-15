package com.pduda.tourney.domain;

import org.junit.Test;
import static org.junit.Assert.*;

public class PlayerTest {

    private Player cut;

    @Test
    public void getShortName_onlyLastName() {
        cut = new Player("Duda");
        assertEquals("Duda", cut.getShortName());
    }

    @Test
    public void getShortName_firstAndLastName() {
        cut = new Player("Pawel Duda");
        assertEquals("P. Duda", cut.getShortName());
    }

    @Test
    public void getShortName_complexFirstName() {
        cut = new Player("Pawel Adam Duda");
        assertEquals("P. A. Duda", cut.getShortName());
    }
}