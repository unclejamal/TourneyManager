package com.pduda.tourney.web.tourneymgmt;

import com.pduda.tourney.domain.RankClass;

public class PlayerPo {

    private String fullName;
    private String club;
    private int points;
    private RankClass rankClass;
    private long fee;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getClub() {
        return club;
    }

    public void setClub(String club) {
        this.club = club;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public RankClass getRankClass() {
        return rankClass;
    }

    public void setRankClass(RankClass rankClass) {
        this.rankClass = rankClass;
    }

    public long getFee() {
        return fee;
    }

    public void setFee(long fee) {
        this.fee = fee;
    }
}
