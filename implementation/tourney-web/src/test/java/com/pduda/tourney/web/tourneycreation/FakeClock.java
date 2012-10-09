package com.pduda.tourney.web.tourneycreation;

import com.pduda.tourney.domain.util.Clock;
import java.util.Date;

public class FakeClock implements Clock {

    private Date now;

    public FakeClock(Date now) {
        this.now = now;
    }

    @Override
    public Date now() {
        return now;
    }
    
    
}
