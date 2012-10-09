package com.pduda.tourney.web.tourneyview;

import com.google.gson.Gson;
import com.pduda.tourney.domain.Tourney;
import com.pduda.tourney.domain.TourneyEvent;
import com.pduda.tourney.infrastructure.gson.GsonFactory;
import com.pduda.tourney.domain.report.FullGamesReport;
import com.pduda.tourney.domain.report.StandingsTextReport;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class TourneyViewPoFactory {

    public TourneyViewPo buildPo(Tourney tourney, long selectedEventId) {
        TourneyViewPo po = new TourneyViewPo();

        po.setTourneyId(tourney.getId());
        po.setName(tourney.getName());
        po.setSelectedEvent(buildSelectedEvent(tourney.getTourneyEvent(selectedEventId)));
        po.setSelectableEvents(buildSelectableTourneyEvents(tourney.getTourneyEvents()));

        return po;
    }

    private TourneyEventPo buildSelectedEvent(TourneyEvent event) {
        TourneyEventPo po = new TourneyEventPo();

        po.setStarted(event.isStarted());
        po.setEventCategory(event.getEventCategory().toString());
        po.setStandingsTextReport(fetchStandingsReport(event));
        po.setWbrAsJson(fetchPartialGamesReport(event.getGamesReports(), "wbr"));
        po.setLbrAsJson(fetchPartialGamesReport(event.getGamesReports(), "lbr"));
        po.setFin1AsJson(fetchPartialGamesReport(event.getGamesReports(), "fin1"));
        po.setFin2AsJson(fetchPartialGamesReport(event.getGamesReports(), "fin2"));

        return po;
    }

    private List<SelectableTourneyEventPo> buildSelectableTourneyEvents(Set<TourneyEvent> tourneyEvents) {
        List<SelectableTourneyEventPo> po = new ArrayList<SelectableTourneyEventPo>();

        for (TourneyEvent tourneyEvent : tourneyEvents) {
            po.add(buildSelectableTourneyEventPo(tourneyEvent));
        }
        
        Collections.sort(po, new EventsByIdComparator());

        return po;
    }

    private SelectableTourneyEventPo buildSelectableTourneyEventPo(TourneyEvent tourneyEvent) {
        SelectableTourneyEventPo po = new SelectableTourneyEventPo();
        
        po.setId(tourneyEvent.getId());
        po.setEventCategory(tourneyEvent.getEventCategory());
        
        return po;
    }

    private String fetchPartialGamesReport(FullGamesReport fullReport, String partial) {
        Gson gson = GsonFactory.createGsonFromBuilder();
        return gson.toJson(fullReport.getPartialReport(partial));
    }

    private String fetchStandingsReport(TourneyEvent tournament) {
        return new StandingsTextReport().report(tournament.getStandings());
    }
}
