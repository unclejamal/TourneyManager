package com.pduda.tourney.domain;

import java.io.Serializable;
import java.util.Objects;

public class Player implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String SPACE = " ";
    public static final String NA = "n/a";
    private Integer rank;
    private String code;
    private String fullName;
    private Gender gender;
    private String city;
    private String club;
    private Integer points;
    private Integer pointsAdded;
    private Integer pointsDeleted;
    private RankClass rankClass;
    private Long fee;

    public Player(int rank, String code, String fullName, Gender gender, String city, String club, int points, int pointsAdded, int pointsDeleted, RankClass rankClass, long fee) {
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
        this.fee = fee;
    }

    public Player(int points, String fullName) {
        this(0, NA, fullName, Gender.UNKNOWN, NA, NA, points, 0, 0, RankClass.NOTRANKED, 0);
    }

    public Player(String fullName, String code) {
        this(0, code, fullName, Gender.UNKNOWN, NA, NA, 0, 0, 0, RankClass.NOTRANKED, 0);
    }

    public Player(String fullName) {
        this(0, fullName);
    }

    public Player() {
    }

    public String getShortName() {
        StringBuilder sb = new StringBuilder();
        String[] splitFullName = fullName.split(SPACE);
        for (int i = 0; i < splitFullName.length - 1; i++) {
            sb.append(splitFullName[i].substring(0, 1));
            sb.append(". ");
        }
        sb.append(splitFullName[splitFullName.length - 1]);

        return sb.toString();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getClub() {
        return club;
    }

    public void setClub(String club) {
        this.club = club;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public Integer getPointsAdded() {
        return pointsAdded;
    }

    public void setPointsAdded(Integer pointsAdded) {
        this.pointsAdded = pointsAdded;
    }

    public Integer getPointsDeleted() {
        return pointsDeleted;
    }

    public void setPointsDeleted(Integer pointsDeleted) {
        this.pointsDeleted = pointsDeleted;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
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

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Player other = (Player) obj;
        if (!Objects.equals(this.fullName, other.fullName)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 43 * hash + Objects.hashCode(this.fullName);
        return hash;
    }

    @Override
    public String toString() {
        return "Player{" + "code=" + code + ", fullName=" + fullName + '}';
    }
}
