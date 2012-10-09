package com.pduda.tourney.web.tourneymgmt;

import com.pduda.tourney.domain.Tourney;
import com.pduda.tourney.domain.TourneyObjectMother;
import com.pduda.tourney.domain.service.tourney.TourneyHandler;
import com.pduda.tourney.web.jsf.WebUtils;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class TourneyManagementBeanTest {

    private TourneyManagementBean cut;
    @Mock
    private WebUtils webUtils;
    @Mock
    private TourneyHandler tourneyHandler;

    @Before
    public void setUp() {
        cut = new TourneyManagementBean();
        cut.setWebUtils(webUtils);
        cut.setTourneyHandler(tourneyHandler);

        Tourney tourney = TourneyObjectMother.createTourneyNotPlayed(4);

        when(webUtils.getRequestParameter(TourneyManagementBean.PARAM_TOURNEY_ID)).thenReturn("1");
        when(tourneyHandler.getTourney(1L)).thenReturn(tourney);

        cut.init();
    }

    @Test
    public void testInit() {
        assertEquals(TourneyObjectMother.TOURNEY_NAME, cut.getPo().getTourneyName());

        assertEquals(2, cut.getPo().getWaitingGames().size());
        assertEquals(0, cut.getPo().getOngoingGames().size());
        assertEquals(1, cut.getPo().getEvents().size());
        assertTrue(cut.getPo().getEvents().get(0).getDoubleGame());
        assertEquals(TourneyObjectMother.EVENT_CATEGORY, cut.getPo().getEvents().get(0).getEventCategory());
        assertTrue(cut.getPo().getEvents().get(0).getStarted());
        assertEquals(4, cut.getPo().getEvents().get(0).getTeams().size());

    }
}