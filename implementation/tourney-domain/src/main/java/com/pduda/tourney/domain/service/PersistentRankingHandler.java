package com.pduda.tourney.domain.service;

import com.pduda.tourney.domain.ranking.PzfsRanking;
import com.pduda.tourney.domain.ranking.PzfsRankingReader;
import com.pduda.tourney.domain.repository.PzfsRankingRepo;
import java.io.Serializable;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.inject.Named;
import org.springframework.transaction.annotation.Transactional;

@Named
@Transactional
public class PersistentRankingHandler implements RankingHandler, Serializable {

    private static final long serialVersionUID = 1L;
    private transient Logger log = Logger.getLogger(PersistentRankingHandler.class.getName());
    @Inject
    private PzfsRankingReader pzfsRankingReader;
    @Inject
    private PzfsRankingRepo pzfsRankingRepo;

    @Override
    public PzfsRanking getPzfsRanking() {
        int count = pzfsRankingRepo.getCount();
        if (0 == count) {
            PzfsRanking pzfsRanking = pzfsRankingReader.loadPzfsRanking();
            return pzfsRankingRepo.persistAndReturn(pzfsRanking);
        }

        return pzfsRankingRepo.getNewestPzfsRanking();
    }
}
