package com.pduda.tourney.web.home;

import com.pduda.tourney.domain.TourneyObjectMother;
import com.pduda.tourney.domain.Tourney;
import com.pduda.tourney.domain.util.MyUtils;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class HomePoFactoryTest {

    private HomePoFactory cut;

    @Before
    public void setUp() {
        cut = new HomePoFactory();
    }

    @Test
    public void testBuildPo() {
        Tourney tourney = TourneyObjectMother.createTourneyNotPlayed(4);

        HomePo po = cut.buildPo(MyUtils.asList(tourney));

        assertEquals(1, po.getTourneys().size());
        TourneyPo actual = po.getTourneys().get(0);

        assertEquals(tourney.getId(), actual.getId());
        assertEquals(tourney.getName(), actual.getName());
        assertEquals(TourneyObjectMother.EVENT_CATEGORY.toString(), actual.getEvents());
    }
}