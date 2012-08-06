package com.pduda.tourney.domain;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Configurable;

@Entity
@javax.persistence.Table(name = "GAME_CODE")
@Configurable(autowire = Autowire.BY_TYPE)
public class GameCode implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String PREFIX_FINAL = "FIN";
    public static final String SEPARATOR = "-";
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "GAME_CODE_ID")
    private long id;
    @Column(name = "PREFIX")
    private String prefix;
    @Column(name = "ROUND")
    private int round;
    @Column(name = "MATCH")
    private int match;

    public GameCode(String prefix, int round, int match) {
        this.prefix = prefix;
        this.round = round;
        this.match = match;
    }

    public GameCode(String stringifiedCode) {
        String[] parts = stringifiedCode.split(SEPARATOR);
        this.prefix = parts[0];
        this.round = Integer.valueOf(parts[1]);
        this.match = Integer.valueOf(parts[2]);
    }

    /**
     * Just for JPA.
     */
    public GameCode() {
        // nothing to do
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getMatch() {
        return match;
    }

    public void setMatch(int match) {
        this.match = match;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public String getPrefixedRound() {
        if (prefix.contains("FIN")) {
            return prefix;
        } else {
            return prefix + round;
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final GameCode other = (GameCode) obj;
        if (!Objects.equals(this.prefix, other.prefix)) {
            return false;
        }
        if (this.round != other.round) {
            return false;
        }
        if (this.match != other.match) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + Objects.hashCode(this.prefix);
        hash = 59 * hash + this.round;
        hash = 59 * hash + this.match;
        return hash;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(prefix);
        sb.append(SEPARATOR);
        sb.append(round);
        sb.append(SEPARATOR);
        sb.append(match);

        return sb.toString();
    }
}
