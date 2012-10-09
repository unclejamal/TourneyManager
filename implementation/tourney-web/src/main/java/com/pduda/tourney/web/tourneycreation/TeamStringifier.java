package com.pduda.tourney.web.tourneycreation;

import com.pduda.tourney.domain.EventPlayer;
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

        if (team.getEventPlayerOne().getFullName() != null) {
            sb.append(team.getEventPlayerOne().getFullName());
            sb.append(SEPARATOR);
            sb.append(team.getEventPlayerOne().getCode());
        }
        if (team.isDouble()) {
            sb.append(SEPARATOR);
            sb.append(team.getEventPlayerTwo().getFullName());
            sb.append(SEPARATOR);
            sb.append(team.getEventPlayerTwo().getCode());
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
            team = new Team(new EventPlayer(member1name, member1code), new EventPlayer(member2name, member2code));
        } else {
            team = new Team(new EventPlayer(member1name, member1code));
        }

        return team;
    }
}
