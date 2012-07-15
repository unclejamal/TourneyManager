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
    private String gender;
    private String city;
    private String club;
    private Integer points;
    private Integer pointsAdded;
    private Integer pointsDeleted;
    private String rankClass;

    public Player(int rank, String code, String fullName, String gender, String city, String club, int points, int pointsAdded, int pointsDeleted, String rankClass) {
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

    public Player(int points, String fullName) {
        this(0, NA, fullName, NA, NA, NA, points, 0, 0, NA);
    }
    
    public Player(String fullName, String code) {
        this(0, code, fullName, NA, NA, NA, 0, 0, 0, NA);
    }    

    public Player(String fullName) {
        this(0, fullName);
    }

    public Player() {
    }

    public String getShortName() {
        StringBuilder sb = new StringBuilder();
        String[] splitFullName = fullName.split(SPACE);
        for (int i=0; i<splitFullName.length -1 ; i++) {
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
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

    public String getRankClass() {
        return rankClass;
    }

    public void setRankClass(String rankClass) {
        this.rankClass = rankClass;
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
