package com.pduda.tourney.web.tourneycreation;

import com.pduda.tourney.domain.Team;
import java.util.List;

public interface SeedingStrategy {

    void seed(List<Team> teams);
}
