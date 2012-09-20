package com.pduda.tourney.domain.adapters.ranking;

import com.pduda.tourney.domain.Gender;
import com.pduda.tourney.domain.RankClass;
import com.pduda.tourney.domain.RankingPlayer;
import com.pduda.tourney.domain.ranking.Ranking;
import com.pduda.tourney.domain.util.MyUtils;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class RankingEntryConverterTest {

    public static final Integer RANK = 1;
    public static final String CODE = "code";
    public static final String FULL_NAME = "fullName";
    public static final String GENDER = "M";
    public static final String CITY = "city";
    public static final String CLUB = "WKPS";
    public static final Integer POINTS_ADDED = 10;
    public static final Integer POINTS_DELETED = 11;
    public static final Integer POINTS = 12;
    public static final String RANK_CLASS = "MASTER";
    public RankingEntryConverter cut;

    @Before
    public void setUp() {
        cut = new RankingEntryConverter();
    }

    @Test
    public void convertsToDomainClasses() {

        List<RankingEntry> rankingEntries = MyUtils.asList(new RankingEntry(RANK, CODE, FULL_NAME, GENDER, CITY, CLUB, POINTS, POINTS_ADDED, POINTS_DELETED, RANK_CLASS));

        Ranking ranking = cut.convert(rankingEntries);

        assertEquals(1, ranking.getPlayers().size());
        RankingPlayer player = ranking.getPlayersByCode(CODE);
        assertEquals(RANK, player.getRank());
        assertEquals(CODE, player.getCode());
        assertEquals(FULL_NAME, player.getFullName());
        assertEquals(Gender.MALE, player.getGender());
        assertEquals(CITY, player.getCity());
        assertEquals(CLUB, player.getClub());
        assertEquals(POINTS, player.getPoints());
        assertEquals(POINTS_ADDED, player.getPointsAdded());
        assertEquals(POINTS_DELETED, player.getPointsDeleted());
        assertEquals(RankClass.MASTER, player.getRankClass());
    }
}