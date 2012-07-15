package com.pduda.tourney.domain.report;

import com.pduda.tourney.domain.Team;
import com.pduda.tourney.domain.TeamBySeedComparator;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import org.springframework.stereotype.Component;

@Component
public class StandingsTextReport implements Serializable {

    public String report(Standings standings) {
        StringBuilder sb = new StringBuilder();

        Set<String> keys = standings.keySet();
        List<String> places = new ArrayList<String>(keys);
        Collections.sort(places);

        int standingCount = 0;
        for (String place : places) {
            standingCount++;
            final List<Team> value = standings.getByPlace(place);
            List<Team> teams = new ArrayList<Team>(value);
            Collections.sort(teams, new TeamBySeedComparator());

            int teamCount = 0;
            for (Team team : teams) {
                teamCount++;
                sb.append(place);
                sb.append(". ");
                sb.append(team.getMembers().get(0).getFullName());
                if (team.getMembers().size() == 2) {
                    sb.append(", ");
                    sb.append(team.getMembers().get(1).getFullName());
                }

                sb.append(" (");
                sb.append(team.getSeed());
                sb.append(")");
                if ((teams.size() != teamCount) || (standings.size() != standingCount)) {
                    sb.append(System.getProperty("line.separator"));
                }
            }
        }

        return sb.toString();
    }
}
