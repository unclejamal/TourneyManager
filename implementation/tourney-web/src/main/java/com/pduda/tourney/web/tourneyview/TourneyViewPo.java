package com.pduda.tourney.web.tourneyview;

import java.util.ArrayList;
import java.util.List;

public class TourneyViewPo {

    private String name;
    private long tourneyId;
    private TourneyEventPo selectedEvent;
    private List<SelectableTourneyEventPo> selectableEvents = new ArrayList<SelectableTourneyEventPo>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TourneyEventPo getSelectedEvent() {
        return selectedEvent;
    }

    public void setSelectedEvent(TourneyEventPo selectedEvent) {
        this.selectedEvent = selectedEvent;
    }

    public List<SelectableTourneyEventPo> getSelectableEvents() {
        return selectableEvents;
    }

    public void setSelectableEvents(List<SelectableTourneyEventPo> selectableEvents) {
        this.selectableEvents = selectableEvents;
    }

    public long getTourneyId() {
        return tourneyId;
    }

    public void setTourneyId(long tourneyId) {
        this.tourneyId = tourneyId;
    }
}
