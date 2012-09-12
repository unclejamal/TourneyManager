package com.pduda.tourney.domain.service;

import com.pduda.tourney.domain.ranking.PzfsRanking;
import javax.inject.Inject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/com/pduda/tourney/persistence-test-context.xml"})
public class PersistentRankingHandlerTest {

    @Inject
    private PersistentRankingHandler cut;

    @Test
    public void test() {
        PzfsRanking pzfsRanking = cut.getPzfsRanking();

        assertNotNull(pzfsRanking);
    }
}
