package com.pduda.tourney.web.tourneyview;

import com.pduda.tourney.domain.EventCategory;

public class SelectableTourneyEventPo {

    private long id;
    private EventCategory eventCategory;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public EventCategory getEventCategory() {
        return eventCategory;
    }

    public void setEventCategory(EventCategory eventCategory) {
        this.eventCategory = eventCategory;
    }
}
