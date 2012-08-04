package com.pduda.tourney.web.creation;

import com.pduda.tourney.domain.Team;
import java.util.List;

public interface SeedingStrategy {

    void seed(List<Team> teams);
}
