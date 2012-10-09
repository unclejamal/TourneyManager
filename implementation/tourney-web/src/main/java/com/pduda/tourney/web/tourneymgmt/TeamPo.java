package com.pduda.tourney.web.tourneymgmt;

import java.util.ArrayList;
import java.util.List;

public class TeamPo {

    private long id;
    private long teamCode;
    private List<PlayerPo> members = new ArrayList<PlayerPo>();
    private int points;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<PlayerPo> getMembers() {
        return members;
    }

    public void setMembers(List<PlayerPo> members) {
        this.members = members;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public long getTeamCode() {
        return teamCode;
    }

    public void setTeamCode(long teamCode) {
        this.teamCode = teamCode;
    }
}
