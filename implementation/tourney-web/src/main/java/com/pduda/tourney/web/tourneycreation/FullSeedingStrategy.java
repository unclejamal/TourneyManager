package com.pduda.tourney.web.tourneycreation;

import com.pduda.tourney.domain.Team;
import java.util.List;
import javax.inject.Named;

@Named
public class FullSeedingStrategy implements SeedingStrategy {

    @Override
    public void seed(List<Team> teams) {
        for (int i = 0; i < teams.size(); i++) {
            Team team = teams.get(i);
            team.setSeed(i + 1);
        }
    }
}
