package com.pduda.tourney.domain;

import java.io.Serializable;
import java.util.Objects;

public class GameId implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String PREFIX_FINAL = "FIN";
    public static final String SEPARATOR = "-";
    private String prefix;
    private int round;
    private int match;

    public GameId(String prefix, int round, int match) {
        this.prefix = prefix;
        this.round = round;
        this.match = match;
    }

    public GameId(String stringifiedId) {
        String[] parts = stringifiedId.split(SEPARATOR);
        this.prefix = parts[0];
        this.round = Integer.valueOf(parts[1]);
        this.match = Integer.valueOf(parts[2]);
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
        final GameId other = (GameId) obj;
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
