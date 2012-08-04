package com.pduda.tourney.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Team implements Serializable {

    private static final long serialVersionUID = 1L;
    private int id;
    private int seed = Integer.MAX_VALUE;
    private String name;
    private List<Player> members = new ArrayList<Player>();

    public Team(Player... memberz) {
        Collections.addAll(members, memberz);
        Collections.sort(this.members, new PlayersByPointsComparator());
        this.name = computeName(members);
    }

    private String computeName(List<Player> members) {
        StringBuilder sb = new StringBuilder();
        sb.append(members.get(0).getShortName());

        if (isDouble()) {
            sb.append(", ");
            sb.append(members.get(1).getShortName());
        }

        return sb.toString();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSeed() {
        return seed;
    }

    public boolean isSeeded() {
        return seed != Integer.MAX_VALUE;
    }

    public void setSeed(int seed) {
        this.seed = seed;
    }

    public List<Player> getMembers() {
        return members;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPoints() {
        int points1 = members.get(0).getPoints();

        if (members.size() == 2) {
            int points2 = members.get(1).getPoints();
            return Math.max(points1, points2) + (int) (Math.min(points1, points2) / 2);
        }

        return points1;
    }

    public boolean isSingle() {
        return members.size() == 1;
    }

    public boolean isDouble() {
        return members.size() == 2;
    }

    @Override
    public String toString() {
        return "Team{" + "seed=" + seed + ", members=" + members + '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Team other = (Team) obj;
        if (!Objects.equals(this.members, other.members)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + Objects.hashCode(this.members);
        return hash;
    }
}
