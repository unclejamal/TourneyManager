package com.pduda.tourney.domain;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.*;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Configurable;

@Entity
@javax.persistence.Table(name = "PLAYER")
@Configurable(autowire = Autowire.BY_TYPE)
public class Player implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String SPACE = " ";
    public static final String NA = "n/a";
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PLAYER_ID")
    private long id;
    @Column(name = "RANK")
    private Integer rank;
    @Column(name = "CODE")
    private String code;
    @Column(name = "FULL_NAME")
    private String fullName;
    @Enumerated
    @Column(name = "GENDER")
    private Gender gender;
    @Column(name = "CITY")
    private String city;
    @Column(name = "CLUB")
    private String club;
    @Column(name = "POINTS")
    private Integer points;
    @Column(name = "POINTS_ADDED")
    private Integer pointsAdded;
    @Column(name = "POINTS_DELETED")
    private Integer pointsDeleted;
    @Enumerated
    @Column(name = "RANK_CLASS")
    private RankClass rankClass;
    @Column(name = "FEE")
    private Long fee;

    public Player(int rank, String code, String fullName, Gender gender, String city, String club, int points, int pointsAdded, int pointsDeleted, RankClass rankClass) {
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
        this.fee = 0L;
    }

    public Player(int points, String fullName) {
        this(0, NA, fullName, Gender.UNKNOWN, NA, NA, points, 0, 0, RankClass.NOTRANKED);
    }

    public Player(String fullName, String code) {
        this(0, code, fullName, Gender.UNKNOWN, NA, NA, 0, 0, 0, RankClass.NOTRANKED);
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
