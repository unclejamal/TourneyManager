package com.pduda.tourney.web.home;

import com.pduda.tourney.domain.Tourney;
import java.util.List;

public class HomePoFactory {

    public HomePo buildPo(List<Tourney> tourneys) {
        return new HomePo();
    }
}
