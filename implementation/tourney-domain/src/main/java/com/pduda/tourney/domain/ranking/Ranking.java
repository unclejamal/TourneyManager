package com.pduda.tourney.domain.ranking;

import com.pduda.tourney.domain.Gender;
import com.pduda.tourney.domain.Player;
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
    private Set<Player> players = new HashSet<Player>();
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
        Player player = new Player(rank, code, fullName, gender, city, club, points, pointsAdded, pointsDeleted, rankClass);
        players.add(player);
    }

    public List<Player> getPlayersByPlace(int place) {
        List<Player> toReturn = new ArrayList<Player>();
        for (Player player : players) {
            if (place == player.getRank()) {
                toReturn.add(player);
            }
        }
        return toReturn;
    }

    public List<Player> getPlayersByFullName(String fullName) {
        List<Player> toReturn = new ArrayList<Player>();
        for (Player player : players) {
            if (fullName.equals(player.getFullName())) {
                toReturn.add(player);
            }
        }
        return toReturn;
    }

    public Player getPlayersByCode(String code) {

        for (Player player : players) {
            if (code.equals(player.getCode())) {
                return player;
            }
        }

        return null;
    }

    public List<Player> getPlayers() {
        return new ArrayList<Player>(players);
    }
}
