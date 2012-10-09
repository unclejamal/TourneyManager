package com.pduda.tourney.domain.service.tourney;

import com.pduda.tourney.domain.EventCategory;
import java.util.Date;
import java.util.Objects;

public class TourneyEventSo {

    public final EventCategory category;
    public final Date date;

    public TourneyEventSo(EventCategory category, Date date) {
        this.category = category;
        this.date = date;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TourneyEventSo other = (TourneyEventSo) obj;
        if (this.category != other.category) {
            return false;
        }
        if (!Objects.equals(this.date, other.date)) {
            return false;
        }
        return true;
    }
}
