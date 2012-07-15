package com.pduda.tourney.domain.fixture.twoko;

import com.pduda.tourney.domain.report.FullGamesReport;
import com.pduda.tourney.domain.Fixture;
import java.io.Serializable;

public interface GamesReportFactory extends Serializable {

    FullGamesReport create(Fixture fixture);
}
