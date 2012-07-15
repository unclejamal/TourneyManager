package com.pduda.tourney.web;

import com.pduda.tourney.domain.GameId;
import com.pduda.tourney.domain.report.StandingsTextReport;
import java.io.Serializable;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;

import org.springframework.context.annotation.Scope;

import com.pduda.tourney.domain.Tournament;
import com.pduda.tourney.domain.service.TournamentHandler;
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
    private TournamentHandler tournamentHandler;
    @Inject
    private StandingsTextReport report;
    private Tournament tourney;
    

    @PostConstruct
    public void init() {
        log.info("Intentando a crear tourneyMgmt");
        int tourneyId = getTourneyId();
        log.log(Level.INFO, "Obteniendo el puto {0}", tourneyId);
        this.tourney = tournamentHandler.getTournament(tourneyId);
        log.info("Acabado de crear tourneyMgmt");
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
        String attrSeed = event.getComponent().getAttributes().get("seed").toString();
        log.log(Level.INFO, "User wants to report winner of {0} - {1}", new Object[]{attrGame, attrSeed});
        tournamentHandler.reportWinner(tourney.getId(), new GameId(attrGame), Integer.valueOf(attrSeed));
    }
    
    public String getStandings() {
        return report.report(tourney.getStandings());
    }

    public Tournament getTourney() {
        return tourney;
    }

    public void setTourney(Tournament tourney) {
        this.tourney = tourney;
    }

//
//    private TopologyDataModel topology;
//
//    public TopologyDataModel getTopology() {
//        log.info("Getting topology");
//        return topology;
//    }
//  public void upload(ActionEvent event) {
//    Long actionNetworkElement = (Long) event.getComponent().getAttributes().get("actionNetworkElement");
//    log.info("User is starting an upload for gid: " + actionNetworkElement);
//
//    taskHandler.upload(actionNetworkElement);
//    getTopologyFromHibernate();
//  }
//
//  public void refreshTopology(ActionEvent event) {
//    getTopologyFromHibernate();
//  }
//
//  public void populateDatabase(ActionEvent event) {
//    networkElementRepo.createDummyNetworkElements();
//    getTopologyFromHibernate();
//
//  }
//
//  private void getTopologyFromHibernate() {
//    List<NetworkElement> list = networkElementRepo.findAll();
//    log.info("Topology is: " + list);
//    topology = new TopologyDataModel(list);
//  }
    protected int getTourneyId() {
        return Integer.valueOf(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id"));
    }
}