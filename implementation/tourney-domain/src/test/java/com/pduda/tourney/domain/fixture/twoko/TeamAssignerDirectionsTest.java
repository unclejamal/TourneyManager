package com.pduda.tourney.domain.fixture.twoko;

import com.pduda.tourney.domain.fixture.twoko.TeamAssignerDirections;
import org.junit.*;
import static org.junit.Assert.*;

public class TeamAssignerDirectionsTest {

    private TeamAssignerDirections cut;

    @Before
    public void setUp() {
        cut = new TeamAssignerDirections();
    }

    @Test
    public void testCreateDirections() {
        cut.createDirections(5);
//        assertSeedsDirection(1, true, true, true);
//        assertSeedsDirection(2, false, true, true);
//        assertSeedsDirection(3, false, false, true);
//        assertSeedsDirection(4, true, false, true);
//        assertSeedsDirection(5, true, false, false);
//        assertSeedsDirection(6, false, false, false);
//        assertSeedsDirection(7, false, true, false);
//        assertSeedsDirection(8, true, true, false);

        assertSeedsDirection(1, true, true, true, true);
        assertSeedsDirection(2, false, false, false, false);
        assertSeedsDirection(3, false, true, true, true);
        assertSeedsDirection(4, true, false, false, false);
        assertSeedsDirection(5, true, false, true, true);
        assertSeedsDirection(6, false, true, false, false);
        assertSeedsDirection(7, false, false, true, true);
        assertSeedsDirection(8, true, true, false, false);
        assertSeedsDirection(9, true, true, false, true);
        assertSeedsDirection(10, false, false, true, false);
        assertSeedsDirection(11, false, true, false, true);
        assertSeedsDirection(12, true, false, true, false);
        assertSeedsDirection(13, true, false, false, true);
        assertSeedsDirection(14, false, true, true, false);
        assertSeedsDirection(15, false, false, false, true);
        assertSeedsDirection(16, true, true, true, false);
    }

    private void assertSeedsDirection(int seed, Boolean... ups) {
        int stageCount = 1;
        for (Boolean isUp : ups) {
            assertTrue("seed " + seed + " in stage " + stageCount, isUp == cut.getDirection(seed, stageCount++));
        }
    }
}