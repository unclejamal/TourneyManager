package com.pduda.tourney.domain.adapters.ranking;

public class RankingEntry {

    public final int rank;
    public final String code;
    public final String fullName;
    public final String gender;
    public final String city;
    public final String club;
    public final int points;
    public final int pointsAdded;
    public final int pointsDeleted;
    public final String rankClass;

    public RankingEntry(int rank, String code, String fullName, String gender, String city, String club, int points, int pointsAdded, int pointsDeleted, String rankClass) {
        this.rank = rank;
        this.code = code;
        this.fullName = fullName;
        this.gender = gender;
        this.city = city;
        this.club = club;
        this.points = points;
        this.pointsAdded = pointsAdded;
        this.pointsDeleted = pointsDeleted;
        this.rankClass = rankClass;
    }
}
