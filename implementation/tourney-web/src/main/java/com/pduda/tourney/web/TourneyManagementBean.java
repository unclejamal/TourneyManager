package com.pduda.tourney.web;

import com.pduda.tourney.domain.GameCode;
import com.pduda.tourney.domain.report.StandingsTextReport;
import com.pduda.tourney.domain.service.TourneyHandler;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;
import org.springframework.context.annotation.Scope;

@Named("tourneyMgmt")
@Scope("view")
public class TourneyManagementBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final transient Logger log = Logger.getLogger(TourneyManagementBean.class.getClass().getName());
    @Inject
    private TourneyHandler tournamentHandler;
    @Inject
    private StandingsTextReport report;
    private TourneyPO tourney;
    private int tourneyId;

    @PostConstruct
    public void init() {
        tourneyId = getTourneyId();
        refreshPo();
    }

    public void startTourney(ActionEvent event) {
        log.log(Level.INFO, "User wants to start tourney {0}", tourney.getId());
        tournamentHandler.startTourney(tourneyId);
        refreshPo();
    }

    public void startGame(ActionEvent event) {
        String attrGameCode = event.getComponent().getAttributes().get("gameCode").toString();
        log.log(Level.INFO, "User wants to start game {0}", new Object[]{attrGameCode});
        tournamentHandler.startGame(tourneyId, new GameCode(attrGameCode));
        refreshPo();
    }

    public void reportWinner(ActionEvent event) {
        String attrGameCode = event.getComponent().getAttributes().get("gameCode").toString();
        String attrWinnerTeamCode = event.getComponent().getAttributes().get("winnerTeamCode").toString();
        log.log(Level.INFO, "User wants to report winner of {0} - {1}", new Object[]{attrGameCode, attrWinnerTeamCode});
        tournamentHandler.reportWinner(tourneyId, new GameCode(attrGameCode), Long.valueOf(attrWinnerTeamCode));
        refreshPo();
    }

    private void refreshPo() {
        this.tourney = new TourneyPO(tournamentHandler.getTournament(tourneyId), report);
        System.out.println("Waitz0rage at PO: " + tourney.getWaitingGames());
    }

    public TourneyPO getTourney() {
        return tourney;
    }

    protected int getTourneyId() {
        return Integer.valueOf(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id"));
    }
}