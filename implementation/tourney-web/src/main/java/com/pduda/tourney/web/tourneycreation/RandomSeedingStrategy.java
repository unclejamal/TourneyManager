package com.pduda.tourney.web.tourneycreation;

import com.pduda.tourney.domain.Team;
import java.util.List;
import javax.inject.Named;

@Named
public class RandomSeedingStrategy implements SeedingStrategy {

    @Override
    public void seed(List<Team> teams) {
        // nothing to do
    }
}
