package com.pduda.tourney.domain.fee;

import com.pduda.tourney.domain.RankClass;
import com.pduda.tourney.domain.TournamentCategory;
import com.pduda.tourney.domain.fee.FeeCalculatorKey.Licensed;
import javax.inject.Inject;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/com/pduda/tourney/domain-context.xml")
public class WkpsFeeCalculatorTest {

    @Inject
    private WkpsFeeCalculator cut;

    @Before
    public void setUp() {
    }

    @Test
    public void test() {
        assertEquals(0, cut.getFee(TournamentCategory.OD, Licensed.LICENSED_AT_THIS_CLUB, 0, RankClass.NOVICE));
        assertEquals(5, cut.getFee(TournamentCategory.OD, Licensed.LICENSED_AT_OTHER_CLUB, 0, RankClass.NOVICE));
        assertEquals(5, cut.getFee(TournamentCategory.OD, Licensed.NOT_LICENCED, 0, RankClass.NOVICE));

        assertEquals(0, cut.getFee(TournamentCategory.OD, Licensed.LICENSED_AT_THIS_CLUB, 13, RankClass.NOVICE));
        assertEquals(5, cut.getFee(TournamentCategory.OD, Licensed.LICENSED_AT_OTHER_CLUB, 13, RankClass.NOVICE));
        assertEquals(5, cut.getFee(TournamentCategory.OD, Licensed.NOT_LICENCED, 13, RankClass.NOVICE));

        assertEquals(0, cut.getFee(TournamentCategory.OD, Licensed.LICENSED_AT_THIS_CLUB, 15, RankClass.NOVICE));
        assertEquals(5, cut.getFee(TournamentCategory.OD, Licensed.LICENSED_AT_OTHER_CLUB, 15, RankClass.NOVICE));
        assertEquals(5, cut.getFee(TournamentCategory.OD, Licensed.NOT_LICENCED, 15, RankClass.NOVICE));

        assertEquals(5, cut.getFee(TournamentCategory.OD, Licensed.LICENSED_AT_THIS_CLUB, 18, RankClass.NOVICE));
        assertEquals(10, cut.getFee(TournamentCategory.OD, Licensed.LICENSED_AT_OTHER_CLUB, 18, RankClass.NOVICE));
        assertEquals(15, cut.getFee(TournamentCategory.OD, Licensed.NOT_LICENCED, 18, RankClass.NOVICE));

        assertEquals(10, cut.getFee(TournamentCategory.OD, Licensed.LICENSED_AT_THIS_CLUB, 78, RankClass.AMATOR));
        assertEquals(15, cut.getFee(TournamentCategory.OD, Licensed.LICENSED_AT_OTHER_CLUB, 78, RankClass.AMATOR));
        assertEquals(20, cut.getFee(TournamentCategory.OD, Licensed.NOT_LICENCED, 78, RankClass.AMATOR));

        assertEquals(30, cut.getFee(TournamentCategory.OD, Licensed.LICENSED_AT_THIS_CLUB, 700, RankClass.MASTER));
        assertEquals(35, cut.getFee(TournamentCategory.OD, Licensed.LICENSED_AT_OTHER_CLUB, 700, RankClass.MASTER));
        assertEquals(70, cut.getFee(TournamentCategory.OD, Licensed.NOT_LICENCED, 700, RankClass.MASTER));


    }
}
