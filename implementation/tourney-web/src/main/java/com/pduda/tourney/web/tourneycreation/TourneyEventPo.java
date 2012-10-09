package com.pduda.tourney.web.tourneycreation;

import com.pduda.tourney.domain.EventCategory;
import java.util.Date;

public class TourneyEventPo {

    private EventCategory category;
    private Date date;

    public TourneyEventPo(EventCategory category, Date date) {
        this.category = category;
        this.date = date;
    }

    public EventCategory getCategory() {
        return category;
    }

    public void setCategory(EventCategory category) {
        this.category = category;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
