package com.pduda.tourney.domain.report;

import com.pduda.tourney.domain.Fixture;
import java.io.Serializable;

public interface GamesReportFactory extends Serializable {

    FullGamesReport create(Fixture fixture);
}
