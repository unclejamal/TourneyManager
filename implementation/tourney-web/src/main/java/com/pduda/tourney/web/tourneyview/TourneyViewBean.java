package com.pduda.tourney.web.tourneyview;

import com.pduda.tourney.domain.Tourney;
import com.pduda.tourney.domain.TourneyObjectMother;
import com.pduda.tourney.domain.service.tourney.TourneyHandler;
import com.pduda.tourney.domain.util.MyUtils;
import com.pduda.tourney.web.jsf.RequestParam;
import com.pduda.tourney.web.jsf.WebUtils;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import org.springframework.context.annotation.Scope;

@Named("tourneyView")
@Scope("view")
public class TourneyViewBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final transient Logger log = Logger.getLogger(TourneyViewBean.class.getClass().getName());
    public static final String PARAM_TOURNEY_ID = "tourneyId";
    public static final String PARAM_EVENT_ID = "eventId";
    @Inject
    private TourneyHandler tournamentHandler;
    @Inject
    private WebUtils webUtils;
    private TourneyViewPoFactory poFactory = new TourneyViewPoFactory();
    private TourneyViewPo po;
    private long tourneyId = -1;
    private long selectedEventId = -1;

    @PostConstruct
    public void init() {
        tourneyId = fetchTourneyIdFromRequest();

        Tourney tourney;
        if (tourneyId == 697) {
            tourney = TourneyObjectMother.createTourneyPlayed(7);
        } else if (tourneyId == 6915) {
            tourney = TourneyObjectMother.createTourneyPlayed(15);
        } else if (tourneyId == 6931) {
            tourney = TourneyObjectMother.createTourneyPlayed(31);
        } else if (tourneyId == 6963) {
            tourney = TourneyObjectMother.createTourneyPlayed(63);
        } else {
            tourney = tournamentHandler.getTourney(tourneyId);
        }
        
        
        try {
            selectedEventId = fetchEventIdFromRequest();
        } catch (EventIdNotAvailableException ex) {
            selectedEventId = MyUtils.any(tourney.getTourneyEvents()).getId();
            webUtils.redirect("viewTourney.html", new RequestParam(PARAM_TOURNEY_ID, tourneyId), new RequestParam(PARAM_EVENT_ID, selectedEventId));
        }

        po = poFactory.buildPo(tourney, selectedEventId);
    }

    public long getTourneyId() {
        return tourneyId;
    }

    public void setTourneyId(long tourneyId) {
        this.tourneyId = tourneyId;
    }

    public long getEventId() {
        return selectedEventId;
    }

    public void setEventId(long eventId) {
        this.selectedEventId = eventId;
    }

    public TourneyViewPo getPo() {
        return po;
    }

    public void setPo(TourneyViewPo po) {
        this.po = po;
    }

    protected long fetchTourneyIdFromRequest() {
        return Long.valueOf(webUtils.getRequestParameter(PARAM_TOURNEY_ID));
    }

    protected long fetchEventIdFromRequest() throws EventIdNotAvailableException {
        String eventIdString = webUtils.getRequestParameter(PARAM_EVENT_ID);
        try {
            long eventId = Long.valueOf(eventIdString);
            return eventId;

        } catch (Exception e) {
            throw new EventIdNotAvailableException(e);
        }
    }
}