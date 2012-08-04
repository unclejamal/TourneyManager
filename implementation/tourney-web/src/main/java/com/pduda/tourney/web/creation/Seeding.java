package com.pduda.tourney.web.creation;

import com.pduda.tourney.domain.Team;
import java.util.List;

public class Seeding {

    private int seededTotal;
    private Type type;

    public Seeding(Type type) {
        this.type = type;
    }

    public int getSeededTotal() {
        return seededTotal;
    }

    public void setSeededTotal(int seededTotal) {
        this.seededTotal = seededTotal;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public void seed(List<Team> teams) {
        SeedingStrategy strategy = getSeedingStrategy();
        strategy.seed(teams);
    }

    private SeedingStrategy getSeedingStrategy() {
        if (Type.FULLY.equals(type)) {
            return new FullSeedingStrategy();
        } else if (Type.PARTIALLY.equals(type)) {
            return new PartialSeedingStrategy(seededTotal);
        } else if (Type.RANDOM.equals(type)) {
            return new PartialSeedingStrategy(seededTotal);
        }

        throw new RuntimeException("No adequate seeding strategy found");
    }

    public static enum Type {

        FULLY, PARTIALLY, RANDOM;
    }
}
