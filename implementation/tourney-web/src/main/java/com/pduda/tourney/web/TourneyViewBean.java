package com.pduda.tourney.web;

import com.google.gson.Gson;
import com.pduda.tourney.domain.ObjectMother;
import com.pduda.tourney.domain.report.StandingsTextReport;
import java.io.Serializable;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;

import org.springframework.context.annotation.Scope;

import com.pduda.tourney.domain.Tournament;
import com.pduda.tourney.domain.report.FullGamesReport;
import com.pduda.tourney.domain.service.TourneyHandler;
import java.util.logging.Level;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named("tourneyView")
@Scope("view")
public class TourneyViewBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final transient Logger log = Logger.getLogger(TourneyViewBean.class.getClass().getName());
    @Inject
    private TourneyHandler tournamentHandler;
    private String standingsTextReport;
    private Tournament tourney;
    private String wbrAsJson;
    private String lbrAsJson;
    private String fin1AsJson;
    private String fin2AsJson;

    private String fetchPartialGamesReport(FullGamesReport fullReport, String partial) {
        Gson gson = new Gson();
        return gson.toJson(fullReport.getPartialReport(partial));
    }

    private String fetchStandingsReport(Tournament tournament) {
        return new StandingsTextReport().report(tournament.getStandings());

    }

    @PostConstruct
    public void init() {
        int tourneyId = getTourneyId();
        if (tourneyId == 697) {
            this.tourney = ObjectMother.createTournament("Wypas Tourney", 7);
        } else if (tourneyId == 6915) {
            this.tourney = ObjectMother.createTournament("Wypas Tourney", 15);
        } else if (tourneyId == 6931) {
            this.tourney = ObjectMother.createTournament("Wypas Tourney", 31);
        } else if (tourneyId == 6963) {
            this.tourney = ObjectMother.createTournament("Wypas Tourney", 63);
        } else {
            this.tourney = tournamentHandler.getTournament(tourneyId);
        }
        wbrAsJson = fetchPartialGamesReport(tourney.getGamesReports(), "wbr");
        lbrAsJson = fetchPartialGamesReport(tourney.getGamesReports(), "lbr");
        fin1AsJson = fetchPartialGamesReport(tourney.getGamesReports(), "fin1");
        fin2AsJson = fetchPartialGamesReport(tourney.getGamesReports(), "fin2");

//        log.info("gamesAsJson " + gamesAsJson);
        standingsTextReport = fetchStandingsReport(tourney);
    }

    public void startTourney(ActionEvent event) {
        log.log(Level.INFO, "User wants to start tourney {0}", tourney.getId());
        tournamentHandler.startTourney(tourney.getId());
    }

    public String getFin1AsJson() {
        return fin1AsJson;
    }

    public String getFin2AsJson() {
        return fin2AsJson;
    }

    public String getLbrAsJson() {
        return lbrAsJson;
    }

    public String getWbrAsJson() {
        return wbrAsJson;
    }

    public String getStandings() {
        return standingsTextReport;
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