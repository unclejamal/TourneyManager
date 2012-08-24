package com.pduda.tourney.domain.service;

import com.pduda.tourney.domain.ObjectMother;
import com.pduda.tourney.domain.Team;
import com.pduda.tourney.domain.Tourney;
import com.pduda.tourney.domain.TourneyCategory;
import java.util.Set;
import javax.inject.Inject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/com/pduda/tourney/persistence-context.xml"})
public class DefaultTourneyHandlerTest {

    @Inject
    private DefaultTourneyHandler cut;
    
    @Before
    public void setUp() {
    }

    @Test
    public void testCreateTournament() {
        Set<Team> teams = ObjectMother.createSeededTeams(4);
        long tourneyId = cut.createTournament(TourneyCategory.OD, "Szeligi", teams);
        
        cut.startTourney(tourneyId);
        
        Tourney persisted = cut.getTournament(tourneyId);
        System.out.println("TEST: waitin games: " + persisted.getWaitingGames());
        System.out.println("TEST: startDate: " + persisted.getStartDate());
//        assertFalse(persisted.getWaitingGames().isEmpty());
    }

 
}
