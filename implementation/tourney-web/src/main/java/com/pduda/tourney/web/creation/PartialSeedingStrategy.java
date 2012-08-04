package com.pduda.tourney.web.creation;

import com.pduda.tourney.domain.Team;
import java.util.List;

public class PartialSeedingStrategy implements SeedingStrategy {

    private final int seededTotal;

    public PartialSeedingStrategy(int seededTotal) {
        this.seededTotal = seededTotal;
    }

    @Override
    public void seed(List<Team> teams) {
        for (int i = 0; i < seededTotal; i++) {
            Team team = teams.get(i);
            team.setSeed(i + 1);
        }
    }
}
