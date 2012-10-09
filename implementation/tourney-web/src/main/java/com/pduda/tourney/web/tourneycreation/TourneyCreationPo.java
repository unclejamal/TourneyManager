package com.pduda.tourney.web.tourneycreation;

import com.pduda.tourney.domain.EventCategory;
import com.pduda.tourney.domain.util.Clock;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TourneyCreationPo {

    private final Clock clock;
    private String tourneyName;
    private List<TourneyEventPo> events = new ArrayList<TourneyEventPo>();
    private TourneyEventPo newEvent;

    public TourneyCreationPo(String tourneyName, Clock clock) {
        this.tourneyName = tourneyName;
        this.clock = clock;
        this.newEvent = new TourneyEventPo(EventCategory.OS, clock.now());
    }

    public List<TourneyEventPo> getEvents() {
        return events;
    }

    public void setEvents(List<TourneyEventPo> events) {
        this.events = events;
    }

    public String getTourneyName() {
        return tourneyName;
    }

    public void setTourneyName(String tourneyName) {
        this.tourneyName = tourneyName;
    }

    public TourneyEventPo getNewEvent() {
        return newEvent;
    }

    public void setNewEvent(TourneyEventPo newEvent) {
        this.newEvent = newEvent;
    }

    public void addNewEvent() {
        TourneyEventPo addMe = new TourneyEventPo(newEvent.getCategory(), newEvent.getDate());
        events.add(addMe);
    }

    public void removeEvent(EventCategory category) {

        for (Iterator<TourneyEventPo> i = events.iterator(); i.hasNext();) {
            TourneyEventPo e = i.next();
            if (category.equals(e.getCategory())) {
                i.remove();
            }
        }
    }
}
