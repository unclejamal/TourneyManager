package com.pduda.tourney.domain.adapters.util;

import com.pduda.tourney.domain.util.Clock;
import java.util.Date;
import javax.inject.Named;

@Named
public class DateClock implements Clock {

    @Override
    public Date now() {
        return new Date();
    }
    
    
}
