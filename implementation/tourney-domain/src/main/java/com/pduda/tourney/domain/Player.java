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
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private PlayerDescription playerDescription = new PlayerDescription();
    @Column(name = "RANK")
    private Integer rank;
    @Column(name = "CODE")
    private String code;
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
        this.playerDescription = new PlayerDescription(fullName, gender, city, club);
        this.rank = rank;
        this.code = code;
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
        // JPA
    }

    public String getShortName() {
        return playerDescription.getShortName();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public String getCity() {
        return playerDescription.getCity();
    }

    public String getClub() {
        return playerDescription.getClub();
    }

    public String getFullName() {
        return playerDescription.getFullName();
    }

    public Gender getGender() {
        return playerDescription.getGender();
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
        if (!Objects.equals(this.getFullName(), other.getFullName())) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 43 * hash + Objects.hashCode(this.getFullName());
        return hash;
    }

    @Override
    public String toString() {
        return "Player{" + "code=" + code + ", fullName=" + getFullName() + '}';
    }
}
