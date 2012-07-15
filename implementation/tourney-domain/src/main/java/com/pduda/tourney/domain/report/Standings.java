package com.pduda.tourney.domain.report;

import com.pduda.tourney.domain.Team;
import java.io.Serializable;
import java.util.*;

public class Standings extends HashMap<String, Set<Team>> implements Serializable {

    private static final long serialVersionUID = 1L;

    public List<Team> getByPlace(String place) {
        return new ArrayList<Team>(get(place));
    }

    public void addPlace(String place, Team team) {
        if (!containsKey(place)) {
            put(place, new HashSet<Team>());
        }

        get(place).add(team);
    }
}
