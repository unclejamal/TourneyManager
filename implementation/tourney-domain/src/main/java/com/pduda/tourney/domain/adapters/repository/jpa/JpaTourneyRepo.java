package com.pduda.tourney.domain.adapters.repository.jpa;

import com.pduda.tourney.domain.Tourney;
import com.pduda.tourney.domain.repository.TourneyRepo;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Named
public class JpaTourneyRepo extends JpaBaseRepo<Tourney, Long> implements TourneyRepo {

    @PersistenceContext(name = "tourney-PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
}
