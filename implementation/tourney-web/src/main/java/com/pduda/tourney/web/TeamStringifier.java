package com.pduda.tourney.web;

import com.pduda.tourney.domain.Player;
import com.pduda.tourney.domain.Team;

/**
 * Remember to adapt equals+hashCode of Team, Player etc to the fields used in
 * this class.
 *
 * @author unclejamal
 */
public class TeamStringifier {

    public static final String SEPARATOR = ":::";

    public String objectToString(Team team) {
        StringBuilder sb = new StringBuilder();

        if (team.getMembers().get(0).getFullName() != null) {
            sb.append(team.getMembers().get(0).getFullName());
            sb.append(SEPARATOR);
            sb.append(team.getMembers().get(0).getCode());
        }
        if (team.getMembers().size() > 1) {
            sb.append(SEPARATOR);
            sb.append(team.getMembers().get(1).getFullName());
            sb.append(SEPARATOR);
            sb.append(team.getMembers().get(1).getCode());
        }
        return sb.toString();
    }

    public Team stringToObject(String s) throws NumberFormatException {
        String[] params = s.split(SEPARATOR);
        String member1name = params[0];
        String member1code = params[1];

        Team team = null;
        if (params.length == 4) {
            String member2name = params[2];
            String member2code = params[3];
            team = new Team(new Player(member1name, member1code), new Player(member2name, member2code));
        } else {
            team = new Team(new Player(member1name, member1code));
        }

        return team;
    }
}
