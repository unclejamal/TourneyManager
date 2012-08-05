package com.pduda.tourney.web;

import com.pduda.tourney.domain.GameId;
import com.pduda.tourney.domain.report.StandingsTextReport;
import java.io.Serializable;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;

import org.springframework.context.annotation.Scope;

import com.pduda.tourney.domain.Tourney;
import com.pduda.tourney.domain.service.TourneyHandler;
import java.util.logging.Level;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named("tourneyMgmt")
@Scope("view")
public class TourneyManagementBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final transient Logger log = Logger.getLogger(TourneyManagementBean.class.getClass().getName());
    @Inject
    private TourneyHandler tournamentHandler;
    @Inject
    private StandingsTextReport report;
    private Tourney tourney;

    @PostConstruct
    public void init() {
        int tourneyId = getTourneyId();
        this.tourney = tournamentHandler.getTournament(tourneyId);
    }

    public void startTourney(ActionEvent event) {
        log.log(Level.INFO, "User wants to start tourney {0}", tourney.getId());
        tournamentHandler.startTourney(tourney.getId());
    }

    public void startGame(ActionEvent event) {
        String attrGame = event.getComponent().getAttributes().get("game").toString();
        log.log(Level.INFO, "User wants to start game {0}", new Object[]{attrGame});
        tournamentHandler.startGame(tourney.getId(), new GameId(attrGame));
    }

    public void reportWinner(ActionEvent event) {
        String attrGame = event.getComponent().getAttributes().get("game").toString();
        String attrWinnerId = event.getComponent().getAttributes().get("winnerId").toString();
        log.log(Level.INFO, "User wants to report winner of {0} - {1}", new Object[]{attrGame, attrWinnerId});
        tournamentHandler.reportWinner(tourney.getId(), new GameId(attrGame), Integer.valueOf(attrWinnerId));
    }

    public String getStandings() {
        return report.report(tourney.getStandings());
    }

    public Tourney getTourney() {
        return tourney;
    }

    public void setTourney(Tourney tourney) {
        this.tourney = tourney;
    }

    protected int getTourneyId() {
        return Integer.valueOf(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id"));
    }
}