package com.pduda.tourney.web.home;

import com.pduda.tourney.domain.Tourney;
import com.pduda.tourney.domain.TourneyEvent;
import java.util.List;
import java.util.Set;

public class HomePoFactory {

    public HomePo buildPo(List<Tourney> tourneys) {
        HomePo po = new HomePo();

        for (Tourney tourney : tourneys) {
            po.addTourney(buildTourneyPo(tourney));
        }

        return po;
    }

    private TourneyPo buildTourneyPo(Tourney tourney) {
        TourneyPo po = new TourneyPo();
        po.setId(tourney.getId());
        po.setName(tourney.getName());

        po.setEvents(buildEvents(tourney.getTourneyEvents()));


        return po;
    }
//
//    private EventPo buildEventPo(TourneyEvent event) {
//        EventPo po = new EventPo();
//
//        return po;
//    }

    private String buildEvents(Set<TourneyEvent> events) {
        StringBuilder sb = new StringBuilder();
        int i = 1;
        for (TourneyEvent event : events) {
            sb.append(event.getEventCategory());
            if (i++ < events.size()) {
                sb.append(", ");
            }
        }

        return sb.toString();
    }
}
