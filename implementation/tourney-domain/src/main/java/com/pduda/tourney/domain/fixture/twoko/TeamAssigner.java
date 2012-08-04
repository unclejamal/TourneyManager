package com.pduda.tourney.domain.fixture.twoko;

import com.pduda.tourney.domain.Team;
import java.util.List;

public interface TeamAssigner {

    void assignTeams(Bracket bracket, List<Team> teams);
}
