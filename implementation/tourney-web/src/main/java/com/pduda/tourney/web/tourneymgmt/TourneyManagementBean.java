package com.pduda.tourney.web.tourneymgmt;

import com.pduda.tourney.domain.GameCode;
import com.pduda.tourney.domain.Tourney;
import com.pduda.tourney.domain.service.tourney.TourneyHandler;
import com.pduda.tourney.web.jsf.WebUtils;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;
import org.springframework.context.annotation.Scope;

@Named("tourneyMgmt")
@Scope("view")
public class TourneyManagementBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final transient Logger log = Logger.getLogger(TourneyManagementBean.class.getClass().getName());
    public static final String PARAM_TOURNEY_ID = "tourneyId";
    public static final String ATTR_EVENT_ID = "eventId";
    @Inject
    private TourneyHandler tourneyHandler;
    @Inject
    private WebUtils webUtils;
    private TourneyManagementPo po;
    private EventPlayerCreator eventPlayerCreator = new EventPlayerCreator();
    private TourneyPlayerPoFactory playerPoFactory = new TourneyPlayerPoFactory();
    private List<String> rankingSuggestions = new ArrayList<String>();
    private long tourneyId;

    @PostConstruct
    public void init() {
        this.tourneyId = getTourneyId();
        refreshPo();
    }

    public void startEvent(ActionEvent event) {
        String eventId = webUtils.getRequestAttribute(event, ATTR_EVENT_ID);
        log.log(Level.INFO, "User wants to start event {0}", eventId);
        tourneyHandler.startEvent(Long.valueOf(eventId));
        refreshPo();
    }

    public void pauseEvent(ActionEvent event) {
        String eventId = webUtils.getRequestAttribute(event, ATTR_EVENT_ID);
        log.log(Level.INFO, "User wants to pause event {0}", eventId);
//        tourneyHandler.startEvent(Long.valueOf(eventId));
        refreshPo();
    }

    public void cancelEvent(ActionEvent event) {
        String eventId = webUtils.getRequestAttribute(event, ATTR_EVENT_ID);
        log.log(Level.INFO, "User wants to cancel event {0}", eventId);
//        tourneyHandler.startEvent(Long.valueOf(eventId));
        refreshPo();
    }

    public void updateTablesTotal(ActionEvent event) {
        log.log(Level.INFO, "User wants to updates tables total to {0}", po.getTablesTotal());
//        tourneyHandler.startEvent(Long.valueOf(eventId));
        refreshPo();
    }

    public void startGame(ActionEvent event) {
        long attrEventId = Long.valueOf(webUtils.getRequestAttribute(event, ATTR_EVENT_ID));
        String attrGameCode = webUtils.getRequestAttribute(event, "gameCode");
        log.log(Level.INFO, "User wants to start game {0}", new Object[]{attrGameCode});
        tourneyHandler.startGame(attrEventId, new GameCode(attrGameCode));
        refreshPo();
    }

    public void reportWinner(ActionEvent event) {
        String attrGameCode = webUtils.getRequestAttribute(event, "gameCode");
        String attrWinnerTeamCode = webUtils.getRequestAttribute(event, "winnerTeamCode");
        String attrEventId = webUtils.getRequestAttribute(event, ATTR_EVENT_ID);
        log.log(Level.INFO, "User wants to report winner of {0} - {1}", new Object[]{attrGameCode, attrWinnerTeamCode});
        tourneyHandler.reportWinner(Long.valueOf(attrEventId), new GameCode(attrGameCode), Long.valueOf(attrWinnerTeamCode));
        refreshPo();
    }

    private void refreshPo() {
        Tourney tourney = tourneyHandler.getTourney(this.tourneyId);
        this.po = new TourneyManagementPoFactory().buildPo(tourney);
    }

    protected long getTourneyId() {
        String id = webUtils.getRequestParameter(PARAM_TOURNEY_ID);
        return Long.valueOf(webUtils.getRequestParameter(PARAM_TOURNEY_ID));
    }

    public TourneyManagementPo getPo() {
        return po;
    }

    void setWebUtils(WebUtils webUtils) {
        this.webUtils = webUtils;
    }

    void setTourneyHandler(TourneyHandler tourneyHandler) {
        this.tourneyHandler = tourneyHandler;
    }
}