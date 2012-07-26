package com.pduda.tourney.domain.ranking;

import com.pduda.tourney.domain.Gender;
import com.pduda.tourney.domain.Player;
import com.pduda.tourney.domain.RankClass;
import java.util.*;

public class Ranking {

    private Map<Integer, Set<Player>> ranking = new HashMap<Integer, Set<Player>>();

    public void addPlayer(int rank, String code, String fullName, Gender gender, String city, String club, int points, int pointsAdded, int pointsDeleted, RankClass rankClass) {
        Player player = new Player(rank, code, fullName, gender, city, club, points, pointsAdded, pointsDeleted, rankClass);
        putPlayerToRanking(player);
    }

    private void putPlayerToRanking(Player player) {
        if (!ranking.containsKey(player.getRank())) {
            ranking.put(player.getRank(), new HashSet<Player>());
        }

        ranking.get(player.getRank()).add(player);
    }

    public List<Player> getPlayersByPlace(int place) {
        Set<Player> players = ranking.get(place);
        return new ArrayList<Player>(players);
    }

    public List<Player> getPlayersByFullName(String fullName) {
        List<Player> toReturn = new ArrayList<Player>();

        for (Set<Player> set : ranking.values()) {
            for (Player player : set) {
                if (fullName.equals(player.getFullName()))
                    toReturn.add(player);
            }
        }

        return toReturn;
    }
    
    public Player getPlayersByCode(String code) {
        for (Set<Player> set : ranking.values()) {
            for (Player player : set) {
                if (code.equals(player.getCode()))
                    return player;
            }
        }

        return null;
    }

    public List<Player> getPlayers() {
        List<Player> toReturn = new ArrayList<Player>();

        for (Set<Player> set : ranking.values()) {
            toReturn.addAll(set);
        }

        return toReturn;
    }
}
