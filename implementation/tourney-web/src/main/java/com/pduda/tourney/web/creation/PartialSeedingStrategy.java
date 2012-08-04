package com.pduda.tourney.web.creation;

import com.pduda.tourney.domain.Team;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;

@Named
public class PartialSeedingStrategy implements SeedingStrategy {
    
    @Inject
    private SeededTotalCalculator seededTotalCalc;

    @Override
    public void seed(List<Team> teams) {
        int seededTotal = seededTotalCalc.calculateSeededTotal(teams.size());

        for (int i = 0; i < seededTotal; i++) {
            Team team = teams.get(i);
            team.setSeed(i + 1);
        }
    }
}
