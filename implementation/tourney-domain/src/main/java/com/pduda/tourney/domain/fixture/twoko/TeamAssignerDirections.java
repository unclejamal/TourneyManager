package com.pduda.tourney.domain.fixture.twoko;

import java.io.Serializable;

public class TeamAssignerDirections implements Serializable {

    private static final long serialVersionUID = 1L;
    private boolean[][] directions;

    /**
     *
     * @param tournamentStages 0 - only final, 1 - with semis, 2 - with quarters
     */
    public void createDirections(final int tournamentStages) {
        final int teamsTotal = (int) Math.pow(2, tournamentStages);

        directions = new boolean[tournamentStages][teamsTotal];

        fill(directions[0], 1, true, false);
        for (int stage = 1; stage < tournamentStages; stage++) {
            fill(directions[stage], (int) Math.pow(2, stage - 1), true, false, true, false);
        }
    }

    private void fill(boolean[] stageDirections, int repeatValues, Boolean... values) {
        int count = 0;
        try {
            while (count != stageDirections.length) {

                // normally
                for (int rv = 0; rv < repeatValues; rv++) {
                    for (int i = 0; i < values.length; i++) {
                        Boolean value = values[i];
                        setNewDrection(stageDirections, count++, value);
                    }
                }

                // recursively
                for (int rv = 0; rv < repeatValues; rv++) {
                    for (int i = 0; i < values.length; i++) {
                        Boolean value = values[values.length - 1 - i];
                        setNewDrection(stageDirections, count++, value);
                    }
                }
            }
        } catch (ArrayStoreException e) {
            // eat it - workaround for breaking out of all iterations
        }
    }

    private void setNewDrection(boolean[] stageDirections, int i, Boolean value) throws ArrayStoreException {
        if (i == stageDirections.length) {
            throw new ArrayStoreException("lol");
        }
        stageDirections[i] = value;
    }

    public boolean getDirection(int seed, int tournamentStage) {
        return directions[tournamentStage - 1][seed - 1];
    }
}
