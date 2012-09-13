package com.pduda.tourney.domain.ranking;

import com.pduda.tourney.domain.Gender;
import com.pduda.tourney.domain.PlayerDescription;
import com.pduda.tourney.domain.RankingPlayer;
import com.pduda.tourney.domain.RankClass;
import java.io.Serializable;
import java.util.*;
import java.util.logging.Logger;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Configurable;

@Entity
@javax.persistence.Table(name = "RANKING")
@Configurable(autowire = Autowire.BY_TYPE)
public class Ranking implements Serializable {

    private static final long serialVersionUID = 1L;
    private transient Logger log = Logger.getLogger(Ranking.class.getClass().getName());
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "RANKING_ID")
    private long id;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @PrimaryKeyJoinColumn
    private Set<RankingPlayer> players = new HashSet<RankingPlayer>();
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "LAST_UPDATE")
    private Date lastUpdate;

    public Ranking(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public Ranking() {
        // JPA
    }

    public void addPlayer(int rank, String code, String fullName, Gender gender, String city, String club, int points, int pointsAdded, int pointsDeleted, RankClass rankClass) {
        PlayerDescription playerDescription = new PlayerDescription(fullName, gender, city, club);
        RankingPlayer player = new RankingPlayer(playerDescription, rank, code, points, pointsAdded, pointsDeleted, rankClass);
        players.add(player);
    }

    public List<RankingPlayer> getPlayersByPlace(int place) {
        List<RankingPlayer> toReturn = new ArrayList<RankingPlayer>();
        for (RankingPlayer player : players) {
            if (place == player.getRank()) {
                toReturn.add(player);
            }
        }
        return toReturn;
    }

    public List<RankingPlayer> getPlayersByFullName(String fullName) {
        List<RankingPlayer> toReturn = new ArrayList<RankingPlayer>();
        for (RankingPlayer player : players) {
            if (fullName.equals(player.getFullName())) {
                toReturn.add(player);
            }
        }
        return toReturn;
    }

    public RankingPlayer getPlayersByCode(String code) {

        for (RankingPlayer player : players) {
            if (code.equals(player.getCode())) {
                return player;
            }
        }

        return null;
    }

    public List<RankingPlayer> getPlayers() {
        return new ArrayList<RankingPlayer>(players);
    }
}
