package com.pduda.tourney.domain;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.*;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Configurable;

@Entity
@javax.persistence.Table(name = "TOURNEY_PLAYER")
@Configurable(autowire = Autowire.BY_TYPE)
public class EventPlayer implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String SPACE = " ";
    public static final String NA = "n/a";
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "TOURNEY_PLAYER_ID")
    private long id;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private RankingPlayer player;
    @Column(name = "FEE")
    private Long fee;

    public EventPlayer(RankingPlayer player) {
        this.player = player;
        this.fee = 0L;
    }

    public EventPlayer(String fullName, String code) {
        this(new RankingPlayer(fullName, code));
    }

    public EventPlayer(int points, String fullName) {
        this(new RankingPlayer(points, fullName));
    }

    public EventPlayer(String fullName) {
        this(new RankingPlayer(fullName));
    }

    public EventPlayer() {
        // JPA
    }

    public String getShortName() {
        return player.getShortName();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCode() {
        return player.getCode();
    }

    public String getCity() {
        return player.getCity();
    }

    public String getClub() {
        return player.getClub();
    }

    public String getFullName() {
        return player.getFullName();
    }

    public Gender getGender() {
        return player.getGender();
    }

    public Integer getPoints() {
        return player.getPoints();
    }

//    public void setPoints(Integer points) {
//        this.points = points;
//    }
    public Integer getPointsAdded() {
        return player.getPointsAdded();
    }

//    public void setPointsAdded(Integer pointsAdded) {
//        this.pointsAdded = pointsAdded;
//    }
    public Integer getPointsDeleted() {
        return player.getPointsDeleted();
    }

//    public void setPointsDeleted(Integer pointsDeleted) {
//        this.pointsDeleted = pointsDeleted;
//    }
    public Integer getRank() {
        return player.getRank();
    }

//    public void setRank(Integer rank) {
//        this.rank = rank;
//    }
    public RankClass getRankClass() {
        return player.getRankClass();
    }

//    public void setRankClass(RankClass rankClass) {
//        this.rankClass = rankClass;
//    }
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
        final EventPlayer other = (EventPlayer) obj;
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
        return "Player{" + "code=" + player.getCode() + ", fullName=" + getFullName() + '}';
    }
}
