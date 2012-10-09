package com.pduda.tourney.web.tourneyview;

import com.pduda.tourney.domain.Tourney;
import com.pduda.tourney.domain.TourneyObjectMother;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TourneyViewPoFactoryTest {

    private TourneyViewPoFactory cut;

    @Before
    public void setUp() {
        cut = new TourneyViewPoFactory();
    }

    @Test
    public void test() {
        Tourney tourney = TourneyObjectMother.createTourneyPlayed(4);

        TourneyViewPo po = cut.buildPo(tourney, TourneyObjectMother.EVENT_ID);

        assertEquals(tourney.getId(), po.getTourneyId());
        assertEquals(tourney.getName(), po.getName());
        assertTrue(po.getSelectedEvent().getStarted());
        assertEquals(TourneyObjectMother.EVENT_CATEGORY.toString(), po.getSelectedEvent().getEventCategory());
        assertTrue(po.getSelectedEvent().getStandingsTextReport() != null);
        assertTrue(po.getSelectedEvent().getFin1AsJson() != null);
        assertTrue(po.getSelectedEvent().getFin2AsJson() != null);
        assertTrue(po.getSelectedEvent().getWbrAsJson() != null);
        assertTrue(po.getSelectedEvent().getLbrAsJson() != null);

        assertEquals(1, po.getSelectableEvents().size());


    }
}