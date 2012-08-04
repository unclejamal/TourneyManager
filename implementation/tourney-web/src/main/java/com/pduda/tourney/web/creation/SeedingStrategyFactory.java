package com.pduda.tourney.web.creation;

import static com.pduda.tourney.web.creation.SeedingType.*;
import javax.inject.Inject;
import javax.inject.Named;

@Named
public class SeedingStrategyFactory {
    
    @Inject
    private FullSeedingStrategy fullSeedingStrategy;
    @Inject
    private PartialSeedingStrategy partialSeedingStrategy;
    @Inject
    private RandomSeedingStrategy randomSeedingStrategy;
    

    public SeedingStrategy getSeedingStrategy(SeedingType type) {
        if (FULLY.equals(type)) {
            return fullSeedingStrategy;
        } else if (PARTIALLY.equals(type)) {
            return partialSeedingStrategy;
        } else if (RANDOM.equals(type)) {
            return randomSeedingStrategy;
        }

        throw new RuntimeException("No adequate seeding strategy found");
    }
}
