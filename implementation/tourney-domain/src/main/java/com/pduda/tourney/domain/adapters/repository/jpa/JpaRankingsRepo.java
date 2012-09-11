package com.pduda.tourney.domain.adapters.repository.jpa;

import com.pduda.tourney.domain.ranking.PzfsRanking;
import com.pduda.tourney.domain.repository.PzfsRankingRepo;
import java.util.List;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Named
public class JpaRankingsRepo extends JpaBaseRepo<PzfsRanking, Long> implements PzfsRankingRepo {

    @PersistenceContext(name = "tourney-PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    public PzfsRanking getNewestPzfsRanking() {
        // FIXME pduda
        List<PzfsRanking> findEntities = findEntities(1, 0);
        return findEntities.get(0);
    }
}
