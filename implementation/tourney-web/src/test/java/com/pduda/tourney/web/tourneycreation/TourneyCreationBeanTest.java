package com.pduda.tourney.web.tourneycreation;

import com.pduda.tourney.domain.EventCategory;
import com.pduda.tourney.domain.Tourney;
import com.pduda.tourney.domain.service.tourney.TourneyCreationSo;
import com.pduda.tourney.domain.service.tourney.TourneyEventSo;
import com.pduda.tourney.domain.service.tourney.TourneyHandler;
import com.pduda.tourney.domain.util.MyUtils;
import com.pduda.tourney.web.jsf.JsfWebUtils;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.event.ActionEvent;
import org.hamcrest.Description;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;
import org.junit.internal.matchers.TypeSafeMatcher;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TourneyCreationBeanTest {

    public final static Date NOW = new Date(123);
    public final static Date TOMORROW = new Date(234);
    private TourneyCreationBean cut;
    @Mock
    private TourneyHandler tourneyHandler;
    @Mock
    private ActionEvent actionEvent;
    @Mock
    private JsfWebUtils webUtils;

    @Before
    public void setUp() {
        cut = new TourneyCreationBean();
        cut.setTourneyHandler(tourneyHandler);
        cut.setWebUtils(webUtils);
        cut.setClock(new FakeClock(NOW));
        when(tourneyHandler.getTourneys()).thenReturn(oneTourney(1));
        cut.init();
    }

    @Test
    public void construction() {
        assertEquals(EventCategory.values().length, cut.getCategorySuggestions().length);
        assertEquals("Tourney 2", cut.getPo().getTourneyName());
        assertEquals(0, cut.getPo().getEvents().size());
        assertEvent(EventCategory.OS, NOW, cut.getPo().getNewEvent());
    }

    @Test
    public void addEvent() {
        setupNewEvent(EventCategory.AS, TOMORROW);

        cut.addEvent(actionEvent);

        assertEquals(1, cut.getPo().getEvents().size());
        assertEvent(EventCategory.AS, TOMORROW, cut.getPo().getEvents().get(0));
    }

    @Test
    public void removeEvent() {
        setupNewEvent(EventCategory.AS, TOMORROW);
        cut.addEvent(actionEvent);
        assertEquals(1, cut.getPo().getEvents().size());

        when(webUtils.getRequestAttribute(any(), eq(TourneyCreationBean.ATTR_CATEGORY))).thenReturn("AS");
        cut.removeEvent(actionEvent);

        assertEquals(0, cut.getPo().getEvents().size());
    }

    @Test
    public void createTourney() {
        setupNewEvent(EventCategory.AS, TOMORROW);
        cut.addEvent(actionEvent);
        cut.getPo().setTourneyName("Liga Weekendowa");
        when(tourneyHandler.createTournament(any(TourneyCreationSo.class))).thenReturn(1L);

        cut.createTourney();

        verify(tourneyHandler).createTournament(argThat(
                new TourneyCreationMatcher("Liga Weekendowa", MyUtils.asList(new TourneyEventSo(EventCategory.AS, TOMORROW)))));
        verify(webUtils).redirect("manageTourney.html?tourneyId=1");
    }

    private List<Tourney> oneTourney(int eventsTotal) {
        List<Tourney> tourneys = new ArrayList<Tourney>();
        for (int i = 0; i < eventsTotal; i++) {
            tourneys.add(new Tourney("zenon"));
        }
        return tourneys;
    }

    private void setupNewEvent(EventCategory category, Date date) {
        cut.getPo().getNewEvent().setCategory(category);
        cut.getPo().getNewEvent().setDate(date);
    }

    private void assertEvent(EventCategory category, Date date, TourneyEventPo event) {
        assertEquals(category, event.getCategory());
        assertEquals(date, event.getDate());
    }

    private TourneyCreationSo dto(String tourneyName, int tablesTotal, List<TourneyEventPo> events) {
        return null;
    }

    private static class TourneyCreationMatcher extends TypeSafeMatcher<TourneyCreationSo> {

        private final String tourneyName;
        private final List<TourneyEventSo> events;

        public TourneyCreationMatcher(String tourneyName, List<TourneyEventSo> events) {
            this.tourneyName = tourneyName;
            this.events = events;
        }

        @Override
        public boolean matchesSafely(TourneyCreationSo actual) {
            final boolean mTourneyName = tourneyName.equals(actual.tourneyName);
            final boolean mEvents = equal(events, actual.events);

            return mTourneyName && mEvents;
        }

        @Override
        public void describeTo(Description description) {
            description.appendText("dto should have").appendValue(tourneyName);

        }

        private boolean equal(List<TourneyEventSo> expected, List<TourneyEventSo> actual) {
            return expected.equals(actual);
        }
    }
}