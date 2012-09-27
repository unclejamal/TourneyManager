package com.pduda.tourney.domain.adapters.repository.jpa;

import com.pduda.tourney.domain.TourneyEvent;
import com.pduda.tourney.domain.repository.EventRepo;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Named
public class JpaEventRepo extends JpaBaseRepo<TourneyEvent, Long> implements EventRepo {

    @PersistenceContext(name = "tourney-PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
}
