package com.pduda.tourney.domain.fixture.twoko;

import com.pduda.tourney.domain.Team;
import java.util.Collection;

public interface TeamAssigner {

    void assignTeams(Bracket bracket, Collection<Team> teams);
}
