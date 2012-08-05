package com.pduda.tourney.domain.repository.jpa;

import com.pduda.tourney.domain.Tournament;
import com.pduda.tourney.domain.repository.TourneyRepo;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Named
public class JpaTourneyRepo extends BaseJpaRepo<Tournament, Integer> implements TourneyRepo {

    @PersistenceContext(name = "tourney-PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
}
