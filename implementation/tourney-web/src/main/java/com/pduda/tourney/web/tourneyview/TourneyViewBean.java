package com.pduda.tourney.web.tourneyview;

import com.google.gson.Gson;
import com.pduda.tourney.domain.ObjectMother;
import com.pduda.tourney.domain.report.StandingsTextReport;
import java.io.Serializable;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Scope;

import com.pduda.tourney.domain.TourneyEvent;
import com.pduda.tourney.domain.Tourney;
import com.pduda.tourney.domain.report.FullGamesReport;
import com.pduda.tourney.domain.service.TourneyHandler;
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
    private Tourney tourney;
    private String wbrAsJson;
    private String lbrAsJson;
    private String fin1AsJson;
    private String fin2AsJson;

    @PostConstruct
    public void init() {
        int tourneyId = getTourneyId();
        if (tourneyId == 697) {
            this.tourney = ObjectMother.createTourneyPlayed("Wypas Tourney", 7);
        } else if (tourneyId == 6915) {
            this.tourney = ObjectMother.createTourneyPlayed("Wypas Tourney", 15);
        } else if (tourneyId == 6931) {
            this.tourney = ObjectMother.createTourneyPlayed("Wypas Tourney", 31);
        } else if (tourneyId == 6963) {
            this.tourney = ObjectMother.createTourneyPlayed("Wypas Tourney", 63);
        } else {
            this.tourney = tournamentHandler.getTourney(tourneyId);
        }
//        wbrAsJson = fetchPartialGamesReport(tourney.getGamesReports(), "wbr");
//        lbrAsJson = fetchPartialGamesReport(tourney.getGamesReports(), "lbr");
//        fin1AsJson = fetchPartialGamesReport(tourney.getGamesReports(), "fin1");
//        fin2AsJson = fetchPartialGamesReport(tourney.getGamesReports(), "fin2");

//        log.info("gamesAsJson " + gamesAsJson);
//        standingsTextReport = fetchStandingsReport(tourney);
    }

    private String fetchPartialGamesReport(FullGamesReport fullReport, String partial) {
        Gson gson = new Gson();
        return gson.toJson(fullReport.getPartialReport(partial));
    }

    private String fetchStandingsReport(TourneyEvent tournament) {
        return new StandingsTextReport().report(tournament.getStandings());
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